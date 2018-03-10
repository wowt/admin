package com.hongcheng.fruitmall.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hongcheng.fruitmall.common.constants.ResponseConstant;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class RestExceptionHandler {

    public static String ERR_MSG = "服务端发生错误,请联系系统管理员处理!";

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object handleExceptionInternal(Exception ex) {

        logger.error("",ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ResponseConstant.TEXT_PLAIN_UTF_8));
        Map bodyMap = new HashMap();
        bodyMap.put("error", ex.getMessage());
        bodyMap.put("msg", ERR_MSG);
        return new ResponseEntity<Object>(bodyMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BizException.class)
    public Object handleExceptionInternal(BizException ex) {

        logger.error("",ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ResponseConstant.TEXT_PLAIN_UTF_8));
        Map bodyMap = new HashMap();
        bodyMap.put("error", ex.getMessage());
        bodyMap.put("msg", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
