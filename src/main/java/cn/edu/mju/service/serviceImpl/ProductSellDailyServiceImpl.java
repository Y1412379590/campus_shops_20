package cn.edu.mju.service.serviceImpl;

import cn.edu.mju.dao.ProductSellDailyDao;
import cn.edu.mju.entity.ProductSellDaily;
import cn.edu.mju.service.ProductSellDailyService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSellDailyServiceImpl extends BaseServiceImpl implements ProductSellDailyService {


    @Autowired
    private ProductSellDailyDao productSellDailyDao;


    @Override
    public void dailyCalculate() {


        //定期统计每个店铺每个商品的销售量
        productSellDailyDao.insertProductSellDaily();

        //统计销量为0的
        productSellDailyDao.insertDefaultProductSellDaily();

    }


    @Override
    public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDaily, Long beginTime, Long endTime) {
        return productSellDailyDao.queryProductSellDailyList(productSellDaily,beginTime,endTime);
    }
}
