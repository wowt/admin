package com.hongcheng.fruitmall.ucenter.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hongcheng.fruitmall.ucenter.dao.mapper.LogInfoEntityMapper;
import com.hongcheng.fruitmall.ucenter.dao.mapper.UserEntityMapper;
import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;
import com.hongcheng.fruitmall.ucenter.entity.UserEntity;
import com.hongcheng.fruitmall.ucenter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private LogInfoEntityMapper loginMapper;

    @Override
    public LogInfoEntity getLoginByEmail(String email) {
        return loginMapper.getByEmail(email);
    }

    @Override
    public void logout(Integer userId) {
        SecurityUtils.getSubject().logout();
    }

    @Override
    public String getUserInfo(Integer userId) {
        return null;
    }

    @Override
    public String login(String userName, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        boolean login = SecurityUtils.getSecurityManager().login(subject, token).isAuthenticated();
        return login ? "success" : "failed";
    }

    @Override
    public String register(LogInfoEntity entity) {
        loginMapper.insert(entity);
        // todo 发送激活邮件

        return null;
    }

    @Override
    public void updateLogin(LogInfoEntity entity) {
        loginMapper.update(entity);
    }
}
