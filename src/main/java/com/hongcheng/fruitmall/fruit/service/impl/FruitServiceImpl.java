package com.hongcheng.fruitmall.fruit.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.constants.FruitState;
import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.mail.MailSendCache;
import com.hongcheng.fruitmall.fruit.dao.cache.FruitCache;
import com.hongcheng.fruitmall.fruit.dao.mapper.FruitEntityMapper;
import com.hongcheng.fruitmall.fruit.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.fruit.pojo.qo.FruitQuery;
import com.hongcheng.fruitmall.fruit.requst.FruitListQueryRequest;
import com.hongcheng.fruitmall.fruit.service.FruitService;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;
import com.hongcheng.fruitmall.willsale.service.WillSaleService;

@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitEntityMapper mapper;

    @Autowired
    private FruitCache cache;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private MailSendCache mailSendCache;

    @Autowired
    private WillSaleService willSaleService;

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
        //查看是否为预售水果
        FruitEntity fruitEntity = mapper.selectById(id);
        if (fruitEntity.getProductState().contains(FruitState.WILL.getValue())) {
            taskExecutor.execute(()->publishWill(id));
        }
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
        cache.cleanPush();
        return id;
    }

    @Override
    public Integer addWillSale(Integer id) {
        FruitEntity entity = mapper.selectById(id);
        String state = entity.getProductState();
        state += (","+FruitState.WILL.getValue());
        mapper.updateState(id,state);
        cache.cleanWillSale();
        return entity.getProductId();
    }

    @Override
    public Integer removeWill(Integer id) {
        FruitEntity entity = mapper.selectById(id);
        String state = entity.getProductState();
        String replace = state.replace(",will", "");
        mapper.updateState(id,replace);
        cache.cleanWillSale();
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
     * 将预约的顾客放入缓存队列，
     * 定时任务会读取缓存并发送提示邮件
     */
    private void publishWill(Integer id) {
        List<WillSaleInfo> infos = willSaleService.getInfoByProductId(id);
        mailSendCache.pushPublishMailToQueue(infos);
        willSaleService.deleteByProductId(id);
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
