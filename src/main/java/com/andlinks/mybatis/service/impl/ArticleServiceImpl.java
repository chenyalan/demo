package com.andlinks.mybatis.service.impl;

import com.andlinks.mybatis.dao.ArticleDao;
import com.andlinks.mybatis.entity.ArticleDO;
import com.andlinks.mybatis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by 陈亚兰 on 2018/3/5.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public ArticleDO add(ArticleDO articleDO) {
        return articleDao.save(articleDO);
    }

    @Override
    public Page getMyArticle(Pageable pageable) {
        return articleDao.getMyArticle(pageable);
    }

    @Override
    public Long delete(Long id) {
        ArticleDO articleDO;
        try {
            articleDO=articleDao.findOne(id);
        }catch (Exception e){
            return -id;
        }
        articleDO.setDeleted(true);
        articleDao.save(articleDO);
        return id;
    }

    @Override
    public Long deleteSelected(Long[] ids) {
        ArticleDO articleDO;
        for(Long id:ids){
            try{
                articleDO=articleDao.findOne(id);
                articleDO.setDeleted(true);
                articleDao.save(articleDO);
            }catch (Exception e){
                return -id;
            }
        }
        return 1L;
    }
}
