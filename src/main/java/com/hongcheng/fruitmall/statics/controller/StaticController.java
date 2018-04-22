package com.hongcheng.fruitmall.statics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.statics.pojo.request.StaticRequest;
import com.hongcheng.fruitmall.statics.pojo.vo.StaticVO;
import com.hongcheng.fruitmall.statics.service.StaticService;

@RestController
@RequestMapping("/admin/static-sale/v1")
public class StaticController {

    @Autowired
    private StaticService service;

    @GetMapping("/static")
    public RestResponse<PageList<StaticVO>> getWeekStatic(StaticRequest request) {
        return RestResponse.success(service.getStaticByQuery(request));
    }

}
