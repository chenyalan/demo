package com.andlinks.mybatis.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 陈亚兰 on 2017/11/28.
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -8553448744730500529L;
    private Long  id;
    private Boolean deleted;
    private Integer version;
    private Date createDate;
    private Date updateDate;
}
