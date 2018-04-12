package com.andlinks.mybatis.service.impl;

import com.andlinks.mybatis.dao.SysPermissionDao;
import com.andlinks.mybatis.dao.SysRoleDao;
import com.andlinks.mybatis.dao.SysUserDao;
import com.andlinks.mybatis.entity.SysPermissionDO;
import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.entity.SysUserDO;
import com.andlinks.mybatis.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/2/2.
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysPermissionDao permissionDao;
    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysUserDao userDao;

    //权限、角色、用户 添加
    @Override
    public SysPermissionDO add(SysPermissionDO permissionDO) {
        return permissionDao.save(permissionDO);
    }

    @Override
    public SysRoleDO add(SysRoleDO sysRoleDO) {
        return roleDao.save(sysRoleDO);
    }

    @Override
    public SysUserDO add(SysUserDO sysUserDO) {
        return userDao.save(sysUserDO);
    }

    //权限、角色 全部查询
    @Override
    public List<SysPermissionDO> getPermissionList() {
        return permissionDao.findAll();
    }

    @Override
    public List<SysRoleDO> getRoleList() {
        return roleDao.findAll();
    }

    //权限、角色、用户 分页查询
    @Override
    public Page<SysPermissionDO> getPermissionPage(Pageable pageable) {
        return permissionDao.findAll(pageable);
    }

    @Override
    public Page<SysRoleDO> getRolePage(Pageable pageable) {
        return roleDao.findAll(pageable);
    }

    @Override
    public Page<SysUserDO> getUser(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    //根据用户名、邮箱名查询
    @Override
    public SysUserDO findByAccount(String account) {
        return userDao.findAccount(account);
    }

    @Override
    public SysUserDO findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public SysUserDO findByAccountAndPassword(String account, String password) {
        return userDao.findByAccountAndPassword(account,password);
    }

    @Override
    public SysUserDO findByUserId(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Set<SysUserDO> findByRole(SysRoleDO sysRoleDO) {
        return userDao.findByRole(sysRoleDO);
    }

    //根据ids查询权限
    @Override
    public Set<SysPermissionDO> findSet(Long[] ids) {
        return permissionDao.findSet(ids);
    }

    //根据id来查询某一个权限
    @Override
    public SysPermissionDO findByPermissionId(Long id) {
        return permissionDao.findById(id);
    }

    //根据角色名查询，添加角色时候去重
    @Override
    public SysRoleDO findByRoleName(String roleName) {
        return roleDao.findByRoleName(roleName);
    }

    //根据角色id查询角色，添加用户时候塞给对象role☞
    @Override
    public SysRoleDO findByRoleId(Long id) {
        return roleDao.findById(id);
    }


}
