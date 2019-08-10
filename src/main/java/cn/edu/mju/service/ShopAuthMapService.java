package cn.edu.mju.service;


import cn.edu.mju.dto.ShopAuthMapExecution;
import cn.edu.mju.entity.ShopAuthMap;

public interface ShopAuthMapService extends BaseService {
	/**
	 *
	 *根据店铺id分页显示该店铺的授权信息
	 *
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,
												 Integer pageIndex, Integer pageSize);

	/**
	 *
	 * 扫码后添加授权
	 * 
	 * @param shopAuthMap
	 * @return
	 * @throws RuntimeException
	 */
	ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap)
			throws RuntimeException;

	/**
	 * 更新授权信息，包括职位等
	 * 
	 * @param shopAuthId
	 * @param title
	 * @param titleFlag
	 * @param enableStatus
	 * @return
	 * @throws RuntimeException
	 */
	ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws RuntimeException;

	/**
	 * 
	 * @param shopAuthMapId
	 * @return
	 * @throws RuntimeException
	 */
	ShopAuthMapExecution removeShopAuthMap(Long shopAuthMapId)
			throws RuntimeException;

	/**
	 * 根据id返回授权信息
	 *
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap getShopAuthMapById(Long shopAuthId);

}
