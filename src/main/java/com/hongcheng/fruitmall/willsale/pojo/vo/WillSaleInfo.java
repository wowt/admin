package com.hongcheng.fruitmall.willsale.pojo.vo;

import com.hongcheng.fruitmall.fruit.pojo.vo.SimpleFruitInfo;
import com.hongcheng.fruitmall.common.pojo.SimpleUserInfo;
import com.hongcheng.fruitmall.willsale.pojo.entity.WillSaleEntity;

import lombok.Data;

@Data
public class WillSaleInfo extends WillSaleEntity {

    private SimpleFruitInfo fruitInfo;

    private SimpleUserInfo userInfo;
}
