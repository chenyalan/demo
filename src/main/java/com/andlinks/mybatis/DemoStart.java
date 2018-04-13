package com.andlinks.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * created by 陈亚兰 on 18-4-13
 */
@SpringBootApplication
@EnableCaching
public class DemoStart {
    public static void main(String[] args){
        SpringApplication.run(DemoStart.class,args);
    }
}
