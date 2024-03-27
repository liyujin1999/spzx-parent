package com.yujin.spzx.manager.controller;


import com.github.pagehelper.PageInfo;
import com.yujin.spzx.manager.service.SysUserService;
import com.yujin.spzx.model.dto.system.AssginRoleDto;
import com.yujin.spzx.model.dto.system.SysUserDto;
import com.yujin.spzx.model.entity.system.SysUser;
import com.yujin.spzx.model.vo.common.Result;
import com.yujin.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    //用户列表分页查询
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             SysUserDto sysUserDto){
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //用户添加
    @PostMapping("/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser){
        sysUserService.saveSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);

    }

    //用户修改
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUserService.updateSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);

    }

    //删除接口
    @DeleteMapping("/deleteById/{userId}")
    public Result deleteById(@PathVariable("userId") Integer userId){
        sysUserService.deleteById(userId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    //为用户保存分配的角色
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto){
        sysUserService.doAssign(assginRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
