package com.hongcheng.fruitmall.common.util.excel.converter;

/**
 * Convert the value of type S to type T.
 * Created by wanghongcheng on 2018/04/20.
 *
 * @author wanghongcheng
 */
public interface Converter<S, T> {
    /**
     * @param source
     * @return
     */
    T convert(S source);
}
