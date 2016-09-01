package org.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/6/5.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final String slat = "abcdefghijklmnopqrstuvwxyz";

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private RedisTemplate redisTemplate;

    private String getMd5(long seckillId){
        String base = seckillId+"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 20);
    }

    public Seckill getById(long seckillId) {
//        Seckill seckill = (Seckill)redisTemplate.opsForHash().get(key, String.valueOf(seckillId));
//                redisTemplate.opsForHash().put(key,String.valueOf(seckillId),seckill.toString());
        String KEY = "seckill";
        String key = "seckill:"+seckillId;
        Seckill seckill = null;
//        Object data = redisTemplate.opsForValue().get(key);
        Object data = redisTemplate.opsForHash().get(KEY,key);

        if (data == null) {
            seckill = seckillDao.queryById(seckillId);
            System.out.println("from dao====="+seckill);
            if (seckill == null) {
                //数据库中无该条记录
            } else {
//                redisTemplate.opsForValue().set(key, JSON.toJSONString(seckill), 12, TimeUnit.HOURS);
                redisTemplate.opsForHash().put(KEY,key, JSON.toJSONString(seckill));
//                redisTemplate.delete(key);
                System.out.println("put in redis");
                return seckill;
            }
        }
//        seckill = JSON.toJavaObject(JSON.parseObject(data), Seckill.class);
        seckill = JSON.parseObject(data.toString(), Seckill.class);
//        redisTemplate.delete(key);
        System.out.println("from redis========"+seckill);
        return seckill;
    }

    public Exposer exportSeckillUrl(long seckillId) {
        //优化点，缓存优化
        //访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false,seckillId);
            }else {
                //放入redis
                redisDao.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        // time now
        Date date = new Date();
        if(date.getTime()<startTime.getTime() || date.getTime()>endTime.getTime()){
            return new Exposer(false,date.getTime(),startTime.getTime(),endTime.getTime());
        }

        return null;
    }

    /**保证事务方法执行时间尽可能短，
     * 不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部*/
    @Transactional
    public void ExecuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {

    }
}
