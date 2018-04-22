package com.hongcheng.fruitmall.talke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageForm;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.talke.pojo.entity.HealthTalkEntity;
import com.hongcheng.fruitmall.talke.service.HealthTalkService;

@RestController
@RequestMapping("/admin/health-talk/v1")
public class HealthTalkController {

    @Autowired
    private HealthTalkService service;

    @GetMapping("/list")
    public RestResponse<PageList<HealthTalkEntity>> getList(PageForm form) {
        return RestResponse.success(service.getList(form));
    }

    @DeleteMapping("/{id}")
    public RestResponse<Integer> delete(@PathVariable Integer id) {
        return RestResponse.success(service.deleteById(id));
    }
}
