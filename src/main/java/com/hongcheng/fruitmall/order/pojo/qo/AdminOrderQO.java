package com.hongcheng.fruitmall.order.pojo.qo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.hongcheng.fruitmall.common.pojo.PageQuery;

import lombok.Data;

/**
 * @author wanghongcheng 2018/04/18
 * 标准AdminOrder查询对象
 */
@Data
public class AdminOrderQO extends PageQuery {

    private String email;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer orderId;
}
