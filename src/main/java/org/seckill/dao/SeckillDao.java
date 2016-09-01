package org.seckill.dao;

import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/4.
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckilled
     * @param killTime
     * @return
     */
    int reduceNumber(long seckilled, Date killTime);

    /**
     * 根据ID查询商品
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(int offet, int limit);

}
