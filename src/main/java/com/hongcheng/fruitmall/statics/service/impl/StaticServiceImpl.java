package com.hongcheng.fruitmall.statics.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.statics.dao.mapper.StaticMapper;
import com.hongcheng.fruitmall.statics.enums.StaticTimeType;
import com.hongcheng.fruitmall.statics.pojo.qo.StaticQuery;
import com.hongcheng.fruitmall.statics.pojo.request.StaticRequest;
import com.hongcheng.fruitmall.statics.pojo.vo.StaticVO;
import com.hongcheng.fruitmall.statics.service.StaticService;

@Service
public class StaticServiceImpl implements StaticService {

    @Autowired
    StaticMapper mapper;

    @Override
    public PageList<StaticVO> getStaticByQuery(StaticRequest request) {
        StaticQuery query = createQuery(request);
        return new PageList<>(mapper.countStatic(query), mapper.getStaticByQuery(query));
    }

    private StaticQuery createQuery(StaticRequest request) {
        StaticQuery qo = BeanMapperFactory.getMapperFacade().map(request, StaticQuery.class);
        qo.paging(request);
        StaticTimeType timeType = StaticTimeType.valueOf(request.getTimeType());
        switch (timeType) {
            case WEEK:
                qo.setStartTime(LocalDateTime.now().with(DayOfWeek.MONDAY));
                qo.setEndTime(LocalDateTime.now());
                break;
            case MONTH:
                qo.setStartTime(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).with(LocalDateTime.MIN));
                qo.setEndTime(LocalDateTime.now());
                break;
            case ALL:
                break;
        }
        return qo;
    }
}
