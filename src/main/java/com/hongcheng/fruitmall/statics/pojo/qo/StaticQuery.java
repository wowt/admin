package com.hongcheng.fruitmall.statics.pojo.qo;

import java.time.LocalDateTime;

import com.hongcheng.fruitmall.common.pojo.PageQuery;

import lombok.Data;

@Data
public class StaticQuery extends PageQuery {

    private String name;

    private Integer type;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
