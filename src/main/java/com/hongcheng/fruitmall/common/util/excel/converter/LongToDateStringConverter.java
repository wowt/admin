package com.hongcheng.fruitmall.common.util.excel.converter;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by wanghongcheng on 2018/04/20.
 */
public class LongToDateStringConverter implements Converter<Long, String> {
    private String pattern;
    private TimeZone timeZone;

    public LongToDateStringConverter(String pattern) {
        this.pattern = pattern;
        this.timeZone = TimeZone.getDefault();
    }

    public LongToDateStringConverter(String pattern, TimeZone timeZone) {
        this.pattern = pattern;
        this.timeZone = timeZone;
    }

    @Override
    public String convert(Long source) {
        if (null == source) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(timeZone);
        return format.format(source);
    }
}
