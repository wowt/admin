package com.hongcheng.fruitmall.ucenter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hongcheng.fruitmall.common.codec.MD5;
import com.hongcheng.fruitmall.common.util.Response;
import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;

@RestController
@RequestMapping("/userCenter")
public class UserController {

    @PostMapping("/login")
    public Response<String> login(LogInfoEntity logInfo) {

    }
}
