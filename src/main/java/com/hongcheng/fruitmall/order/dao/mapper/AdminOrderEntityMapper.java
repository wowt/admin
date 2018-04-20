package com.hongcheng.fruitmall.order.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hongcheng.fruitmall.order.pojo.entity.OrderEntity;
import com.hongcheng.fruitmall.order.pojo.qo.AdminOrderQO;

/**
 * @author wanghongcheng 2018/04/18
 * admin订单管理mapper
 */
public interface AdminOrderEntityMapper {

    List<OrderEntity> getListByQuery(@Param("qo")AdminOrderQO qo);

    Integer getCountByQuery(@Param("qo") AdminOrderQO qo);

    Integer updateStateById(@Param("id") Integer id,@Param("state") String state);
}
