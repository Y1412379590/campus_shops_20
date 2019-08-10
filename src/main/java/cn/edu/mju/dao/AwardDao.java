package cn.edu.mju.dao;

import java.util.List;

import cn.edu.mju.entity.Award;
import org.apache.ibatis.annotations.Param;


public interface AwardDao extends BaseDao {

	/**
	 * 依据传入的条件分页显示奖品信息列表
	 *
	 * @param awardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwardList(@Param("awardCondition") Award awardCondition,
							   @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	int queryAwardCount(@Param("awardCondition") Award awardCondition);

	Award queryAwardByAwardId(long awardId);

	int insertAward(Award award);

	int updateAward(Award award);

	int deleteAward(@Param("awardId") long awardId,@Param("shopId")long shopId);
}
