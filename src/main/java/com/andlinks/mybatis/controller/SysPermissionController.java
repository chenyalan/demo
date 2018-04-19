package com.andlinks.mybatis.controller;

import com.andlinks.mybatis.common.ResultData;
import com.andlinks.mybatis.entity.SysPermissionDO;
import com.andlinks.mybatis.entity.SysRoleDO;
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

import java.util.List;
import java.util.Set;

/**
 * Created by 陈亚兰 on 2018/4/19.
 */
@RestController
@RequestMapping("/sys/permission")
@Api(description = "权限")
public class SysPermissionController {
    @Autowired
    private SysService sysService;

    @PostMapping
    @ApiOperation("权限添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name="permissionCN",value = "权限中文",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="permissionEN",value = "权限英文",dataType = "String",paramType = "query")
    })
    public ResultData addPermission(String permissionCN,String permissionEN){
        SysPermissionDO sysPermissionDO=new SysPermissionDO(permissionCN,permissionEN);
        return ResultData.success(sysService.add(sysPermissionDO));
    }


    @ApiOperation("权限分页")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "当前页",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value = "每页个数",dataType = "Integer",paramType = "query")
    })
    public ResultData getPagePermission(@ApiIgnore @PageableDefault(value = 10,size=10,direction = Sort.Direction.DESC)Pageable pageable){
        return ResultData.success(sysService.getPermissionPage(pageable));
    }

    @ApiOperation("权限更改")
    @PutMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "权限id",paramType = "path",dataType = "Long"),
            @ApiImplicitParam(name="permissionEN",value = "权限英文",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="permissionCN",value = "权限中文",paramType = "query",dataType = "String")
    })
    public ResultData updatePermission(@PathVariable Long id,String permissionEN,String permissionCN){
        SysPermissionDO permissionDO=sysService.findByPermissionId(id);
        permissionDO.setPermissionCN(permissionCN);
        permissionDO.setPermissionEN(permissionEN);
        sysService.add(permissionDO);
        return ResultData.success("更新成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("权限删除")
    @ApiImplicitParam(name = "id",value = "权限id",paramType = "path",dataType = "Long")
    public ResultData deletePermission(@PathVariable Long id){
        SysPermissionDO permissionDO=sysService.findByPermissionId(id);
        List<SysRoleDO> roles=sysService.getRoleList();
        for(SysRoleDO r:roles){
            Set<SysPermissionDO> per=r.getPermissions();
            for(SysPermissionDO p:per){
                if(p.equals(permissionDO)){
                    return ResultData.error("该权限有角色在使用");
                }
            }
        }
        permissionDO.setDeleted(true);
        sysService.add(permissionDO);
        return ResultData.success("删除成功");
    }

}
