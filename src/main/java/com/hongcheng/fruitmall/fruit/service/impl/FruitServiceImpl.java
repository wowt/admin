package com.hongcheng.fruitmall.fruit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.constants.FruitState;
import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.fruit.dao.cache.FruitCache;
import com.hongcheng.fruitmall.fruit.dao.mapper.FruitEntityMapper;
import com.hongcheng.fruitmall.fruit.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.fruit.pojo.qo.FruitQuery;
import com.hongcheng.fruitmall.fruit.requst.FruitListQueryRequest;
import com.hongcheng.fruitmall.fruit.service.FruitService;

@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    FruitEntityMapper mapper;

    @Autowired
    FruitCache cache;

    @Override
    public List<FruitEntity> getFruitList(FruitListQueryRequest request) {
        return mapper.getListByQO(createQO(request));
    }

    @Override
    public Integer createFruit(FruitEntity entity) {
        entity.setProductState(FruitState.PREPUBLISH.getValue());
        mapper.insert(entity);
        return entity.getProductId();
    }

    @Override
    public FruitEntity getFruitById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public Integer changeToPublish(Integer id) {
        mapper.updateState(id,FruitState.PUBLISH.getValue());
        cache.cleanPublish();
        return id;
    }

    @Override
    public Integer expired(Integer id) {
        mapper.updateState(id,FruitState.PREPUBLISH.getValue());
        cleanAllCache();
        return id;
    }

    @Override
    public Integer update(FruitEntity entity) {
        mapper.update(entity);
        cleanAllCache();
        return entity.getProductId();
    }

    @Override
    public Integer addPush(Integer id) {
        FruitEntity entity = mapper.selectById(id);
        String state = entity.getProductState();
        state += (","+FruitState.PUSH.getValue());
        mapper.updateState( id, state);
        cache.cleanPush();
        return entity.getProductId();
    }

    @Override
    public Integer removePush(Integer id) {
        FruitEntity entity = mapper.selectById(id);
        String state = entity.getProductState();
        String replace = state.replace(",push", "");
        mapper.updateState(id,replace);
        return id;
    }

    /**
     * 创建查询对象
     * @param request
     * @return
     */
    private FruitQuery createQO(FruitListQueryRequest request) {
        FruitQuery qo = BeanMapperFactory.getMapperFacade().map(request, FruitQuery.class);
        qo.paging(request);
        return qo;
    }

    /**
     * 清理所有缓存
     */
    private void cleanAllCache() {
        cache.cleanPublish();
        cache.cleanDayDeal();
        cache.cleanWeekHot();
        cache.cleanWillSale();
        cache.cleanPush();
    }
}
