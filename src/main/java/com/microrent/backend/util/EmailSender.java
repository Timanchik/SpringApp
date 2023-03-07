package com.microrent.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class EmailSender {

    @Value("${mail.sender.address}")
    private String senderAddress;
    @Value("${mail.sender.name}")
    private String senderName;
    private final JavaMailSender javaMailSender;

    private final HtmlEmailSampleConvertor htmlEmailSampleConvertor;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender, HtmlEmailSampleConvertor htmlEmailSampleConvertor) {
        this.javaMailSender = javaMailSender;
        this.htmlEmailSampleConvertor = htmlEmailSampleConvertor;
    }

    public void sendEmailRegistrationData(String toEmail, String password) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress(senderAddress, senderName));
        helper.setTo(toEmail);
        helper.setSubject("Данные для входа");
        helper.setText(htmlEmailSampleConvertor.GetHtmlEmailRegistrationData(toEmail, password), true);
        javaMailSender.send(message);
    }
}

