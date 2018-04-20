package com.hongcheng.fruitmall.order.dao.cache;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.order.pojo.entity.OrderEntity;

@Component
public class AdminOrderCache extends AbstractCache {

    private static final String orderQueueKey = "order_queue_key";

    /**
     * 获取队列中的所有订单
     * @return
     */
    public List<OrderEntity> getOrderListFromQueue() {
        List<OrderEntity> entityList = new ArrayList<>();
        OrderEntity entity = null;
        do {
            entity = popFromHead(orderQueueKey, OrderEntity.class);
            if(entity != null) {
                entityList.add(entity);
            }
        }while (entity != null);

        return entityList;
    }
}
