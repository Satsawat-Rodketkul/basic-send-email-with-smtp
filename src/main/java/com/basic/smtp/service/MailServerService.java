package com.basic.smtp.service;

import com.basic.smtp.models.MailServerModel;
import com.basic.smtp.property.MailServerProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailServerService {

    private final JavaMailSender javaMailSender;
    private final MailServerProperty mailServerProperty;

    public ResponseEntity<String> sendMail(MailServerModel req) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailServerProperty.getUsername());
            message.setTo(req.getTo());
            message.setSubject(req.getSubject());
            message.setText(req.getText());

            javaMailSender.send(message);
            log.info("Send mail to {} success", req.getTo());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok("Send mail success");
    }

}
