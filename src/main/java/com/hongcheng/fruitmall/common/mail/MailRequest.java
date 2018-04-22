package com.hongcheng.fruitmall.common.mail;

import java.util.Date;

import lombok.Data;

@Data
public class MailRequest {

    private String from;
    private String to;
    private String subject;
    private String message;
    private Date requestTime = new Date();
}
