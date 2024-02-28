package com.basic.smtp.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailServerAttachmentModel {
    private String attachment;
    private String to;
    private String subject;
    private String text;
}
