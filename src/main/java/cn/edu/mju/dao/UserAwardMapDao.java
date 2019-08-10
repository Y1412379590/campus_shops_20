package cn.edu.mju.dao;

import java.util.List;

import cn.edu.mju.entity.UserAwardMap;
import org.apache.ibatis.annotations.Param;


public interface UserAwardMapDao {

	/**
	 * 根据传入的查询条件分页返回数据
	 *
	 * @param userAwardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */

	List<UserAwardMap> queryUserAwardMapList(
            @Param("userAwardCondition") UserAwardMap userAwardCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 
	 * @param userAwardCondition
	 * @return
	 */
	int queryUserAwardMapCount(
            @Param("userAwardCondition") UserAwardMap userAwardCondition);

	/**
	 * 
	 * @param userAwardId
	 * @return
	 */
	UserAwardMap queryUserAwardMapById(long userAwardId);

	/**
	 * 
	 * @param userAwardMap
	 * @return
	 */
	int insertUserAwardMap(UserAwardMap userAwardMap);

	/**
	 * 
	 * @param userAwardMap
	 * @return
	 */
	int updateUserAwardMap(UserAwardMap userAwardMap);
}
