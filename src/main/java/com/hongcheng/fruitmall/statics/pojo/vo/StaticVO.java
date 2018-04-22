package com.hongcheng.fruitmall.statics.pojo.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StaticVO {

    private Integer productId;

    private String productTitle;

    private Integer totalCapacity;

    private BigDecimal totalMoney;
}
