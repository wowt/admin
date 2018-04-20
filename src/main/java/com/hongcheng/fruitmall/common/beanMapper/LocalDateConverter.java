package com.hongcheng.fruitmall.common.beanMapper;

import java.time.LocalDate;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
    @Override
    public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType) {
        return LocalDate.from(source);
    }

    @Override
    public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType) {
        return LocalDate.from(source);
    }
}