package cn.edu.mju.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 顾客消费的商品映射
 *
 */
@Getter@Setter
public class ProductSellDaily {

    private Long productSellDailyId;

    //哪天的销量  精确到天
    private Date createTime;

    //销量
    private Integer total;

    //商品信息
    private Product product;

    //店铺信息
    private Shop shop;





}
