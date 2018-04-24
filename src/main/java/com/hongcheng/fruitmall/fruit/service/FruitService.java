package com.hongcheng.fruitmall.fruit.service;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.fruit.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.fruit.requst.FruitListQueryRequest;

/**
 * @author wanghongcheng 2018/04/14
 * fruit相关服务
 */
public interface FruitService {

    /**
     * 获取水果列表
     * @param request
     * @return
     */
    PageList<FruitEntity> getFruitList(FruitListQueryRequest request);


    /**
     * 创建水果
     * @param entity
     * @return
     */
    Integer createFruit(FruitEntity entity);

    /**
     * 获取一个
     * @param id
     * @return
     */
    FruitEntity getFruitById(Integer id);

    /**
     * 发布上市
     * @param id
     * @return
     */
    Integer changeToPublish(Integer id);

    /**
     * 下架
     * @param id
     * @return
     */
    Integer expired(Integer id);

    /**
     * 编辑
     * @param entity
     * @return
     */
    Integer update(FruitEntity entity);

    /**
     * 店长推荐
     * @param id
     * @return
     */
    Integer addPush(Integer id);

    /**
     * 移除推荐
     * @param id
     * @return
     */
    Integer removePush(Integer id);

    /**
     * 加入预售（该水果状态只能为prepublish）
     * @param id
     * @return
     */
    Integer addWillSale(Integer id);

    /**
     * 取消预售
     * @param id
     * @return
     */
    Integer removeWill(Integer id);
}
