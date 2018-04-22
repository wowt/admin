package com.hongcheng.fruitmall.common.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.willsale.pojo.vo.WillSaleInfo;

@Component
public class MailSendCache extends AbstractCache {

    private static String MAIL_PUBLISH_QUEUE_KEY="mail_publish_queue_key"; //水果上线邮件队列

    private static String MAIL_ACTIVE_QUEUE_KEY="mail_active_queue_key"; //激活邮件队列

    private static String MAIL_FORGETPASS_QUEUE_KEY="mail_forgetpass_queue_key";//忘记密码队列

    /**
     * 将发布信息加入缓存队列
     * @param infos
     */
    public void pushPublishMailToQueue(List<WillSaleInfo> infos) {
        if(CollectionUtils.isEmpty(infos) ) {
            return;
        }
        infos.forEach(info -> {
            if (info.getUserInfo() == null) {
                return;
            }
            pushFromTail(MAIL_PUBLISH_QUEUE_KEY,info);
        });
    }

    public List<WillSaleInfo> popPublishMailFromQueue(Integer size) {
        List<WillSaleInfo> infos = new ArrayList<>();
        for (int i = 0;i<size; i++) {
            WillSaleInfo info = popFromHead(MAIL_PUBLISH_QUEUE_KEY, WillSaleInfo.class);
            if (info == null) {
                return infos;
            }
            infos.add(info);
        }
        return infos;
    }

    /**
     * 取出激活邮件对象
     * @return
     */
    public MailUserInfo popActiveMail() {
        return popFromHead(MAIL_ACTIVE_QUEUE_KEY, MailUserInfo.class);
    }

    /**
     * 取出忘记密码邮件对象
     * @return
     */
    public MailUserInfo popForgetPassMail() {
        return popFromHead(MAIL_FORGETPASS_QUEUE_KEY, MailUserInfo.class);
    }

}
