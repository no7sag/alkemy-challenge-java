package com.no7sag.alkemychallengejava.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendTextEmail(String newUserEmail) {

        Email from = new Email("disney.api.email.testing@gmail.com");
        String subject = "Le damos la bienvenida a Disney API \uD83C\uDF89";
        Email to = new Email(newUserEmail);
        Content content = new Content("text/plain", "¡Gracias por registrarse!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
        } catch (IOException ex) {
            System.out.println("Hubo un error al enviar el email");
        }

    }

}