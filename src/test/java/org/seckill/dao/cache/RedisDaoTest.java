package org.seckill.dao.cache;

import org.junit.BeforeClass;
import org.junit.Test;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @title
 * @since 2016/6/12
 */
public class RedisDaoTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private long id = 1;
    public static BeanFactory factory = null;
    private static RedisDao redisDao = null;
    private static SeckillDao seckillDao = null;
    @BeforeClass
    public static void setUpBeforeClass(){
        factory = new ClassPathXmlApplicationContext(
                "classpath:spring/spring-dao.xml");
        redisDao = factory.getBean(RedisDao.class);
        seckillDao = factory.getBean(SeckillDao.class);
    }
    @Test
    public void getSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }

}