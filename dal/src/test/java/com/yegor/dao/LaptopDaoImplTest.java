package com.yegor.dao;

import com.yegor.entity.LaptopEntity;
import com.yegor.entity.RoleEntity;
import com.yegor.entity.UserEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by YegorKost on 22.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-dal-context-test.xml")
@Sql(scripts = "/create-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class LaptopDaoImplTest {
    @Autowired
    private LaptopDao<LaptopEntity> laptopDao;

    private static LaptopEntity laptopEntity1, laptopEntity2;

    @BeforeClass
    public static void setUp() {
        laptopEntity1 = new LaptopEntity();
        laptopEntity1.setModel("model1");
        laptopEntity1.setMake("make1");
        laptopEntity1.setScreen(15.4f);
        laptopEntity1.setProcessor("processor1");
        laptopEntity1.setMemory(1);
        laptopEntity1.setImage("image1");
        laptopEntity1.setAmount(1);
        laptopEntity1.setPrice(1.11);

        laptopEntity2 = new LaptopEntity();
        laptopEntity2.setModel("model2");
        laptopEntity2.setMake("make2");
        laptopEntity2.setScreen(15.4f);
        laptopEntity2.setProcessor("processor2");
        laptopEntity2.setMemory(2);
        laptopEntity2.setImage("image2");
        laptopEntity2.setAmount(2);
        laptopEntity2.setPrice(2.22);
    }

    @Test
    public void addLaptop() throws Exception {
        LaptopEntity laptopEntityNew = new LaptopEntity();
        laptopEntityNew.setModel("newModel");
        laptopDao.addLaptop(laptopEntityNew);
        assertEquals(laptopEntityNew, laptopDao.getLaptop("newModel"));
    }

    @Test
    public void getLaptop() throws Exception {
        assertEquals(laptopEntity1, laptopDao.getLaptop("model1"));
        assertEquals(laptopEntity2, laptopDao.getLaptop("model2"));
    }

    @Test
    public void getAllLaptops() throws Exception {
        List<LaptopEntity> laptopEntities = laptopDao.getAllLaptops();
        assertTrue(laptopEntities.contains(laptopEntity1));
        assertTrue(laptopEntities.contains(laptopEntity2));
    }

    @Test
    public void updateLaptop() throws Exception {
        LaptopEntity laptopEntityUpdate = laptopDao.getLaptop("model1");
        laptopEntityUpdate.setPrice(7.77);
        laptopDao.updateLaptop(laptopEntityUpdate);
        assertEquals(laptopEntityUpdate, laptopDao.getLaptop("model1"));
    }

    @Test
    public void deleteLaptop() throws Exception {
        laptopDao.deleteLaptop(laptopEntity1);
        assertEquals(null, laptopDao.getLaptop("model1"));
    }

}