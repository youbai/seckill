package org.seckill.dto;

/**
 * Created by Administrator on 2016/6/11.
 * ajax请求返回类型，json
 */
public class SeckllResult<T> {
    private boolean success;
    private T data;
    private String error;

    public SeckllResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckllResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SeckllResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
