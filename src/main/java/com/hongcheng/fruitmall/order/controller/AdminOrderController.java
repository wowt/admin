package com.hongcheng.fruitmall.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.order.enums.OrderState;
import com.hongcheng.fruitmall.order.pojo.vo.OrderVO;
import com.hongcheng.fruitmall.order.requst.AdminOrderRequest;
import com.hongcheng.fruitmall.order.service.AdminOrderService;

@RestController
@RequestMapping("/admin/order-center/v1")
public class AdminOrderController {

    @Autowired
    private AdminOrderService service;

    @GetMapping("/orders/submitted")
    public RestResponse<PageList<OrderVO>> getSubmittedOrders(Boolean isFresh) {
        return RestResponse.success(service.getSubmitOrders(isFresh));
    }

    @GetMapping("/orders/WaitingSign")
    public RestResponse<PageList<OrderVO>> getWaitingSignOrders() {
        AdminOrderRequest request = new AdminOrderRequest();
        request.setState(OrderState.ACCEPTED.getValue());
        return RestResponse.success(service.orderList(request));
    }

    @GetMapping("/orders")
    public RestResponse<PageList<OrderVO>> getList(AdminOrderRequest request) {
        return RestResponse.success(service.orderList(request));
    }

    @PatchMapping("/order/{id}/accept")
    public RestResponse<Integer> acceptOrder(@PathVariable Integer id) {
        return RestResponse.success(service.acceptOrder(id));
    }

    @PatchMapping("/order/{id}/refuse")
    public RestResponse<Integer> refuseOrder(@PathVariable Integer id) {
        return RestResponse.success(service.refuse(id));
    }

    @PatchMapping("/order/{id}/sign")
    public RestResponse<Integer> signOrder(@PathVariable Integer id) {
        return RestResponse.success(service.sign(id));
    }

}
