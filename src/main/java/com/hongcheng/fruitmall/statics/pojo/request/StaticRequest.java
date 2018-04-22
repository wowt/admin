package com.hongcheng.fruitmall.statics.pojo.request;

import com.hongcheng.fruitmall.common.pojo.PageForm;

import lombok.Data;

@Data
public class StaticRequest extends PageForm {

    private String name;

    private Integer type;

    private String timeType;
}
