package cn.edu.mju.web.controller.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.edu.mju.dto.UserShopMapExecution;
import cn.edu.mju.entity.PersonInfo;
import cn.edu.mju.entity.Shop;
import cn.edu.mju.entity.UserShopMap;
import cn.edu.mju.service.UserShopMapService;
import cn.edu.mju.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/shop")
public class UserShopManagementController {
	@Autowired
	private UserShopMapService userShopMapService;


	@RequestMapping(value = "/listusershopmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listUserShopMapsByShop(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			UserShopMap userShopMapCondition = new UserShopMap();
			userShopMapCondition.setShop(currentShop);
			String userName = HttpServletRequestUtil.getString(request,
					"userName");
			if (userName != null) {
				PersonInfo personInfo = new PersonInfo();
				personInfo.setName(userName);
				userShopMapCondition.setUser(personInfo);
			}
			UserShopMapExecution ue = userShopMapService.listUserShopMap(
					userShopMapCondition, pageIndex, pageSize);
			modelMap.put("userShopMapList", ue.getUserShopMapList());
			modelMap.put("count", ue.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

}
