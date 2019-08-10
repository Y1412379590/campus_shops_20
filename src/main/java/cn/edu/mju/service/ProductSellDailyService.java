package cn.edu.mju.service;


import cn.edu.mju.entity.ProductSellDaily;

import java.util.List;

/**
 * @see cn.edu.mju.service.serviceImpl.ProductSellDailyServiceImpl
 */
public interface ProductSellDailyService extends BaseService {

    //每日定时对所有店铺的商品销量统计
    void dailyCalculate();

    /**
     * 根据查询条件返回商品日销售的统计列表
     */
    List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDaily,Long beginTime,Long endTime);



}
