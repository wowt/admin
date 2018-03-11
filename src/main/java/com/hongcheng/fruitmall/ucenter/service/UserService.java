package com.hongcheng.fruitmall.ucenter.service;

import org.omg.PortableInterceptor.INACTIVE;

import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;

public interface UserService {

    /**
     * 验证登录
     * @param email
     * @return LoginEntity
     */
    LogInfoEntity getLoginByEmail(String email);

    /**
     * 用户注销
     * @return success
     */
    void logout(Integer userId);

    /**
     * 获取用户信息详情
     * @param userId
     * @return
     */
    String getUserInfo(Integer userId);

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    String login(String email, String password);

    /**
     * 注册
     * @param entity
     */
    String register(LogInfoEntity entity);

    /**
     * 更新登录相关
     * @param entity
     */
    void updateLogin(LogInfoEntity entity);

    /**
     * 激活注册邮件连接
     * @param email
     * @param code
     * @return
     */
    Boolean confirm(String email, Integer code);
}
