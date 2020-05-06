package com.baldgroup.addressbook.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * Create By  @林俊杰
 * 2020/4/26 20:12
 *
 * @version 1.0
 */
@Configuration
public class MultipartFileConfig {
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大

        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
