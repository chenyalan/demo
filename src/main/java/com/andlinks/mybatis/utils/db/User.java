package com.andlinks.mybatis.utils.db;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 陈亚兰 on 2018/4/3.
 */
@Setter
@Getter
public class User {
    private String idCard;
    private String name;
    public User(){
    }
    public User(String idCard,String name){
        this.idCard=idCard;
        this.name=name;
    }
}
