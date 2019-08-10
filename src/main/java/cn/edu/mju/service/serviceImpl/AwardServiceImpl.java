package cn.edu.mju.service.serviceImpl;

import java.util.Date;
import java.util.List;

import ch.qos.logback.core.util.FileUtil;
import cn.edu.mju.dao.AwardDao;
import cn.edu.mju.dto.AwardExecution;
import cn.edu.mju.entity.Award;
import cn.edu.mju.enums.AwardStateEnum;
import cn.edu.mju.service.AwardService;
import cn.edu.mju.util.ImageUtil;
import cn.edu.mju.util.PageCalculator;
import cn.edu.mju.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



@Service
public class AwardServiceImpl implements AwardService {

	@Autowired
	private AwardDao awardDao;

	@Override
	public AwardExecution getAwardList(Award awardCondition, int pageIndex,
									   int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Award> awardList = awardDao.queryAwardList(awardCondition,
				rowIndex, pageSize);
		int count = awardDao.queryAwardCount(awardCondition);
		AwardExecution ae = new AwardExecution();
		ae.setAwardList(awardList);
		ae.setCount(count);
		return ae;
	}

	@Override
	public Award getAwardById(long awardId) {
		return awardDao.queryAwardByAwardId(awardId);
	}

	@Override
	@Transactional
	public AwardExecution addAward(Award award, CommonsMultipartFile thumbnail) {
		if (award != null && award.getShopId() != null) {
			award.setCreateTime(System.currentTimeMillis());
			award.setLastEditTime(System.currentTimeMillis());
			award.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(award, thumbnail);
			}
			try {
				int effectedNum = awardDao.insertAward(award);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品失败:" + e.toString());
			}
			return new AwardExecution(AwardStateEnum.SUCCESS, award);
		} else {
			return new AwardExecution(AwardStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AwardExecution modifyAward(Award award,
			CommonsMultipartFile thumbnail) {
		if (award != null && award.getShopId() != null) {
			award.setLastEditTime(System.currentTimeMillis());
			if (thumbnail != null) {
				Award tempAward = awardDao.queryAwardByAwardId(award
						.getAwardId());
				if (tempAward.getAwardImg() != null) {
					PathUtil.deleteFile(tempAward.getAwardImg());
				}
				addThumbnail(award, thumbnail);
			}
			try {
				int effectedNum = awardDao.updateAward(award);
				if (effectedNum <= 0) {
					throw new RuntimeException("更新商品信息失败");
				}
				return new AwardExecution(AwardStateEnum.SUCCESS, award);
			} catch (Exception e) {
				throw new RuntimeException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new AwardExecution(AwardStateEnum.EMPTY);
		}
	}

	private void addThumbnail(Award award, CommonsMultipartFile thumbnail) {
		String dest = PathUtil.getShopImagePath(award.getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		award.setAwardImg(thumbnailAddr);
	}

}
