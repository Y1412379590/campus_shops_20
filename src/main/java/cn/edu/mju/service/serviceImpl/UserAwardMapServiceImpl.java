package cn.edu.mju.service.serviceImpl;

import java.util.Date;
import java.util.List;

import cn.edu.mju.dao.UserAwardMapDao;
import cn.edu.mju.dao.UserShopMapDao;
import cn.edu.mju.dto.UserAwardMapExecution;
import cn.edu.mju.entity.UserAwardMap;
import cn.edu.mju.entity.UserShopMap;
import cn.edu.mju.enums.UserAwardMapStateEnum;
import cn.edu.mju.service.UserAwardMapService;
import cn.edu.mju.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class UserAwardMapServiceImpl implements UserAwardMapService {

	@Autowired
	private UserAwardMapDao userAwardMapDao;

	@Autowired
	private UserShopMapDao userShopMapDao;

	@Override
	public UserAwardMapExecution listUserAwardMap(
			UserAwardMap userAwardCondition, Integer pageIndex, Integer pageSize) {
		if (userAwardCondition != null && pageIndex != null && pageSize != null) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
					pageSize);
			List<UserAwardMap> userAwardMapList = userAwardMapDao
					.queryUserAwardMapList(userAwardCondition, beginIndex,
							pageSize);
			int count = userAwardMapDao
					.queryUserAwardMapCount(userAwardCondition);
			UserAwardMapExecution ue = new UserAwardMapExecution();
			ue.setUserAwardMapList(userAwardMapList);
			ue.setCount(count);
			return ue;
		} else {
			return null;
		}

	}

	@Override
	public UserAwardMap getUserAwardMapById(long userAwardMapId) {
		return userAwardMapDao.queryUserAwardMapById(userAwardMapId);
	}

	@Override
	@Transactional
	public UserAwardMapExecution addUserAwardMap(UserAwardMap userAwardMap)
			throws RuntimeException {
		if (userAwardMap != null && userAwardMap.getUser() != null
				) {
			userAwardMap.setCreateTime(System.currentTimeMillis());
			try {
				int effectedNum = 0;
				if (userAwardMap.getPoint() != null
						&& userAwardMap.getPoint() > 0) {
					UserShopMap userShopMap = userShopMapDao.queryUserShopMap(
							userAwardMap.getUser().getUserId(),1);
					if (userShopMap != null) {
						if (userShopMap.getPoint() >= userAwardMap.getPoint()) {
							userShopMap.setPoint(userShopMap.getPoint()
									- userAwardMap.getPoint());
							effectedNum = userShopMapDao
									.updateUserShopMapPoint(userShopMap);
							if (effectedNum <= 0) {
								throw new RuntimeException("更新积分信息失败");
							}
						} else {
							throw new RuntimeException("积分不足无法领取");
						}

					} else {
						// 在店铺没有积分
						throw new RuntimeException("在本店铺没有积分，无法对换奖品");
					}
				}
				effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
				if (effectedNum <= 0) {
					throw new RuntimeException("领取奖励失败");
				}

				return new UserAwardMapExecution(UserAwardMapStateEnum.SUCCESS,
						userAwardMap);
			} catch (Exception e) {
				throw new RuntimeException("领取奖励失败:" + e.toString());
			}
		} else {
			return new UserAwardMapExecution(
					UserAwardMapStateEnum.NULL_USERAWARD_INFO);
		}
	}

	@Override
	@Transactional
	public UserAwardMapExecution modifyUserAwardMap(UserAwardMap userAwardMap)
			throws RuntimeException {
		if (userAwardMap == null || userAwardMap.getUserAwardId() == null
				|| userAwardMap.getUsedStatus() == null) {
			return new UserAwardMapExecution(
					UserAwardMapStateEnum.NULL_USERAWARD_ID);
		} else {
			try {
				int effectedNum = userAwardMapDao
						.updateUserAwardMap(userAwardMap);
				if (effectedNum <= 0) {
					return new UserAwardMapExecution(
							UserAwardMapStateEnum.INNER_ERROR);
				} else {
					return new UserAwardMapExecution(
							UserAwardMapStateEnum.SUCCESS, userAwardMap);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyUserAwardMap error: "
						+ e.getMessage());
			}
		}
	}

}
