package cn.edu.mju.dao;

import java.util.List;

import cn.edu.mju.entity.UserShopMap;
import org.apache.ibatis.annotations.Param;


public interface UserShopMapDao {
	/**
	 * 根据传入的查询条件查询
	 *
	 * @param userShopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserShopMap> queryUserShopMapList(
            @Param("userShopCondition") UserShopMap userShopCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 根据用户id和商铺id查询
	 * @param userId
	 * @param shopId
	 * @return
	 */
	UserShopMap queryUserShopMap(@Param("userId") long userId,
                                 @Param("shopId") long shopId);

	/**
	 * 
	 * @param userShopCondition
	 * @return
	 */
	int queryUserShopMapCount(
            @Param("userShopCondition") UserShopMap userShopCondition);

	/**
	 *添加一條用戶店鋪的積分記錄
	 *
	 * @param userShopMap
	 * @return
	 */
	int insertUserShopMap(UserShopMap userShopMap);

	/**
	 * 更新用戶在某店鋪的積分
	 *
	 * @param userShopMap
	 * @return
	 */
	int updateUserShopMapPoint(UserShopMap userShopMap);

}
