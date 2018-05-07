package com.hongcheng.fruitmall.fruit.dao.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.fruit.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;

@Component
public class FruitCache extends AbstractCache {

    private static final Integer TIMEOUT = 300;

    private static String FRUIT_DAYDEAL_KEY = "dayDeal_key";

    private static String FRUIT_PUBLISH_KEY ="publish_key";

    private static String FRUIT_SHOPPER_PUSH_KEY = "shopper_push_key"; //店长推荐

    private static String FRUIT_WEEK_HOT_KEY = "week_hot_key"; //本周热卖

    private static String FRUIT_Will_ON_SALE_KEY = "will_on_sale_key"; //预售

    /**
     * 获取今日促销的水果
     * @return
     */
    public List<FruitEntity> getDayDealFromCache(){
        return get(FRUIT_DAYDEAL_KEY, new TypeReference<List<FruitEntity>>() {});
    }

    public void putDayDealToCache(List<FruitEntity> fruits) {
        put(FRUIT_DAYDEAL_KEY, fruits, TIMEOUT);
    }

    /**
     * 获取店长推荐的水果
     * @return
     */
    public List<FruitEntity> getPushFruitsFromCache(){
        return get(FRUIT_SHOPPER_PUSH_KEY, new TypeReference<List<FruitEntity>>() {});
    }

    public void putPushFruitsToCache(List<FruitEntity> fruits) {
        put(FRUIT_SHOPPER_PUSH_KEY, fruits, TIMEOUT);
    }

    /**
     * 获取本周热卖的水果
     * @return
     */
    public List<FruitEntity> getWeekHotFruitsFromCache(){
        return get(FRUIT_WEEK_HOT_KEY, new TypeReference<List<FruitEntity>>() {});
    }

    public void putWeekHotFruitsToCache(List<FruitEntity> fruits) {
        put(FRUIT_WEEK_HOT_KEY, fruits, TIMEOUT);
    }

    /**
     * 获取预售水果
     * @return
     */
    public List<FruitEntity> getWillSaleFruitsFromCache(){
        return get(FRUIT_Will_ON_SALE_KEY, new TypeReference<List<FruitEntity>>() {});
    }

    public void putWillSaleFruitsToCache(List<FruitEntity> fruits) {
        put(FRUIT_Will_ON_SALE_KEY, fruits, TIMEOUT);
    }

    /**
     * 获取所有发布水果
     * @param
     */
    public List<FruitEntity> getPublishAll() {
        return get(FRUIT_PUBLISH_KEY,new TypeReference<List<FruitEntity>>() {});
    }

    public void putPublishAll(List<FruitEntity> fruits) {put(FRUIT_PUBLISH_KEY,fruits,TIMEOUT);}

    public void cleanDayDeal() {
        delete(FRUIT_DAYDEAL_KEY);
    }

    public void cleanWeekHot() {
        delete(FRUIT_WEEK_HOT_KEY);
    }

    public void cleanWillSale() {
        delete(FRUIT_Will_ON_SALE_KEY);
    }

    public void cleanPublish(){delete(FRUIT_PUBLISH_KEY);}

    public void cleanPush(){delete(FRUIT_SHOPPER_PUSH_KEY);}
}
