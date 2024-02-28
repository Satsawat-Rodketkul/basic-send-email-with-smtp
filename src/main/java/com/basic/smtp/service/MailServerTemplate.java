package com.basic.smtp.service;

import com.basic.smtp.models.MailServerTemplateModel;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
@AllArgsConstructor
public class MailServerTemplate {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public ResponseEntity<String> sendMailTemplate(MailServerTemplateModel req) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context ctx = new Context();
            ctx.setVariable("email", req.getTo());

            String htmlContent = templateEngine.process("template-test", ctx);

            helper.setTo(req.getTo());
            helper.setSubject("Template");
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
            log.info("Send mail with template to {} success", req.getTo());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok("Send mail with template success");
    }
}
