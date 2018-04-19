package com.andlinks.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/1/31.
 */
@Entity
@Table(name="sys_permission")
@Where(clause = "deleted=0")
@Getter
@Setter

public class SysPermissionDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -829519875038118259L;
    @Column(columnDefinition = "varchar(40) comment '权限中文'")
    private String permissionCN;
    @Column(columnDefinition = "varchar(40) comment '权限英文'")
    private String permissionEN;
    @JsonBackReference
    @ManyToMany(mappedBy = "permissions")
    private Set<SysRoleDO> roles;
    public  SysPermissionDO(){}
    public  SysPermissionDO(String permissionCN,String permissionEN){
        this.permissionCN=permissionCN;
        this.permissionEN=permissionEN;
    }
}
