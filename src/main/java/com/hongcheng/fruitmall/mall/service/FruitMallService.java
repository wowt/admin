package com.hongcheng.fruitmall.mall.service;

import java.util.List;

import com.hongcheng.fruitmall.mall.entity.FruitEntity;

public interface FruitMallService {

    /**
     * 获取今日促销的水果列表
     * @return
     */
    List<FruitEntity> getDayDealFruit();

    /**
     * 店长推荐
     * @return
     */
    List<FruitEntity> getShopperPush();

    /**
     * 本周热卖
     * @return
     */
    List<FruitEntity> getWeekSaleFruits();

    /**
     * 即将出售
     * @return
     */
    List<FruitEntity> getWillOnSale();
}
