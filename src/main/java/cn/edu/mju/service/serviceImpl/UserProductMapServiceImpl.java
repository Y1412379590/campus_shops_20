package cn.edu.mju.service.serviceImpl;

import java.util.Date;
import java.util.List;

import cn.edu.mju.dao.UserProductMapDao;
import cn.edu.mju.dto.UserProductMapExecution;
import cn.edu.mju.entity.UserProductMap;
import cn.edu.mju.service.UserProductMapService;
import cn.edu.mju.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserProductMapServiceImpl extends BaseServiceImpl implements UserProductMapService {

	@Autowired
	private UserProductMapDao userProductMapDao;


	@Override
	public UserProductMapExecution listUserProductMap(
			UserProductMap userProductCondition, Integer pageIndex,
			Integer pageSize) {
		if (userProductCondition != null && pageIndex != null
				&& pageSize != null) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
					pageSize);
			List<UserProductMap> userProductMapList = userProductMapDao
					.queryUserProductMapList(userProductCondition, beginIndex,
							pageSize);
			int count = userProductMapDao
					.queryUserProductMapCount(userProductCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}

	}



}
