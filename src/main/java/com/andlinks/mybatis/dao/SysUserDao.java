package com.andlinks.mybatis.dao;

import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.entity.SysUserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/2/1.
 */
public interface SysUserDao extends JpaRepository<SysUserDO,Long> {
    Page<SysUserDO> findAll(Pageable pageable);
    @Query(value = "select user from SysUserDO user where account=?1")
    SysUserDO findAccount(String account);
    SysUserDO findByEmail(String email);
    SysUserDO findByAccountAndPassword(String account,String password);
    SysUserDO findById(Long id);
    Set<SysUserDO> findByRole(SysRoleDO sysRoleDO);
}
