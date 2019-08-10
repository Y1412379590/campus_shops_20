package cn.edu.mju.dao;

import cn.edu.mju.entity.ProductSellDaily;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductSellDailyDaoTest {

    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    @Autowired
    private UserProductMapDao userProductMapDao;

    @Test
    public void insertProductSellDaily() {


    }
}