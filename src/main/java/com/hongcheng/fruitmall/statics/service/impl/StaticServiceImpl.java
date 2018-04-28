package com.hongcheng.fruitmall.statics.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        Integer total = mapper.countStatic(query);
        List<StaticVO> list = mapper.getStaticByQuery(query);
        return new PageList<>(total,list);
    }

    private StaticQuery createQuery(StaticRequest request) {
        StaticQuery qo = BeanMapperFactory.getMapperFacade().map(request, StaticQuery.class);
        qo.paging(request);
        StaticTimeType timeType = StaticTimeType.valueOf(request.getTimeType());
        switch (timeType) {
            case WEEK:
                qo.setStartTime(LocalDateTime.now().with(DayOfWeek.MONDAY)
                        .with(LocalTime.MIN));
                qo.setEndTime(LocalDateTime.now());
                break;
            case MONTH:
                qo.setStartTime(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())
                        .with(LocalTime.MIN));
                qo.setEndTime(LocalDateTime.now());
                break;
            case ALL:
                break;
        }
        return qo;
    }
}
