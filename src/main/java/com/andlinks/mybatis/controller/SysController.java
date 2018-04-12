package com.andlinks.mybatis.controller;

import com.andlinks.mybatis.common.ResultData;
import com.andlinks.mybatis.entity.SysPermissionDO;
import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.entity.SysUserDO;
import com.andlinks.mybatis.entity.vo.RegisterVO;
import com.andlinks.mybatis.entity.vo.UserShowVO;
import com.andlinks.mybatis.entity.vo.UserVO;
import com.andlinks.mybatis.service.SysService;
import com.andlinks.mybatis.utils.DateTransUtils;
import com.andlinks.mybatis.utils.JavaMailUtils;
import com.andlinks.mybatis.utils.PassWordUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by 陈亚兰 on 2018/2/1.
 */
@RequestMapping("/sys")
@RestController
@Api(description = "权限 角色 用户")

public class SysController {
    @Autowired
    private SysService sysService;
    @Autowired
    private JavaMailUtils javaMailUtils;

    //==============================权限==================================//

    @PostMapping("/permission")
    @ApiOperation("权限添加")
    @ApiImplicitParam(name="permissionDO",value = "权限",dataType = "SysPermissionDO",paramType = "body")
    public ResultData addPermission(@RequestBody SysPermissionDO permissionDO){
        permissionDO.setDeleted(false);
        return ResultData.success(sysService.add(permissionDO));
    }


    @ApiOperation("权限分页")
    @GetMapping("/permission/page")
    public ResultData getPagePermission(@PageableDefault(value = 10,size=10,direction = Sort.Direction.DESC)Pageable pageable){
        return ResultData.success(sysService.getPermissionPage(pageable));
    }

    @ApiOperation("权限更改")
    @PutMapping("/permission/{id}")
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

    @DeleteMapping("/permission/{id}")
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

    //==============================角色==================================//

    @PostMapping("/roles")
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
    @GetMapping("/role/page")
    public ResultData getRolePage(@PageableDefault(value = 10,size=10,direction = Sort.Direction.DESC)Pageable pageable){
        return ResultData.success(sysService.getRolePage(pageable));
    }

    @ApiOperation("角色全部")
    @GetMapping("/role")
    public ResultData getRole(){
        return ResultData.success(sysService.getRoleList());
    }

    @ApiOperation("角色更改")
    @PutMapping("/role")
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
    @DeleteMapping("/role/{id}")
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


    //==============================用户==================================//

    @ApiOperation("用户添加")
    @PostMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userVO",value="系统用户",paramType = "body",dataType ="UserVO"),
            @ApiImplicitParam(name="roleId",value = "角色ID",paramType = "query",dataType = "Long")
    })
    public ResultData addUser(@RequestBody @Valid UserVO userVO,BindingResult bindingResult,Long roleId){
        if(bindingResult.hasErrors()){
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        SysUserDO ifUserExist= sysService.findByAccount(userVO.getAccount());
        if(ifUserExist!=null){
            return ResultData.error("此账号已存在");
        }
        if(roleId==null){
            return ResultData.error("请为账号分配角色");
        }
        SysRoleDO role=sysService.findByRoleId(roleId);
        Date now=new Date();
        String salt= DateTransUtils.getStringSec(now);
        SysUserDO sysUserDO=new SysUserDO(userVO.getAccount(),userVO.getUsername(), PassWordUtils.privatePassWord(userVO.getPassword(),salt),userVO.getTelephone(),userVO.getEmail(),userVO.getAddress(),userVO.getClassName(),userVO.getGrade(),userVO.getSex(),userVO.getRemark(),now);
        sysUserDO.setRole(role);
        sysService.add(sysUserDO);

        UserShowVO userShowVO=new UserShowVO(sysUserDO.getAccount(),sysUserDO.getUsername(),sysUserDO.getTelephone(),sysUserDO.getEmail(),sysUserDO.getAddress(),sysUserDO.getClassName(),sysUserDO.getGrade(),sysUserDO.getSex(),sysUserDO.getRole());

        return ResultData.success(userShowVO);
    }


    @ApiOperation("用户首页注册")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name="registerVO",value="系统用户",paramType = "body",dataType ="RegisterVO"),
            @ApiImplicitParam(name="roleId",value = "角色ID",paramType = "query",dataType = "Long")
    })
    public ResultData register(@RequestBody @Valid RegisterVO registerVO, BindingResult bindingResult, Long roleId) {
        if (bindingResult.hasErrors()) {
            return ResultData.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if (sysService.findByAccount(registerVO.getAccount()) != null) {
            return ResultData.error("账户已经存在");
        } else if (sysService.findByEmail(registerVO.getEmail()) != null) {
            return ResultData.error("邮箱已经注册");
        }
//        javaMailUtils.sendSimpleMail(registerVO.getEmail(),"注册会员","请您在2分钟内点击链接,完成注册");
//        javaMailUtils.sendSimpleMail(registerVO.getEmail(),"注册会员","");
        Map<String,Object> map=new HashMap<>();
        map.put("account",registerVO.getAccount());
        javaMailUtils.sendVelocity(registerVO.getEmail(),"注册会员",map);
        SysRoleDO role=sysService.findByRoleId(roleId);
        Date now=new Date();
        String salt=DateTransUtils.getStringSec(now);
        SysUserDO sysUserDO=new SysUserDO(registerVO.getAccount(),registerVO.getUsername(),PassWordUtils.privatePassWord(registerVO.getPassword(),salt),registerVO.getEmail(),now);
        sysUserDO.setRole(role);
        sysService.add(sysUserDO);
        return ResultData.success("发送成功");
    }

    @ApiOperation(value = "用户分页")
    @GetMapping("/user")
    public ResultData getUsers(@PageableDefault(value =10,size=10,direction = Sort.Direction.DESC)Pageable pageable){
        return ResultData.success(sysService.getUser(pageable));
    }

    @ApiOperation("用户更新,密码不改")
    @PostMapping("/user/{roleId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sysUserDO",value = "系统用户",paramType = "body",dataType = "SysUserDO"),
            @ApiImplicitParam(name = "roleId",value = "角色id",paramType = "path",dataType ="Long" )
    })
    public ResultData updateUser(@RequestBody SysUserDO sysUserDO,@PathVariable Long roleId){
        SysRoleDO sysRoleDO=sysService.findByRoleId(roleId);
        sysUserDO.setRole(sysRoleDO);
        sysService.add(sysUserDO);
        return ResultData.success("更新成功");
    }

    @ApiOperation("用户删除")
    @DeleteMapping("/user/{id}")
    @ApiImplicitParam(name = "id",value = "用户id",paramType = "path",dataType = "Long")
    public ResultData deleteUser(@PathVariable Long id){
        SysUserDO sysUserDO=sysService.findByUserId(id);
        sysUserDO.setDeleted(true);
        sysService.add(sysUserDO);
        return ResultData.success("删除成功");
    }

}
