package com.hongcheng.fruitmall.fruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.fruit.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.fruit.requst.FruitListQueryRequest;
import com.hongcheng.fruitmall.fruit.service.FruitService;

@RestController
@RequestMapping("/admin/fruit/v1")
public class FruitManageController {

    @Autowired
    FruitService service;

    @GetMapping("/fruits")
    public RestResponse<List<FruitEntity>> getFruitList(@RequestBody FruitListQueryRequest request) {
        return RestResponse.success(service.getFruitList(request));
    }

    @PostMapping("/fruit")
    public RestResponse<Integer> createFruit(@RequestBody FruitEntity entity) {
        return RestResponse.success(service.createFruit(entity));
    }

    @GetMapping("/fruit/{id}")
    public RestResponse<FruitEntity> getById(@PathVariable Integer id) {
        return RestResponse.success(service.getFruitById(id));
    }

    @PutMapping("/fruit/{id}")
    public RestResponse<Integer> editFruit(@RequestBody FruitEntity entity) {
        return RestResponse.success(service.update(entity));
    }

    @PatchMapping("/fruit/{id}/toPublish")
    public RestResponse<Integer> updateToPublish(@PathVariable Integer id) {
        return RestResponse.success(service.changeToPublish(id));
    }

    @PatchMapping("/fruit/{id}/toPrePublish")
    public RestResponse<Integer> updateToPrePublish(@PathVariable Integer id) {
        return RestResponse.success(service.expired(id));
    }

    @PatchMapping("/fruit/{id}/toPush")
    public RestResponse<Integer> updateToPush(@PathVariable Integer id) {
        return RestResponse.success(service.addPush(id));
    }

    @PatchMapping("/fruit/{id}/removePush")
    public RestResponse<Integer> removePush(@PathVariable Integer id) {
        return RestResponse.success(service.removePush(id));
    }
}
