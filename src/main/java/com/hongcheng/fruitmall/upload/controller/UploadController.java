package com.hongcheng.fruitmall.upload.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.upload.FileUtil;

@RestController
@RequestMapping("/admin/img")
public class UploadController {

    @Value("${location}")
    private String basePath;

    private static String RESPONSEURL="http://60.205.218.101/static/img/";

    @PostMapping("/product")
    public RestResponse<String> uploadProductImg(@RequestParam MultipartFile file,HttpServletRequest request) throws Exception {
        String name = FileUtil.uploadFile(file, basePath + "products/");
        return RestResponse.success(RESPONSEURL+"products/"+name);
    }

    @PostMapping("/health")
    public RestResponse<String> uploadHealthImg(@RequestParam MultipartFile file) throws Exception {
        String name = FileUtil.uploadFile(file, basePath + "health/");
        return RestResponse.success(RESPONSEURL+"health/"+name);
    }
}
