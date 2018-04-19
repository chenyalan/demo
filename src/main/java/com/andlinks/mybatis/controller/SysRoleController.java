package com.andlinks.mybatis.controller;

import com.andlinks.mybatis.common.ResultData;
import com.andlinks.mybatis.entity.SysPermissionDO;
import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.entity.SysUserDO;
import com.andlinks.mybatis.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/2/1.
 */
@RequestMapping("/sys/role")
@RestController
@Api(description = "角色")
public class SysRoleController {
    @Autowired
    private SysService sysService;

    @PostMapping
    @ApiOperation("角色添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysRoleDO",value = "角色",dataType = "SysRoleDO",paramType = "body"),
            @ApiImplicitParam(name = "permissionIds",value = "权限id",dataType = "Long",paramType = "query")
    })
    public ResultData addRole(@RequestBody SysRoleDO sysRoleDO,Long[] permissionIds){
        if(sysRoleDO.getRoleName()==null||sysRoleDO.getRoleType()==null){
            return ResultData.error("角色名或类型为空");
        }
        if(permissionIds==null){
            return ResultData.error("请为角色赋予权限");
        }
        if(sysService.findByRoleName(sysRoleDO.getRoleName())!=null){
            return ResultData.error("已存在该角色");
        }
        SysRoleDO dataRole= sysService.add(sysRoleDO);
        Set<SysPermissionDO> sysPermissionDOSet=sysService.findSet(permissionIds);//根据id查询权限，然后塞进角色的权限属性列
        dataRole.setPermissions(sysPermissionDOSet);
        dataRole.setDeleted(false);
        sysService.add(dataRole);
        return ResultData.success(dataRole);
    }

    @ApiOperation("角色分页")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "当前页",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value = "每页个数",dataType = "Integer",paramType = "query")
    })
    public ResultData getRolePage(@ApiIgnore@PageableDefault(value = 10,size=10,direction = Sort.Direction.DESC)Pageable pageable){
        return ResultData.success(sysService.getRolePage(pageable));
    }

    @ApiOperation("角色全部")
    @GetMapping("/all")
    public ResultData getRole(){
        return ResultData.success(sysService.getRoleList());
    }

    @ApiOperation("角色更改")
    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "permissionIds",value = "权限ids",paramType = "query",dataType = "Long[]"),
            @ApiImplicitParam(name="sysRoleDO",value = "角色",paramType = "body",dataType = "SysRoleDO")
    })
    public ResultData updateRole(@RequestBody SysRoleDO sysRoleDO,Long[] permissionIds){
        Set<SysPermissionDO> permission=sysService.findSet(permissionIds);
        sysRoleDO.setPermissions(permission);
        sysService.add(sysRoleDO);
        return ResultData.success("更新成功");
    }

    @ApiOperation("角色删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id",value = "角色id",paramType = "path",dataType = "Long")
    public ResultData deleteRole(@PathVariable Long id){
        SysRoleDO sysRoleDO=sysService.findByRoleId(id);
        Set<SysUserDO> users=sysService.findByRole(sysRoleDO);
        if(users.size()!=0){
            return ResultData.error("该角色有用户正在使用");
        }
        sysRoleDO.setPermissions(null); //设置为null 这样就删除了role_permission对应的记录
        sysRoleDO.setDeleted(true);
        sysService.add(sysRoleDO);
        return ResultData.success("删除成功");
    }
}
