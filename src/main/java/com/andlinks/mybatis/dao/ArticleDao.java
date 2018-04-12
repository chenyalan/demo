package com.andlinks.mybatis.dao;

import com.andlinks.mybatis.entity.ArticleDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by 陈亚兰 on 2018/3/5.
 */
public interface ArticleDao extends JpaRepository<ArticleDO,Long> {
    @Query(value = "select a from ArticleDO a where a.deleted=0")
    Page getMyArticle(Pageable pageable);
}
