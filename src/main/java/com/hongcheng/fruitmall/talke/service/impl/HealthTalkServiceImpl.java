package com.hongcheng.fruitmall.talke.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.pojo.PageForm;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.common.pojo.PageQuery;
import com.hongcheng.fruitmall.talke.dao.mapper.HealthTalkEntityMapper;
import com.hongcheng.fruitmall.talke.pojo.entity.HealthTalkEntity;
import com.hongcheng.fruitmall.talke.service.HealthTalkService;

@Service
public class HealthTalkServiceImpl implements HealthTalkService {

    @Autowired
    private HealthTalkEntityMapper mapper;

    @Override
    public PageList<HealthTalkEntity> getList(PageForm form) {
        PageQuery qo = createPageQuery(form);
        return new PageList<>(mapper.getTotal(qo),mapper.listAll(qo));
    }

    @Override
    public Integer deleteById(Integer id) {
        return mapper.delete(id);
    }

    private PageQuery createPageQuery(PageForm form) {
        PageQuery qo = BeanMapperFactory.getMapperFacade().map(form, PageQuery.class);
        qo.paging(form);
        return qo;
    }
}
