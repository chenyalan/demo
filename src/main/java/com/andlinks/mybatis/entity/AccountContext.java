package com.andlinks.mybatis.entity;

import com.andlinks.mybatis.entity.redis.UserRedis;

/**
 * Created by 陈亚兰 on 2018/3/6.
 */
public class AccountContext {
    public static ThreadLocal<UserRedis> accountThreadLocal=new ThreadLocal<>();
    public static void setContext(UserRedis account){
        accountThreadLocal.set(account);
    }
    public static UserRedis getAccount(){
       return accountThreadLocal.get();
    }
    public void delete(){
        accountThreadLocal.remove();
    }
}
