package com.hongcheng.fruitmall.order.requst;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.hongcheng.fruitmall.common.pojo.PageForm;

import lombok.Data;

@Data
public class AdminOrderRequest extends PageForm {

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    private Integer orderId;

    private String state;
}
