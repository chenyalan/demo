package com.andlinks.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/1/31.
 */
@Entity
@Table(name="sys_role")
@Where(clause = "deleted=0")
@Getter
@Setter
public class SysRoleDO extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 5877100122277246969L;
    @Column(columnDefinition = "varchar(40) comment '角色名'")
    private String roleName;
    @Column(columnDefinition = "varchar(40) comment '角色类型'")
    private String roleType;
//    @OneToMany
//    private Set<SysUserDO> users;
    @ManyToMany
    @JoinTable(name="sys_role_permission",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<SysPermissionDO> permissions;

}
