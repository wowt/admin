package com.hongcheng.fruitmall.mall.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.mall.dao.cache.FruitMallCache;
import com.hongcheng.fruitmall.mall.dao.mapper.FruitEntityMapper;
import com.hongcheng.fruitmall.mall.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.service.FruitMallService;

@Service
public class FruitMallServiceImpl implements FruitMallService {

    @Autowired
    private FruitEntityMapper fruitMapper;

    @Autowired
    private FruitMallCache cache;

    @Override
    public List<FruitEntity> getDayDealFruit() {
        List<FruitEntity> dayDeals = cache.getDayDealFromCache();
        if(CollectionUtils.isEmpty(dayDeals)) {
            dayDeals = fruitMapper.getDealFruits();
            cache.putDayDealToCache(dayDeals);
        }
        return dayDeals;
    }

    @Override
    public List<FruitEntity> getShopperPush() {
        List<FruitEntity> pushFruits = cache.getPushFruitsFromCache();
        if(CollectionUtils.isEmpty(pushFruits)) {
            pushFruits = fruitMapper.getPushFruits();
            cache.putPushFruitsToCache(pushFruits);
        }
        return pushFruits;
    }

    @Override
    public List<FruitEntity> getWeekSaleFruits() {
        return null;
    }

    @Override
    public List<FruitEntity> getWillOnSale() {
        List<FruitEntity> willSaleFruits = cache.getWillSaleFruitsFromCache();
        if(CollectionUtils.isEmpty(willSaleFruits)) {
            willSaleFruits = fruitMapper.getWillSaleFruits();
            cache.putWillSaleFruitsToCache(willSaleFruits);
        }
        return willSaleFruits;
    }
}
