package com.basic.smtp.service;

import com.basic.smtp.models.MailServerAttachmentModel;
import com.basic.smtp.property.MailServerProperty;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class MailServerAttachmentService {

    private final JavaMailSender javaMailSender;
    private final MailServerProperty mailServerProperty;

    public ResponseEntity<String> sendMailWithAttachment(MailServerAttachmentModel req) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(mailServerProperty.getUsername());
            helper.setTo(req.getTo());
            helper.setSubject(req.getSubject());
            helper.setText(req.getText());

            FileSystemResource file = new FileSystemResource(req.getAttachment());
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(message);
            log.info("Send mail with attachment to {} success", req.getTo());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return ResponseEntity.ok("Send mail with attachment success");
    }

}
