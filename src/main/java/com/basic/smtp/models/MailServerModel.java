package com.basic.smtp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailServerModel {
    private String to;
    private String subject;
    private String text;
}
