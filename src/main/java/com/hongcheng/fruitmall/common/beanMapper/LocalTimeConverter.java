package com.hongcheng.fruitmall.common.beanMapper;

import java.time.LocalTime;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
    @Override
    public LocalTime convertTo(LocalTime source, Type<LocalTime> destinationType) {
        return LocalTime.from(source);
    }

    @Override
    public LocalTime convertFrom(LocalTime source, Type<LocalTime> destinationType) {
        return LocalTime.from(source);
    }
}