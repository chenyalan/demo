package com.andlinks.mybatis.entity;

import lombok.Data;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by 陈亚兰 on 2018/3/5.
 */
@Data
@Where(clause = "deleted=0")
@javax.persistence.Table(name = "article")
@Entity
public class ArticleDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4005580478293218684L;
    private String article;
    private String auther;
    private String address;
    public ArticleDO(){
    }
    public ArticleDO(String article,String auther,String address){
        this.article=article;
        this.auther=auther;
        this.address=address;
        this.setDeleted(false);
    }
}
