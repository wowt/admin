package com.hongcheng.fruitmall.order.dao.cache;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.order.pojo.vo.OrderVO;

@Component
public class AdminOrderCache extends AbstractCache {

    private static final String orderQueueKey = "order_queue_key";

    /**
     * 获取队列中的所有订单
     * @return
     */
    public List<OrderVO> getOrderListFromQueue() {
        List<OrderVO> orderVOS = new ArrayList<>();
        OrderVO vo = null;
        do {
            vo = popFromHead(orderQueueKey, OrderVO.class);
            if(vo != null) {
                orderVOS.add(vo);
            }
        }while (vo != null);

        return orderVOS;
    }
}
