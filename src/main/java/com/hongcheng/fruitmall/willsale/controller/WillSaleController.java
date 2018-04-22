package com.hongcheng.fruitmall.willsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleVO;
import com.hongcheng.fruitmall.willsale.request.WillSaleRequest;
import com.hongcheng.fruitmall.willsale.service.WillSaleService;

@RestController
@RequestMapping("/admin/will-sale/v1")
public class WillSaleController {

    @Autowired
    private WillSaleService service;

    @GetMapping("/willList")
    public RestResponse<PageList<WillSaleVO>> getWillList(WillSaleRequest request) {
        return RestResponse.success(service.getListByQuery(request));
    }

    @DeleteMapping("/will/{id}")
    public RestResponse<Integer> deleteWill(@PathVariable Integer productId) {
        return RestResponse.success(service.expired(productId));
    }
}
