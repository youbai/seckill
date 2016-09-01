package org.seckill.dao;

import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;

/**
 * Created by Administrator on 2016/6/4.
 */
public interface SuccessKilledDao {
    /**
     * 查询购买明细
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKill(long seckillId, long userPhone);

    /**
     * 根据商品id查询秒杀成功记录
     * @param seckillId
     * @return
     */
    SuccessKilled queryBySeckill(long seckillId);

}
