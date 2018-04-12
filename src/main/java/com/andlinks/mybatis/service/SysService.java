package com.andlinks.mybatis.service;

import com.andlinks.mybatis.entity.SysPermissionDO;
import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.entity.SysUserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/2/1.
 */
public interface SysService {
    //权限、角色、用户添加
    SysPermissionDO add(SysPermissionDO permissionDO);
    SysRoleDO add(SysRoleDO sysRoleDO);
    SysUserDO add(SysUserDO sysUserDO);

    //权限、角色全部查询
    List<SysPermissionDO> getPermissionList();
    List<SysRoleDO> getRoleList();


    //权限、角色、用户分页查询
    Page<SysPermissionDO> getPermissionPage(Pageable pageable);
    Page<SysRoleDO> getRolePage(Pageable pageable);
    Page<SysUserDO> getUser(Pageable pageable);

    ///user 根据账户、邮箱 查询
    SysUserDO findByAccount(String name);
    SysUserDO findByEmail(String email);
    SysUserDO findByAccountAndPassword(String account,String password);//登陆时候匹配账号和密码
    SysUserDO findByUserId(Long id);
    Set<SysUserDO> findByRole(SysRoleDO sysRoleDO);

    //permission 根据ids查询权限
    Set<SysPermissionDO> findSet(Long[] ids);

    SysPermissionDO findByPermissionId(Long id);

    //role 根据角色名查询，比如添加角色时候去重
    SysRoleDO findByRoleName(String roleName);

    //根据id来查询角色
    SysRoleDO findByRoleId(Long id);
}
