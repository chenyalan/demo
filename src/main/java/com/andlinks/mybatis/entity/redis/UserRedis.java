package com.andlinks.mybatis.entity.redis;

import com.andlinks.mybatis.entity.vo.PermissionVO;
import com.andlinks.mybatis.meiju.Sex;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/2/28.
 */
@Data
@RedisHash(value = "userToken")
public class UserRedis implements Serializable{
    private static final long serialVersionUID = 6433282499780320578L;
    @Id
    private String account;

//    private SysRoleDO role;
    private String roleName;
    private String roleType;
    /**
     * 这里有个巨坑，用带@ManytoMany关联表的注解会引起  java.lang.StackOverflowError: null
     * 所以不用SysRoleDO
     */
    private Set<PermissionVO> permissionVOS;
    private Sex sex;


    private Date dealTime;
    public UserRedis(String account,String roleName,Sex sex,Set<PermissionVO> permissionVOS,String roleType,Date dealTime){
        this.account=account;
        this.roleName=roleName;
        this.permissionVOS=permissionVOS;
        this.sex=sex;
        this.roleType=roleType;
        this.dealTime=dealTime;
    }
}
