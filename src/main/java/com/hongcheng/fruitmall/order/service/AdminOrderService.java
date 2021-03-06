package com.hongcheng.fruitmall.order.service;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.order.pojo.entity.OrderEntity;
import com.hongcheng.fruitmall.order.pojo.vo.OrderVO;
import com.hongcheng.fruitmall.order.requst.AdminOrderRequest;

/**
 * @author wanghongcheng 2018/04/18
 * 后台管理订单服务接口
 */
public interface AdminOrderService {

    /**
     * 获取order列表
     * @param request
     * @return
     */
    PageList<OrderVO> orderList(AdminOrderRequest request);

    /**
     * 获取刚提交的订单列,不分页
     * @return
     */
    PageList<OrderVO> getSubmitOrders(Boolean isFresh);

    /**
     * 接受订单
     * @param orderId
     * @return
     */
    Integer acceptOrder(Integer orderId);

    /**
     * 拒绝订单
     * @param orderId
     * @return
     */
    Integer refuse(Integer orderId);

    /**
     * 发货
     * @param orderId
     * @return
     */
    Integer dispatch(Integer orderId);

    /**
     * 管理员手动签收
     * @param orderId
     * @return
     */
    Integer sign(Integer orderId);
}
