package com.basic.smtp.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailServerProperty {
    private String host;
    private String port;
    private String username;
    private String password;
}