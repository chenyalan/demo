package com.andlinks.mybatis.entity.vo;

import com.andlinks.mybatis.meiju.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.Serializable;

/**
 * Created by 陈亚兰 on 2018/2/24.
 */
@Setter
@Getter
public class UserVO implements Serializable {

    @NotNull(message = "账号名不为空")
    @Size(min =2,max=20,message = "个数为3-20")
    private String account;

    @NotNull(message = "姓名不为空")
    @Size(min =2,max=20,message = "个数为3-20")
    private String username;

    @NotNull(message = "密码不为空")
    @Size(min =6,max=20,message = "个数为6-20")
    private String password;

    @NotNull(message = "电话不为空")
//    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message = "请输入正确的电话")
    @Pattern(regexp = "^(13|15|17|18)[0-9]{9}$",message = "请输入正确的电话")
    private String telephone;

    @NotNull(message = "邮箱不为空")
    @Email(message = "输入正确的邮箱")
    private String email;


    private String address;

    private String className;

    private String grade;

    @NotNull(message = "性别不为空")
    private Sex sex;

    private String remark;

    private String token;

    private String file;
}
