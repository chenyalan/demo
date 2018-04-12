package com.andlinks.mybatis.entity.vo;

import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.meiju.Sex;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 陈亚兰 on 2018/2/26.
 */
@Getter
@Setter
public class UserShowVO {
    private String account;

    private String username;

    private String telephone;

    private String email;

    private String address;

    private String className;

    private String grade;

    private Sex sex;

    private SysRoleDO role;
    public UserShowVO(){}
    public UserShowVO(String account,String username,String telephone,String email,String address,String className,String grade,Sex sex,SysRoleDO role){
        this.account=account;
        this.username=username;
        this.telephone=telephone;
        this.email=email;
        this.address=address;
        this.className=className;
        this.grade=grade;
        this.sex=sex;
        this.role=role;
    }
}
