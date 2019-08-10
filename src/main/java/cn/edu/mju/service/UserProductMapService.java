package cn.edu.mju.service;

import cn.edu.mju.dto.UserProductMapExecution;
import cn.edu.mju.entity.UserProductMap;


/**
 * @see cn.edu.mju.service.serviceImpl.UserProductMapServiceImpl
 */
public interface UserProductMapService extends BaseService {
	/**
	 * 查询店铺每日销量
	 *
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserProductMapExecution listUserProductMap(
			UserProductMap userProductCondition, Integer pageIndex,
			Integer pageSize);




}
