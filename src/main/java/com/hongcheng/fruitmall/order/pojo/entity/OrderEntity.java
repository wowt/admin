package com.hongcheng.fruitmall.order.pojo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author wanghongcheng 2018/04/18
 * 订单实体类
 */
@Data
public class OrderEntity {

    private Integer orderId;

    private BigDecimal payMoney;

    private String payPath;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String state;

    private String remarks;

    private Integer userId;

    private String userAddress;
}
