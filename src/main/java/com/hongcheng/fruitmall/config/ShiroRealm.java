package com.hongcheng.fruitmall.config;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongcheng.fruitmall.common.codec.MD5;
import com.hongcheng.fruitmall.ucenter.entity.LogInfoEntity;
import com.hongcheng.fruitmall.ucenter.service.UserService;

public class ShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        log.info("验证当前Subject时获取到token为：" + token.toString());
        LogInfoEntity entity = userService.getLoginByEmail(token.getUsername());

        boolean verify = Optional.of(entity)
                .map(LogInfoEntity::getPassword)
                .equals(MD5.getMD5(token.getPassword().toString() + entity.getRegistTime()));

        if(verify) { //更新最后登录时间
            LogInfoEntity info = new LogInfoEntity();
            info.setLastTime(LocalDateTime.now());
            userService.updateLogin(info);
        }

        return verify ? new SimpleAuthenticationInfo(entity, entity.getPassword(), getName()) : null;
    }
}
