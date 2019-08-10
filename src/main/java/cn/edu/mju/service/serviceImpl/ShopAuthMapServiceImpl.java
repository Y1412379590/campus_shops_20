package cn.edu.mju.service.serviceImpl;

import java.util.Date;
import java.util.List;

import cn.edu.mju.dao.ShopAuthMapDao;
import cn.edu.mju.dto.ShopAuthMapExecution;
import cn.edu.mju.entity.ShopAuthMap;
import cn.edu.mju.enums.ShopAuthMapStateEnum;
import cn.edu.mju.service.ShopAuthMapService;
import cn.edu.mju.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ShopAuthMapServiceImpl extends BaseServiceImpl implements ShopAuthMapService {


	@Autowired
	private ShopAuthMapDao shopAuthMapDao;


	@Override
	public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,
														Integer pageIndex, Integer pageSize) {
		if (shopId != null && pageIndex != null && pageSize != null) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
					pageSize);
			List<ShopAuthMap> shopAuthMapList = shopAuthMapDao
					.queryShopAuthMapListByShopId(shopId, beginIndex, pageSize);
			int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
			ShopAuthMapExecution se = new ShopAuthMapExecution();
			se.setShopAuthMapList(shopAuthMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}

	}


	@Override
	@Transactional
	public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap)
			throws RuntimeException {
		if (shopAuthMap != null && shopAuthMap.getShop() != null && shopAuthMap.getShop().getShopId() != null
				&& shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null) {
			shopAuthMap.setCreateTime(System.currentTimeMillis());
			shopAuthMap.setLastEditTime(System.currentTimeMillis());
			shopAuthMap.setEnableStatus(1);
			shopAuthMap.setTitleFlag(0);
			try {
				//添加授权信息
				int effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
				if (effectedNum <= 0) {
					throw new RuntimeException("添加授权失败");
				}
				return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,
						shopAuthMap);
			} catch (Exception e) {
				throw new RuntimeException("添加授权失败:" + e.toString());
			}
		} else {
			return new ShopAuthMapExecution(
					ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
		}
	}

	@Override
	@Transactional
	public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap)
			throws RuntimeException {
		if (shopAuthMap == null || shopAuthMap.getShopAuthId() == null) {
			return new ShopAuthMapExecution(
					ShopAuthMapStateEnum.NULL_SHOPAUTH_ID);
		} else {
			try {
				int effectedNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
				if (effectedNum <= 0) {
					return new ShopAuthMapExecution(
							ShopAuthMapStateEnum.INNER_ERROR);
				} else {// 创建成功
					return new ShopAuthMapExecution(
							ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
				}
			} catch (Exception e) {
				throw new RuntimeException("updateShopByOwner error: "
						+ e.getMessage());
			}
		}
	}

	@Override
	public ShopAuthMapExecution removeShopAuthMap(Long shopAuthMapId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
		return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
	}

}
