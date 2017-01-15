package com.ecs.mail;

import com.ecs.clr.ApplicationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLoader.class);

    private JavaMailSender javaMailSender;
    private MailTemplateHandler mailTemplateHandler;

    @Autowired
    public MailSender(JavaMailSender javaMailSender, MailTemplateHandler mailTemplateHandler) {
        this.javaMailSender = javaMailSender;
        this.mailTemplateHandler = mailTemplateHandler;
    }

    @Async
    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("from@someEmailDomain.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Sample mail subject");
            String content = mailTemplateHandler.generateContent(message);
            messageHelper.setText(content, true);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
            logger.error("Error occured while attempting to send an email");
        }
    }

}