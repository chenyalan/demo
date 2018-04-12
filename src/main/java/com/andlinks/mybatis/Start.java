package com.andlinks.mybatis;

import com.andlinks.mybatis.common.MyException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by 陈亚兰 on 2017/11/28.
 */
@SpringBootApplication
@EnableJpaAuditing
public class Start {
    public static void main(String[] args) throws MyException{
        SpringApplication.run(Start.class,args);
    }
}
