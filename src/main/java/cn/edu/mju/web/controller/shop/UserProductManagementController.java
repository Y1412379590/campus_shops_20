package cn.edu.mju.web.controller.shop;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.edu.mju.dto.EchartSeries;
import cn.edu.mju.dto.EchartXAxis;
import cn.edu.mju.dto.UserProductMapExecution;
import cn.edu.mju.entity.Product;
import cn.edu.mju.entity.ProductSellDaily;
import cn.edu.mju.entity.Shop;
import cn.edu.mju.entity.UserProductMap;
import cn.edu.mju.service.*;
import cn.edu.mju.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/shop")
public class UserProductManagementController {

	@Autowired
	private UserProductMapService userProductMapService;

	@Autowired
	private ProductSellDailyService productSellDailyService;



	/*
	* 列出销量统计
	* */
	@RequestMapping(value = "/listproductselldailyinfobyshop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listProductSellDailyInfobSshop(HttpServletRequest request){


		Map<String, Object> modelMap = new HashMap<String, Object>();

		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if(currentShop != null && currentShop.getShopId() != null){

			ProductSellDaily productSellDaily = new ProductSellDaily();
			productSellDaily.setShop(currentShop);

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE,-1);
			Long endTime = calendar.getTimeInMillis();
			calendar.add(Calendar.DATE,-6);
			Long beginTime = calendar.getTimeInMillis();
			List<ProductSellDaily> productSellDailyList = productSellDailyService.listProductSellDaily(productSellDaily,beginTime,endTime);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			HashSet<String> legendData = new HashSet<>();
			HashSet<String> xData = new HashSet<>();
			List<EchartSeries> series = new ArrayList<>();
			List<Integer> totalList = new ArrayList<>();

			String currentProductName = "";

			for (int i = 0;i<productSellDailyList.size();i++){

				ProductSellDaily productSellDaily1 = productSellDailyList.get(i);

				//去重
				legendData.add(productSellDaily1.getProduct().getProductName());

				xData.add(sdf.format(productSellDaily1.getCreateTime()));

				if (!currentProductName.equals(productSellDaily1.getProduct().getProductName())&&!currentProductName.isEmpty()) {

					EchartSeries es = new EchartSeries();
					es.setName(currentProductName);

					es.setData(totalList.subList(0,totalList.size()));

					series.add(es);

					totalList = new ArrayList<>();

					currentProductName = productSellDaily1.getProduct().getProductName();

					totalList.add(productSellDaily1.getTotal());

				}else{
					totalList.add(productSellDaily1.getTotal());
					currentProductName = productSellDaily1.getProduct().getProductName();
				}

				if(i == productSellDailyList.size() -1){
					EchartSeries es = new EchartSeries();
					es.setName(currentProductName);
					es.setData(totalList.subList(0,totalList.size()));
					series.add(es);
				}

			}

			modelMap.put("series",series);
			modelMap.put("legendData",legendData);
			List<EchartXAxis> xAxes = new ArrayList<>();
			EchartXAxis echartXAxis = new EchartXAxis();
			echartXAxis.setData(xData);
			xAxes.add(echartXAxis);
			modelMap.put("xAxis",xAxes);
			modelMap.put("success",true);

		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","empty shopId");
		}

		return modelMap;

	}



	@RequestMapping(value = "/listuserproductmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listUserProductMapsByShop(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			UserProductMap userProductMapCondition = new UserProductMap();
			userProductMapCondition.setShop(currentShop);
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			if (productName != null) {
				Product product = new Product();
				product.setProductName(productName);
				userProductMapCondition.setProduct(product);
			}
			UserProductMapExecution ue = userProductMapService
					.listUserProductMap(userProductMapCondition, pageIndex,
							pageSize);
			modelMap.put("userProductMapList", ue.getUserProductMapList());
			modelMap.put("count", ue.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

}
