package cn.edu.mju.dao;

import cn.edu.mju.CampusShopsStarter;
import cn.edu.mju.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampusShopsStarter.class)
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;


    @Test
    public void queryAreas() {
        List<Area> areaList = areaDao.queryAreas();
        System.out.println(areaList.size());

    }
}