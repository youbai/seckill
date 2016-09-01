package org.seckill.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SeckillServiceTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static BeanFactory factory = null;
    private static SeckillService service = null;
    @BeforeClass
    public static void setUpBeforeClass(){
        factory = new ClassPathXmlApplicationContext("classpath:spring/spring-service.xml",
                "classpath:spring/spring-dao.xml");
        service = factory.getBean(SeckillService.class);
    }

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = service.getSeckillList();
        log.info("list = {}",list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1;
        Seckill seckill = service.getById(id);
        log.info("seckill = {}",seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {

    }

    @Test
    public void testExecuteSeckill() throws Exception {

    }

}