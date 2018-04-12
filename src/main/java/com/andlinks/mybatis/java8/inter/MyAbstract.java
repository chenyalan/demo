package com.andlinks.mybatis.java8.inter;

/**
 * Created by 陈亚兰 on 2017/12/19.
 */
public interface MyAbstract {
    public String getAbs(String abs);
    default Double getMath(int radius){
        return 3.14*Math.pow(radius,2);
    }
}
