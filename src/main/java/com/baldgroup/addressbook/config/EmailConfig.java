package com.baldgroup.addressbook.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create By  @林俊杰
 * 2020/4/21 20:13
 *
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class EmailConfig {

    @Value("${mail.mailFrom}")
    private String mailMailFrom;

    @Value("${mail.domainName}")
    private String mailDomainName;
}