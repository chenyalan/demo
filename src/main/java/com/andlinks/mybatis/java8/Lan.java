package com.andlinks.mybatis.java8;

import com.andlinks.mybatis.java8.model.Employee;
import com.google.common.annotations.VisibleForTesting;

import javax.sql.rowset.spi.SyncProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 陈亚兰 on 2017/12/11.
 */
public class Lan {
    public static void main(String[] args) {
        List<Employee> listA = Arrays.asList(new Employee("陈亚兰", 24, 5500.0),
                new Employee("小青", 23, 6500.0),
                new Employee("小怪兽",11,2222.3),
                new Employee("小白",32,3232.2));
        List<Employee> listB=filterEmp(listA, new Myfilter<Employee>() {
                    @Override
                    public boolean getT(Employee employee) {
                        return employee.getAge()>23;
                    }
                });
                List < Employee > listC = filterEmp(listA,n->n.getAge()>25);
                listC.forEach(System.out::println);
                System.out.println("__________________________________");
                listA.stream().filter(a->a.getAge()>22).forEach(System.out::println);
                listA.stream().map(Employee::getName).distinct();

                //---------
        Runnable r=new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello runnable");
            }
        };
        r.run();
        Runnable rl=()->System.out.println("Hello Lambda");
        rl.run();
    }

    public static List<Employee> filterEmp(List<Employee> list,Myfilter<Employee> myfilter){
        List<Employee> emps=new ArrayList<>();
        for(Employee employee:list){
            if(myfilter.getT(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

}

