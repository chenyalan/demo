package com.andlinks.mybatis.dao.redis;

import com.andlinks.mybatis.entity.redis.UserRedis;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by 陈亚兰 on 2018/2/28.
 */
public interface UserRedisDao extends JpaRepository<UserRedis,String> {
    UserRedis findByAccount(String account);
}
