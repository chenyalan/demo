package com.andlinks.mybatis.entity.vo;

import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.meiju.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by 陈亚兰 on 2018/2/26.
 */
@Setter
@Getter
public class RegisterVO implements Serializable {

    @NotNull(message = "账号名不为空")
    @Size(min =3,max=20,message = "个数为3-20")
    private String account;

    private String username;

    @NotNull(message = "密码不为空")
    @Size(min =6,max=20,message = "个数为6-20")
    private String password;

    @NotNull(message = "邮箱不为空")
    @Email(message = "输入正确的邮箱")
    private String email;

    private Long[] ids;
}
