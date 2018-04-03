package com.hongcheng.fruitmall.common.util;

import lombok.Data;

/**
 * 通用返回请求
 * @param <T>
 */
@Data
public class Response<T> {

    private Integer code=200;

    private String msg;

    private T responseData;

    private Integer total;

    public Response<T> setData(T responseData) {
        this.responseData = responseData;
        return this;
    }

    public Response<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response<T> setCode(Integer code) {
        this.code = code;
        return this;
    }
}
