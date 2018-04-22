package com.hongcheng.fruitmall.willsale.pojo.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WillSaleVO {

    private Integer productId;

    private String productTitle;

    private String introduce;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    private Integer numOfPeople; //预约人数
}
