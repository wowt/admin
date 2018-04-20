package com.hongcheng.fruitmall.fruit.requst;

import com.hongcheng.fruitmall.common.pojo.PageForm;

import lombok.Data;

@Data
public class FruitListQueryRequest extends PageForm {

    private Integer productType;

    private String keyWord;

    private String state;
}
