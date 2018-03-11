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

    private Long total;
}
