package com.hongcheng.fruitmall.fruit.pojo.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SimpleFruitInfo {

    private Integer productId;

    private String productImg;

    private String title;

    private BigDecimal price;

    private Integer quality;
}
