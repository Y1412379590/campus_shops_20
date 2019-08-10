package cn.edu.mju.service;


import cn.edu.mju.dto.UserShopMapExecution;
import cn.edu.mju.entity.UserShopMap;

/**
 * @see cn.edu.mju.service.serviceImpl.UserShopMapServiceImpl
 */
public interface UserShopMapService {

	/*
	* 根据传入的查询信息分页查询用户积分列表
	* */
	UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition,
										 int pageIndex, int pageSize);

}
