package com.hongcheng.fruitmall.ucenter.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.common.codec.MD5;
import com.hongcheng.fruitmall.mall.dao.mapper.CartEntityMapper;
import com.hongcheng.fruitmall.mall.dao.mapper.UserCollectEntityMapper;
import com.hongcheng.fruitmall.ucenter.dao.cache.UserCache;
import com.hongcheng.fruitmall.ucenter.dao.mapper.LogInfoEntityMapper;
import com.hongcheng.fruitmall.ucenter.dao.mapper.UserEntityMapper;
import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;
import com.hongcheng.fruitmall.ucenter.entity.UserEntity;
import com.hongcheng.fruitmall.ucenter.enums.UserState;
import com.hongcheng.fruitmall.ucenter.pojo.MailBody;
import com.hongcheng.fruitmall.ucenter.pojo.MailRequest;
import com.hongcheng.fruitmall.ucenter.service.MailgunService;
import com.hongcheng.fruitmall.ucenter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userMapper;
    @Autowired
    private LogInfoEntityMapper loginMapper;
    @Autowired
    CartEntityMapper cartMapper;
    @Autowired
    UserCollectEntityMapper collectMapper;
    @Autowired
    private UserCache userCache;
    @Autowired
    private MailgunService mailService;

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
    public String login(String email, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        boolean login = SecurityUtils.getSecurityManager().login(subject, token).isAuthenticated();
        return login ? "success" : "failed";
    }

    @Override
    public String register(LogInfoEntity entity) {
        entity.setRegistTime(LocalDateTime.now());
        //生成随机验证码并存入缓存
        Integer registerCode = (int)(Math.random()*1000000);
        userCache.putRegisterCode(entity.getEmail(), registerCode);
        //密码加密存储
        entity.setPassword(MD5.getMD5(entity.getPassword()+entity.getRegistTime()));
        // 发送激活邮件 先生成邮件模型
        MailRequest mail = MailBody.getActiveMail(entity.getUserName(), entity.getEmail(), registerCode);
        loginMapper.insert(entity);
        mailService.sendActiveMail(mail);
        return "success";
    }

    @Override
    public void updateLogin(LogInfoEntity entity) {
        loginMapper.update(entity);
    }

    @Override
    public Boolean confirm(String email, Integer code) {
        boolean equals = Optional.of(code)
                .equals(userCache.getRegisterCode(email));
        //验证成果就创建一个用户
        if(equals) {
            //生成user
            UserEntity user = new UserEntity(email);
            userMapper.insert(user);
            //更新login
            LogInfoEntity info = new LogInfoEntity();
            info.setUserId(user.getUserId());
            info.setState(UserState.ACTIVE.getValue());
            loginMapper.update(info);
            userCache.delete(email);
        }
        return equals;
    }
}
