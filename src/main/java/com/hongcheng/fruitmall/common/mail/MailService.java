package com.hongcheng.fruitmall.common.mail;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class MailService {
    @Value("${mailgun.apikey}")
    private String mailgun_apikey;

    @Value("${mailgun.url}")
    private String mailgun_url;

    @Value("${mailgun.user}")
    private String user;

    public void sendMail(MailRequest mail) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", mailgun_apikey));

        WebResource webResource = client.resource(mailgun_url);
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", user);
        formData.add("to", mail.getTo());
        formData.add("subject", mail.getSubject());
        formData.add("html", mail.getMessage());
        webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);

        client.destroy();
    }
}
