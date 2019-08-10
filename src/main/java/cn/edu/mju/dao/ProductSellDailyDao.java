package cn.edu.mju.dao;

import cn.edu.mju.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductSellDailyDao extends BaseDao {


    /**
     * 根据查询条件返回商品日销售的统计列表
     *
     * @param productSellDailyCondition
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ProductSellDaily> queryProductSellDailyList(
            @Param("productSellDailyCondition") ProductSellDaily productSellDailyCondition,
            @Param("beginTime") Long beginTime,@Param("endTime") Long endTime);



    int insertProductSellDaily();


    int insertDefaultProductSellDaily();




}
