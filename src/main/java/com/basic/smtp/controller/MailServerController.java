package com.basic.smtp.controller;

import com.basic.smtp.models.MailServerAttachmentModel;
import com.basic.smtp.models.MailServerModel;
import com.basic.smtp.models.MailServerTemplateModel;
import com.basic.smtp.service.MailServerAttachmentService;
import com.basic.smtp.service.MailServerService;
import com.basic.smtp.service.MailServerTemplate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail-server/v1")
@AllArgsConstructor
public class MailServerController {

    private final MailServerService mailServerService;
    private final MailServerAttachmentService mailServerAttachmentService;
    private final MailServerTemplate mailServerTemplate;

    @PostMapping("/mail/send")
    public ResponseEntity<String> sendMail(@RequestBody MailServerModel req) {
        return mailServerService.sendMail(req);
    }

    @PostMapping("/mail/attachment/send")
    public ResponseEntity<String> sendMailAttachment(@RequestBody MailServerAttachmentModel req) {
        return mailServerAttachmentService.sendMailWithAttachment(req);
    }

    @PostMapping("/mail/template/send")
    public ResponseEntity<String> sendMailTemplate(@RequestBody MailServerTemplateModel req) {
        return mailServerTemplate.sendMailTemplate(req);
    }

}
