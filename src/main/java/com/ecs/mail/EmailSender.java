package com.ecs.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    private JavaMailSender javaMailSender;
    private MailTemplateHandler mailTemplateHandler;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender, MailTemplateHandler mailTemplateHandler) {
        this.javaMailSender = javaMailSender;
        this.mailTemplateHandler = mailTemplateHandler;
    }

    @Async
    public void prepareAndSend(String[] recipients, String subject, Map<String,Object> ctx) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("from@someEmailDomain.com");
            messageHelper.setTo(recipients);
            messageHelper.setSubject(subject);
            String content = mailTemplateHandler.generateContent(ctx);
            messageHelper.setText(content, true);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            logger.error("Error occured while attempting to send an email",e);
        }
    }

}