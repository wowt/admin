package com.hongcheng.fruitmall.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hongcheng.fruitmall.common.util.Response;
import com.hongcheng.fruitmall.mall.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.service.FruitMallService;

@RestController
@RequestMapping("/mall/v1/fruits")
public class FruitMallController {

    @Autowired
    private FruitMallService mallService;

    @GetMapping("/dayDeals")
    public Response<List<FruitEntity>> getDayDeals() {
        List<FruitEntity> dayDealFruit = mallService.getDayDealFruit();

        return new Response<List<FruitEntity>>()
                .setCode(200)
                .setMsg("success")
                .setTotal(dayDealFruit.size())
                .setData(dayDealFruit);
    }

    @GetMapping("/shopper-push")
    public Response<List<FruitEntity>> getPushFruits() {
        List<FruitEntity> shopperPush = mallService.getShopperPush();

        return new Response<List<FruitEntity>>()
                .setCode(200)
                .setMsg("success")
                .setTotal(shopperPush.size())
                .setData(shopperPush);
    }
}
