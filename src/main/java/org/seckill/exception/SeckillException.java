package org.seckill.exception;

/**
 * 秒杀异常
 * Created by Administrator on 2016/6/5.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
