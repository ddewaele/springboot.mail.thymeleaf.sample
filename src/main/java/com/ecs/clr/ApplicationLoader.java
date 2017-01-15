package com.ecs.clr;


import com.ecs.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLoader implements CommandLineRunner {


    private static final Logger logger = LoggerFactory.getLogger(ApplicationLoader.class);

    @Autowired
    private MailSender mailClient;

    @Override public void run(String... strings) throws Exception {
        logger.info("About to send and email");
        mailClient.prepareAndSend("recipient@someEmailDomain.com","Hello from Spring Boot");
        logger.info("Email request sent...");
    }
}