package org.seckill.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Spring整合junit，junit启动时加载springIOC容器
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring/spring-dao.xml"})
public class SeckillDaoTest {//main/resources/spring/spring-dao.xml
    public static BeanFactory factory = null;
    private static SeckillDao seckillDao = null;
    @BeforeClass
    public static void setUpBeforeClass(){
        factory = new ClassPathXmlApplicationContext("classpath:spring/spring-dao.xml");
        seckillDao = factory.getBean(SeckillDao.class);
    }
//    ApplicationContext ac =
//            new ClassPathXmlApplicationContext("classpath*:spring/spring-dao.xml");
//        SeckillDao seckillDao = ac.getBean(SeckillDao.class);
    //
//    @Resource
//    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {

    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1;
        Seckill seckill = seckillDao.queryById(id);
        Assert.assertEquals(id,seckill.getSeckillId());
        System.out.print(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {

    }
}