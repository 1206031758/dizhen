package com.bunfly.dizhen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//设置myatis的映射包
@MapperScan(basePackages = "com.bunfly.dizhen.dao")
public class DizhenApplication {

    public static void main(String[] args) {
        SpringApplication.run(DizhenApplication.class, args);
    }

}
