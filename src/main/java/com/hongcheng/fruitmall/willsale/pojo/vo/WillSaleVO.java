package com.hongcheng.fruitmall.willsale.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WillSaleVO {

    private Integer productId;

    private String title;

    private String productImg;

    private BigDecimal dealPrice;

    private String productState;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    private Integer numOfPeople; //预约人数
}
