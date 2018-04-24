package com.hongcheng.fruitmall.fruit.pojo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FruitEntity {

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 产品名字
     */
    private String productName;

    /**
     * 产地
     */
    private String productPlace;

    /**
     * 现在状态
     */
    private String productState;

    /**
     * 属于什么类型
     */
    private Integer productType;

    /**
     * 图片URL
     */
    private String productImg;

    /**
     * tips
     */
    private String tips;

    /**
     * 单价 /kg
     */
    private BigDecimal price;

    /**
     * 折扣价
     */
    private BigDecimal dealPrice;

    /**
     * 购买1kg可以获得多少积分
     */
    private BigDecimal score;

    /**
     * 开始销售时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startTime;

    /**
     * 止时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endTime;

    /**
     * 介绍
     */
    private String introduce;

    private String title;

}
