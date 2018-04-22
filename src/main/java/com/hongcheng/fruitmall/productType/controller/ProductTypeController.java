package com.hongcheng.fruitmall.productType.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.productType.pojo.ProductTypeEntity;
import com.hongcheng.fruitmall.productType.service.ProductTypeService;

@RestController
@RequestMapping("/admin/product-type/v1")
public class ProductTypeController {

    @Autowired
    private ProductTypeService service;

    @GetMapping("/types")
    public RestResponse<PageList<ProductTypeEntity>> getList() {
        return RestResponse.success(service.getAll());
    }

    @DeleteMapping("/type/{id}")
    public RestResponse<Integer> deleteById(@PathVariable Integer id) {
        return RestResponse.success(service.deleteById(id));
    }

    @PostMapping("/type")
    public RestResponse<Integer> create(@RequestBody ProductTypeEntity entity) {
        return RestResponse.success(service.insert(entity));
    }
}
