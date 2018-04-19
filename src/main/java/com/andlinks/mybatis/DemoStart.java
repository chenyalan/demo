package com.andlinks.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * created by 陈亚兰 on 18-4-13
 */
@SpringBootApplication
@EnableCaching
@EnableJpaAuditing //此注解必加因为用了@CreateDate
public class DemoStart {
    public static void main(String[] args){
        SpringApplication.run(DemoStart.class,args);
    }
}
