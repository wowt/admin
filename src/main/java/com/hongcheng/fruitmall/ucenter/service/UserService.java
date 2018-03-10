package com.hongcheng.fruitmall.ucenter.service;

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
     * @param userName
     * @param password
     * @return
     */
    String login(String userName, String password);

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
}
