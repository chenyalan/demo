package com.andlinks.mybatis.meiju;

import com.andlinks.mybatis.base.BaseEntity;
import com.andlinks.mybatis.common.MyException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 陈亚兰 on 2017/11/28.
 */
public enum State implements BaseEnum {
    Registering("正在注册",0),
    Using("正在使用",1),
    DisUsed("禁止使用",-1);
    private int value;

    private static Map<Integer,State> valueMap=new HashMap<>();

    static{
        for(State state:State.values()){
            valueMap.put(state.getNum(),state);
        }
    }

    public static State getByValue(int value){
        State state =valueMap.get(value);
        if(state==null){
            throw new MyException("没有这个元素");
        }
        return state;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    State(String state, int num){
        this.stateName=state;
        this.num=num;
    }
    String stateName;
    int num;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
