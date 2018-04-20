package com.hongcheng.fruitmall.order.requst;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.hongcheng.fruitmall.common.pojo.PageForm;

import lombok.Data;

@Data
public class AdminOrderRequest extends PageForm {

    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private Integer orderId;
}
