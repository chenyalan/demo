package com.andlinks.mybatis.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 陈亚兰 on 2018/2/28.
 */
@Data
public class PermissionVO implements Serializable {
    private String permissionEN;
    public PermissionVO(){}
    public PermissionVO(String permissionEN){
        this.permissionEN=permissionEN;
    }
}
