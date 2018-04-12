package com.andlinks.mybatis.java8.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 陈亚兰 on 2017/12/11.
 */
@Setter
@Getter
public class Employee {
    private String name;
    private int age;
    private Double salary;
    public Employee(String name,int age ,Double salary){
        this.name=name;
        this.age=age;
        this.salary=salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
