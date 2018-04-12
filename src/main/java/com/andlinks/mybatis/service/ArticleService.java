package com.andlinks.mybatis.service;

import com.andlinks.mybatis.entity.ArticleDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by 陈亚兰 on 2018/3/5.
 */
public interface ArticleService {
    ArticleDO add(ArticleDO articleDO);
    Page getMyArticle(Pageable pageable);
    Long delete(Long id);
    Long deleteSelected(Long[] ids);
}
