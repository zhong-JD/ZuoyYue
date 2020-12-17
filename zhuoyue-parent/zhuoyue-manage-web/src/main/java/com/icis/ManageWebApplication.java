package com.icis;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//启动类
@SpringBootApplication
@EnableDubbo
public class ManageWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageWebApplication.class,args);
    }
}
