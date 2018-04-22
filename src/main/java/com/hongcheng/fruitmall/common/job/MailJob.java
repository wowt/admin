package com.hongcheng.fruitmall.common.job;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hongcheng.fruitmall.common.mail.*;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;

@Component
public class MailJob {

    @Autowired
    private MailSendCache cache;

    @Autowired
    private MailService mailService;

    private static final Integer SIZE=100; //每次发送100个邮件

    /**
     * 每隔1分钟读取邮件缓存队列
     */
    @Scheduled(fixedDelay = 1*60*1000)
    public void sendPublishMail() {
        List<WillSaleInfo> infos = cache.popPublishMailFromQueue(SIZE);
        if (CollectionUtils.isNotEmpty(infos)) {
            infos.stream().forEach(info -> {
                MailRequest publishMail = MailBody.getPublishMail(info.getUserInfo().getEmail(), info.getUserInfo().getNick(),
                        info.getFruitInfo().getTitle(), info.getFruitInfo().getProductImg());
                mailService.sendMail(publishMail);
            });
        }
    }

    /**
     * 每隔1秒读取激活邮件队列
     */
    @Scheduled(fixedDelay = 1000)
    public void sendActiveMail() {
        MailUserInfo info = cache.popActiveMail();
        if(info != null) {
            MailRequest activeMail = MailBody.getActiveMail(info.getUserName(), info.getEmail(), info.getCode());
            mailService.sendMail(activeMail);
        }
    }

    /**
     * 每隔1秒读取忘记密码邮件队列
     */
    @Scheduled(fixedDelay = 1000)
    public void sendForgetMail() {
        MailUserInfo info = cache.popForgetPassMail();
        if(info != null) {
            MailRequest forGetPassMail = MailBody.getForgetPasswordMail(info.getEmail(),info.getUserName(),info.getCode());
            mailService.sendMail(forGetPassMail);
        }
    }
}
