package com.hongcheng.fruitmall.ucenter.service.impl;

import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongcheng.fruitmall.ucenter.pojo.MailRequest;
import com.hongcheng.fruitmall.ucenter.service.MailgunService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class MailgunServiceImpl implements MailgunService {

    @Value("${mailgun.apikey}")
    private String mailgun_apikey;

    @Value("${mailgun.url}")
    private String mailgun_url;

    @Value("${mailgun.user}")
    private String user;

    @Override
    public void sendActiveMail(MailRequest mail) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgun_apikey));

        String[] tos = mail.getTo().split(";");
        for (int i = 0; i < tos.length; i++) {
            if ("".equals(tos[i])) {
                continue;
            }
            WebResource webResource = client.resource(mailgun_url);
            MultivaluedMapImpl formData = new MultivaluedMapImpl();
            formData.add("from", user);
            formData.add("to", tos[i]);
            formData.add("subject", mail.getSubject());
            formData.add("html", mail.getMessage());
            webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        }
    }


//    public static void main(String[] args) {
//        MailgunService service = new MailgunServiceImpl();
//        MailRequest mail = new MailRequest();
//        mail.setFrom("postmaster@sandbox62318caf630342c09fa97db6b75c25aa.mailgun.org");
//        mail.setSubject("就是测试！");
//        mail.setTo("1591952840@qq.com");
//        String link = "https://wwww.baidu.com";
//        mail.setMessage("<p><a href="+link+">"+link+"</a></p>");
//        service.sendActiveMail(mail);
//    }
}
