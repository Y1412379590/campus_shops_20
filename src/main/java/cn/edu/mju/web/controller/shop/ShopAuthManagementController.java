package cn.edu.mju.web.controller.shop;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.mju.dto.ShopAuthMapExecution;
import cn.edu.mju.entity.PersonInfo;
import cn.edu.mju.entity.Shop;
import cn.edu.mju.entity.ShopAuthMap;
import cn.edu.mju.enums.ShopAuthMapStateEnum;
import cn.edu.mju.service.ShopAuthMapService;
import cn.edu.mju.util.CodeUtil;
import cn.edu.mju.util.HttpServletRequestUtil;
import cn.edu.mju.util.QRCodeUtil;
import cn.edu.mju.util.baidu.ShortNetAddress;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/shop")
public class ShopAuthManagementController {
	@Autowired
	private ShopAuthMapService shopAuthMapService;

	private static String urlPrefix;

	private static String urlMiddle;

	private static String urlSuffix;

	private static String authUrl;

	@Value("${wechat.prefix}")
	public  String getUrlPrefix() {
		return urlPrefix;
	}

	@Value("${wechat.middle}")
	public  String getUrlMiddle() {
		return urlMiddle;
	}
	@Value("${wechat.suffix}")
	public  String getUrlSuffix() {
		return urlSuffix;
	}
	@Value("${wechat.auth.url}")
	public  String getAuthUrl() {
		return authUrl;
	}

	@RequestMapping(value = "/listshopauthmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopAuthMapsByShop(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			ShopAuthMapExecution se = shopAuthMapService
					.listShopAuthMapByShopId(currentShop.getShopId(),
							pageIndex, pageSize);
			modelMap.put("shopAuthMapList", se.getShopAuthMapList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopauthmapbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopAuthMapById(@RequestParam Long shopAuthId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (shopAuthId != null && shopAuthId > -1) {
			ShopAuthMap shopAuthMap = shopAuthMapService
					.getShopAuthMapById(shopAuthId);
			modelMap.put("shopAuthMap", shopAuthMap);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopAuthId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/addshopauthmap", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addShopAuthMap(String shopAuthMapStr,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		ShopAuthMap shopAuthMap = null;
		try {
			shopAuthMap = mapper.readValue(shopAuthMapStr, ShopAuthMap.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (shopAuthMap != null) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute(
						"currentShop");
				PersonInfo user = (PersonInfo) request.getSession()
						.getAttribute("user");
				if (!currentShop.getOwner().getUserId().equals(user.getUserId())) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "无操作权限");
					return modelMap;
				}
				shopAuthMap.setShop(currentShop);
				shopAuthMap.setEmployee(user);
				ShopAuthMapExecution se = shopAuthMapService
						.addShopAuthMap(shopAuthMap);
				if (se.getState() == ShopAuthMapStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入授权信息");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyshopauthmap", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShopAuthMap(String shopAuthMapStr,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		boolean statusChange = HttpServletRequestUtil.getBoolean(request,"statusChange");
		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		ShopAuthMap shopAuthMap = null;
		try {
			shopAuthMap = mapper.readValue(shopAuthMapStr, ShopAuthMap.class);
			shopAuthMap.setShop((Shop) request.getSession().getAttribute("currentShop"));
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
			try {
				if(!checkPermission(shopAuthMap.getShopAuthId())){
					modelMap.put("success",false);
					modelMap.put("errMsg","无法对店家本身权限做操作，已经是最高权限");
					return modelMap;
				}
				ShopAuthMapExecution se = shopAuthMapService
						.modifyShopAuthMap(shopAuthMap);
				if (se.getState() == ShopAuthMapStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入要修改的授权信息");
		}
		return modelMap;
	}







	private boolean checkPermission(Long shopAuthId){

		ShopAuthMap grantedPerson = shopAuthMapService.getShopAuthMapById(shopAuthId);
		return grantedPerson.getTitleFlag() != 0;

	}


	@RequestMapping(value = "/generateqrcode4shopauth", method = RequestMethod.GET)
	@ResponseBody
	public void generateQRCode4ShopAuth(HttpServletRequest request,
									   HttpServletResponse response) {

		Shop shop = (Shop) request.getSession().getAttribute("currentShop");

		if (shop != null && shop.getShopId() != null) {
			try {
				long timpStamp = System.currentTimeMillis();

				String content = "{aaashopIdaaa:" + shop.getShopId() + ",aaacreateTimeaaa:" +
						timpStamp + "}";
				String longUrl = urlPrefix + authUrl+urlMiddle+URLEncoder.encode(content,"UTF-8") + urlSuffix;
				String shortUrl = ShortNetAddress.getShortURL(longUrl);
				BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(shortUrl,
						response);

				MatrixToImageWriter.writeToStream(qRcodeImg, "png",
						response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}





}
