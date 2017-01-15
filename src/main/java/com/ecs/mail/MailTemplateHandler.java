package com.ecs.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailTemplateHandler {

    public static final String MESSAGE = "message";
    public static final String MAIL_TEMPLATE = "mailTemplate";

    private TemplateEngine templateEngine;
 
    @Autowired
    public MailTemplateHandler(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String generateContent(String message) {
        Context context = new Context();
        context.setVariable(MESSAGE, message);
        return templateEngine.process(MAIL_TEMPLATE, context);
    }
 
}