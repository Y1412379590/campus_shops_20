package cn.edu.mju.service.serviceImpl;

import java.util.List;

import cn.edu.mju.dao.UserShopMapDao;
import cn.edu.mju.dto.UserShopMapExecution;
import cn.edu.mju.entity.UserShopMap;
import cn.edu.mju.service.UserShopMapService;
import cn.edu.mju.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShopMapServiceImpl implements UserShopMapService {

	@Autowired
	private UserShopMapDao userShopMapDao;

	@Override
	public UserShopMapExecution listUserShopMap(

			UserShopMap userShopMapCondition, int pageIndex, int pageSize) {
		if (userShopMapCondition != null && pageIndex != -1 && pageSize != -1) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
					pageSize);
			List<UserShopMap> userShopMapList = userShopMapDao
					.queryUserShopMapList(userShopMapCondition, beginIndex,
							pageSize);
			int count = userShopMapDao
					.queryUserShopMapCount(userShopMapCondition);
			UserShopMapExecution ue = new UserShopMapExecution();
			ue.setUserShopMapList(userShopMapList);
			ue.setCount(count);
			return ue;
		} else {
			return null;
		}

	}
}
