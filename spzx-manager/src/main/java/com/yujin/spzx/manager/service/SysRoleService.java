package com.yujin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yujin.spzx.model.dto.system.SysRoleDto;
import com.yujin.spzx.model.entity.system.SysRole;

public interface SysRoleService {
    //角色列表的方法
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);
    //角色添加的方法
    void saveSysRole(SysRole sysRole);
}
