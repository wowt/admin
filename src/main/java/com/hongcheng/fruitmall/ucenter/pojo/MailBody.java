package com.hongcheng.fruitmall.ucenter.pojo;

import java.util.Date;

import com.hongcheng.fruitmall.common.constants.SiteConstant;

public class MailBody {

    public static MailRequest getActiveMail(String userName ,String email, Integer registerCode) {
        MailRequest mail = new MailRequest();

        String link=SiteConstant.SITE_URL+"/active/"+email+"/"+registerCode;
        String subject = "账号激活";
        String text = "<style>p{color:#555;font-size:16px; line-height:20px;}.sayhi{font-weight:bold;}p.fcol_999{color:#999;}p a{font:16px arial; color:#003399;}</style>";
        text += "<a href='" + SiteConstant.SITE_URL + "'><img src='" + SiteConstant.SITE_ICO + "' width='179' height='80' /></a>";
        text += "<p class='sayhi'>您好，" + userName + "，欢迎成为 fruitMall 用户。</p>";
        text += "<p>请点击下面的链接对您的邮箱进行验证：</p>";
        text += "<p><a href='" + link + "' target='_blank'>" + link + "</a></p>";
        text += "<p class='fcol_999'>(此链接24小时内有效，超时需要重新获取验证邮件)</p>";
        text += "<p>如果该链接无法点击，请将其复制粘贴到您的浏览器地址栏中访问。</p>";
        text += "<p>如此邮件非您本人操作请忽略。</p>";
        text += "<p>祝使用愉快！</p>";

        mail.setSubject(subject);
        mail.setTo(email);
        mail.setMessage(text);
        mail.setRequestTime(new Date());
        return mail;
    }
}
