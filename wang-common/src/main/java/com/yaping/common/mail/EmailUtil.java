package com.yaping.common.mail;

import com.yaping.common.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@EnableAsync
public class EmailUtil {

    Logger logger = LoggerFactory.getLogger(EmailUtil.class);


    @Value("${spring.mail.username}")
    private String form;

    @Value("${work.order.notify.subject}")
    private String default_subject;

    @Value("${work.order.notify.text}")
    private String default_text;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String email, String subject, String text) {
        if (mailSender == null) {
            mailSender = SpringUtils.getBean(JavaMailSender.class);
        }
        if (email == null) {
            throw new NullPointerException("The receiving mailbox can not be empty");
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(form);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(StringUtils.hasText(subject) ? subject : default_subject);
        simpleMailMessage.setText(StringUtils.hasText(text) ? text : default_text);

        try {

            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            logger.error("send email error|email:{}|text:{}|error:{}", email, text);
        }
    }

    @Async
    public void sendEmail(String email) {
        sendEmail(email, null, null);
    }

}