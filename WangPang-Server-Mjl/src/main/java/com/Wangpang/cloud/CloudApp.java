package com.keke.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@MapperScan("com.keke.cloud.web.mapper")
@EnableTransactionManagement
public class CloudApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudApp.class,args);
    }
}
