package com.andlinks.mybatis.service.redis.impl;

import com.andlinks.mybatis.dao.redis.UserRedisDao;
import com.andlinks.mybatis.entity.redis.UserRedis;
import com.andlinks.mybatis.service.redis.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 陈亚兰 on 2018/2/28.
 */
@Service
public class UserRedisServiceImpl implements UserRedisService {

    @Autowired
    private UserRedisDao userRedisDao;

    //我擦擦，巨坑啊用findByAccount还不行，一定要findOne...所以@Id是有作用的。
    @Override
    public UserRedis findByAccount(String account) {
        return userRedisDao.findOne(account);
    }

    @Override
    public UserRedis save(UserRedis userRedis) {
        return userRedisDao.save(userRedis);
    }

    @Override
    public void delete(String account) {

    }
}
