package com.mw.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类，用于配置Spring MVC的相关设置。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置跨域资源共享（CORS）。
     * 允许从特定来源（<a href="http://localhost:7000">...</a>）访问应用的所有URL，
     * 支持所有HTTP方法，并允许所有请求头，同时允许客户端凭证。
     *
     * @param registry CORS配置注册表，用于添加跨域映射。
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:7000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
