package com.yujin.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.yujin.spzx.manager.service.SysRoleService;
import com.yujin.spzx.model.dto.system.SysRoleDto;
import com.yujin.spzx.model.entity.system.SysRole;
import com.yujin.spzx.model.vo.common.Result;
import com.yujin.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService ;
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current ,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS) ;
    }
}