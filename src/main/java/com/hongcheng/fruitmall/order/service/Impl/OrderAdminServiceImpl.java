package com.hongcheng.fruitmall.order.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.order.dao.mapper.AdminOrderEntityMapper;
import com.hongcheng.fruitmall.order.enums.OrderState;
import com.hongcheng.fruitmall.order.pojo.entity.OrderEntity;
import com.hongcheng.fruitmall.order.pojo.qo.AdminOrderQO;
import com.hongcheng.fruitmall.order.requst.AdminOrderRequest;
import com.hongcheng.fruitmall.order.service.OrderAdminService;

/**
 * @author wanghongcheng 2018/04/18
 * 后台管理订单服务接口实现
 */
@Service
public class OrderAdminServiceImpl implements OrderAdminService {

    @Autowired
    private AdminOrderEntityMapper mapper;

    @Override
    public PageList<OrderEntity> orderList(AdminOrderRequest request) {
        AdminOrderQO qo = createQO(request);
        return new PageList<>(mapper.getCountByQuery(qo),mapper.getListByQuery(qo));
    }

    @Override
    public PageList<OrderEntity> getSubmitOrders() {
        return null;
    }

    @Override
    public Integer acceptOrder(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.ACCEPTED.getValue());
    }

    @Override
    public Integer refuse(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.REFUSED.getValue());
    }

    @Override
    public Integer dispatch(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.DISPATCHING.getValue());
    }

    @Override
    public Integer sign(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.SIGNED.getValue());
    }

    /**
     * 构造查询对象
     * @param request
     * @return
     */
    private AdminOrderQO createQO(AdminOrderRequest request) {
        AdminOrderQO qo = BeanMapperFactory.getMapperFacade().map(request, AdminOrderQO.class);
        qo.paging(request);
        return qo;
    }
}
