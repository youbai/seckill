package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    /**
     * 输出秒杀接口地址
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    void ExecuteSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,SeckillCloseException,RepeatKillException;
}
