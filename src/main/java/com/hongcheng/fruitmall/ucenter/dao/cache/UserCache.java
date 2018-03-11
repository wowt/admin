package com.hongcheng.fruitmall.ucenter.dao.cache;

import org.springframework.stereotype.Component;

import com.hongcheng.fruitmall.common.cache.AbstractCache;

@Component
public class UserCache extends AbstractCache {

    //激活邮件验证码 只保存一天
    private static final Integer REGISTERCODETIMEOUT = 24*60*60;

    /**
     * 从缓存中获取激活码
     * @param key LoginId
     * @return 激活码
     */
    public Integer getRegisterCode(String key) {
        return get(key, Integer.class);
    }

    /**
     * 将激活码放入缓存
     * @param key loginId
     * @param code
     */
    public void putRegisterCode(String key, Integer code) {
        put(key, code, REGISTERCODETIMEOUT);
    }

}
