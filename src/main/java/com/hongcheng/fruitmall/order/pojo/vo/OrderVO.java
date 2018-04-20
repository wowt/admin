package com.hongcheng.fruitmall.order.pojo.vo;

import java.util.List;

import com.hongcheng.fruitmall.fruit.pojo.vo.SimpleFruitInfo;
import com.hongcheng.fruitmall.order.pojo.entity.OrderEntity;

import lombok.Data;

/**
 * 返回数据对象
 */
@Data
public class OrderVO extends OrderEntity {

    private List<SimpleFruitInfo> fruitsInfo;

    private Float pay;

    private String address;
}
