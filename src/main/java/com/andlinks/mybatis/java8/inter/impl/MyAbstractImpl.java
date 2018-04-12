package com.andlinks.mybatis.java8.inter.impl;

import com.andlinks.mybatis.java8.inter.MyAbstract;

import java.sql.Connection;
import java.util.Collection;

/**
 * Created by 陈亚兰 on 2017/12/19.
 */
public class MyAbstractImpl implements MyAbstract {
    @Override
    public String getAbs(String abs) {
        return  abs+" is chenyalan's ";
    }
    public static void main(String[] args){
        MyAbstract myAbstract=new MyAbstract() {
            @Override
            public String getAbs(String abs) {
                return "coco ";
            }
        };
        System.out.println(myAbstract.getAbs("nice"));
        System.out.println("面积是:"+myAbstract.getMath(3));
    }
}
