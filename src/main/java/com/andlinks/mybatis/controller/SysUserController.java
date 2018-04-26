package com.andlinks.mybatis.controller;

import com.andlinks.mybatis.common.ResultData;
import com.andlinks.mybatis.entity.SysRoleDO;
import com.andlinks.mybatis.entity.SysUserDO;
import com.andlinks.mybatis.entity.vo.RegisterVO;
import com.andlinks.mybatis.entity.vo.UserShowVO;
import com.andlinks.mybatis.entity.vo.UserVO;
import com.andlinks.mybatis.service.SysService;
import com.andlinks.mybatis.utils.JavaMailUtils;
import com.andlinks.mybatis.utils.PassWordUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 陈亚兰 on 2018/4/19.
 */
@RestController
@RequestMapping("/sys/user")
@Api(description = "用户")
public class SysUserController {
    @Autowired
    private SysService sysService;
    @Autowired
    private JavaMailUtils javaMailUtils;

    @ApiOperation("用户添加")
    @PostMapping("/{roleId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userVO",value="系统用户",paramType = "body",dataType ="UserVO"),
            @ApiImplicitParam(name="roleId",value = "角色ID",paramType = "path",dataType = "Long")
    })
    public ResultData addUser(@RequestBody @Valid UserVO userVO, BindingResult bindingResult, @PathVariable Long roleId){
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
        String salt= RandomStringUtils.randomAscii(4);
        SysUserDO sysUserDO=new SysUserDO(userVO.getAccount(),userVO.getUsername(), PassWordUtils.privatePassWord(userVO.getPassword(),salt),userVO.getTelephone(),userVO.getEmail(),userVO.getAddress(),userVO.getClassName(),userVO.getGrade(),userVO.getSex(),userVO.getRemark(),salt);
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
        if(roleId==null){
            return ResultData.error("未分配角色");
        }
        if (sysService.findByAccount(registerVO.getAccount()) != null) {
            return ResultData.error("账户已经存在");
        } else if (sysService.findByEmail(registerVO.getEmail()) != null) {
            return ResultData.error("邮箱已经注册");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("account",registerVO.getAccount());
        javaMailUtils.sendVelocity(registerVO.getEmail(),"注册会员",map);
        SysRoleDO role=sysService.findByRoleId(roleId);
        String salt=RandomStringUtils.randomAscii(4);
        SysUserDO sysUserDO=new SysUserDO(registerVO.getAccount(),registerVO.getUsername(),PassWordUtils.privatePassWord(registerVO.getPassword(),salt),registerVO.getEmail(),salt);
        sysUserDO.setRole(role);
        sysService.add(sysUserDO);
        return ResultData.success("发送成功");
    }

    @ApiOperation(value = "用户分页")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "当前页",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value = "每页个数",dataType = "Integer",paramType = "query")
    })
    public ResultData getUsers(@ApiIgnore@PageableDefault(value =10,size=10,direction = Sort.Direction.DESC)Pageable pageable){
        return ResultData.success(sysService.getUser(pageable));
    }

    /**
     * {
     "id": 52,
     "deleted": false,
     "createDate": 1524191952000,
     "modifyDate": 1524191845000,
     "account": "leeaoyuan",
     "username": "李袄原",
     "password": "6c15fb47a604fb62b2986a821f8e980a",
     "telephone": null,
     "email": "sharp_94free@163.com",
     "address": "首尔顶顶顶",
     "className": "首尔大学",
     "grade": null,
     "sex": "FEMALE",
     "remark": null,
     "photoAddress": null,
     "salt": "4hJc",
     "userState": "Using"
     }  account  id 密码 deleted createDate modifyDate 不能更改
     * @param sysUserDO
     * @param roleId
     * @return
     */
    @ApiOperation("用户更新,密码不改")
    @PutMapping("/{roleId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sysUserDO",value = "系统用户",paramType = "body",dataType = "SysUserDO"),
            @ApiImplicitParam(name = "roleId",value = "角色id",paramType = "path",dataType ="Long" )
    })
    public ResultData updateUser(@RequestBody SysUserDO sysUserDO,@PathVariable Long roleId){
        SysRoleDO sysRoleDO=sysService.findByRoleId(roleId);
        sysUserDO.setRole(sysRoleDO);
        sysUserDO.setDeleted(false);
        sysService.add(sysUserDO);
        return ResultData.success("更新成功");
    }

    @ApiOperation("用户删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id",value = "用户id",paramType = "path",dataType = "Long")
    public ResultData deleteUser(@PathVariable Long id){
        SysUserDO sysUserDO=sysService.findByUserId(id);
        if(sysUserDO==null){
            return ResultData.error("不存在该用户");
        }
        sysUserDO.setDeleted(true);
        sysService.add(sysUserDO);
        return ResultData.success("删除成功");
    }
}
