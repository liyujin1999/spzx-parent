package com.yujin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yujin.spzx.model.dto.system.SysRoleDto;
import com.yujin.spzx.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    //角色列表的方法
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);
    //角色添加的方法
    void saveSysRole(SysRole sysRole);
    //角色修改的方法
    void updateSysRole(SysRole sysRole);
    //角色删除的方法
    void deleteById(Long roleId);
    //查询所有角色
    Map<String, Object> findAllRoles(Long userId);
}
