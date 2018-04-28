package com.hongcheng.fruitmall.statics.pojo.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StaticVO {

    private Integer productId;

    private String title;

    private String productState;

    private Integer totalCapacity;

    private BigDecimal totalMoney;

    private Integer orderNum;
}
