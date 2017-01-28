package com.ecs.rest;

import com.ecs.mail.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestMailService {

    private static final Logger logger = LoggerFactory.getLogger(RestMailService.class);

    @Autowired
    private EmailSender emailSender;

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody  Payload payload) throws Exception {
        logger.info("About to send and email");
        Map<String,Object> ctx = new HashMap<>();
        ctx.put("message",payload.message);
        emailSender.prepareAndSend(payload.to.split(","),payload.subject,ctx);
        logger.info("Email request sent...");
    }

    static class Payload {
        public String to;
        public String subject;
        public String message;
    }
}
