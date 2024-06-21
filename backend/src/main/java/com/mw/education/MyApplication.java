package com.mw.education;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * 为了简化项目的开发，本项目有以下缺陷：
 *      1、Controller直接访问了Mapper，没有Service层
 *      2、硬编码登录认证及角色管理
 *      3、使用MyBatis Generator生成的代码，只支持Select类型的Example
 *      4、前后端分离，没有视图层
 *      5、数据库设计非常简单
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mw.education.dao")
public class MyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
