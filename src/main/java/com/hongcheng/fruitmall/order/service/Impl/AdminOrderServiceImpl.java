package com.hongcheng.fruitmall.order.service.Impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.order.dao.cache.AdminOrderCache;
import com.hongcheng.fruitmall.order.dao.mapper.AdminOrderEntityMapper;
import com.hongcheng.fruitmall.order.enums.OrderState;
import com.hongcheng.fruitmall.order.pojo.qo.AdminOrderQO;
import com.hongcheng.fruitmall.order.pojo.vo.OrderVO;
import com.hongcheng.fruitmall.order.requst.AdminOrderRequest;
import com.hongcheng.fruitmall.order.service.AdminOrderService;

/**
 * @author wanghongcheng 2018/04/18
 * 后台管理订单服务接口实现
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private AdminOrderEntityMapper mapper;

    @Autowired
    private AdminOrderCache orderCache;

    @Override
    public PageList<OrderVO> orderList(AdminOrderRequest request) {
        AdminOrderQO qo = createQO(request);
        return new PageList<>(mapper.getCountByQuery(qo),mapper.getListByQuery(qo));
    }

    @Override
    public PageList<OrderVO> getSubmitOrders(Boolean isFresh) {
        PageList<OrderVO> result = new PageList<>();
        //刷新缓存从数据库中取数据
        if(isFresh) {
            AdminOrderQO qo = new AdminOrderQO();
            qo.setState(OrderState.SUBMITTED.getValue());
            List<OrderVO> list = mapper.getListByQuery(qo);
            result.setTotal(list.size());
            result.setData(list);
            orderCache.cleanOrderQueue();
        } else {
            //从缓存队列中取出所有数据
            List<OrderVO> vos = orderCache.getOrderListFromQueue();
            result.setTotal(vos.size());
            result.setData(vos);
        }
        return result;
    }

    @Override
    public Integer acceptOrder(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.ACCEPTED.getValue(),null);
    }

    @Override
    public Integer refuse(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.REFUSED.getValue(),null);
    }

    @Override
    public Integer dispatch(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.DISPATCHING.getValue(),null);
    }

    @Override
    public Integer sign(Integer orderId) {
        return mapper.updateStateById(orderId, OrderState.SIGNED.getValue(), LocalDateTime.now());
    }

    /**
     * 构造查询对象
     * @param request
     * @return
     */
    private AdminOrderQO createQO(AdminOrderRequest request) {
        AdminOrderQO qo = BeanMapperFactory.getMapperFacade().map(request, AdminOrderQO.class);
        if(qo.getEndTime() != null) {
            qo.setEndTime(qo.getEndTime().with(LocalTime.MAX));
        }
        qo.paging(request);
        return qo;
    }
}
