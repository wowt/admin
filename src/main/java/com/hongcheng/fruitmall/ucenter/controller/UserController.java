package com.hongcheng.fruitmall.ucenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hongcheng.fruitmall.common.codec.MD5;
import com.hongcheng.fruitmall.common.util.Response;
import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;
import com.hongcheng.fruitmall.ucenter.service.UserService;

@RestController
@RequestMapping("/userCenter/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response<String> login(LogInfoEntity logInfo) {
        Response<String> response = new Response<>();
        response.setResponseData(userService.login(logInfo.getEmail(), logInfo.getPassword()));
        return response;
    }

    @GetMapping("/logout")
    public Response<String> logout(Integer id) {
        userService.logout(id);
        return new Response<>();
    }

    @PostMapping("/register")
    public Response<String> register(LogInfoEntity logInfo) {
        Response<String> response = new Response<>();
        response.setResponseData(userService.register(logInfo));
        return response;
    }

    @GetMapping("/active")
    public Response<Boolean> confirm(String email, Integer code) {
        Response<Boolean> response = new Response<>();
        response.setResponseData(userService.confirm(email, code));
        return response;
    }


}
