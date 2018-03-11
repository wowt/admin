package com.hongcheng.fruitmall.ucenter.service;

import com.hongcheng.fruitmall.ucenter.pojo.MailRequest;

public interface MailgunService {

    /**
     * 发送邮件
     * @param request
     */
    void sendActiveMail(MailRequest request);
}
