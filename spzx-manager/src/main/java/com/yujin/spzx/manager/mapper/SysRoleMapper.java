package com.yujin.spzx.manager.mapper;

import com.yujin.spzx.model.dto.system.SysRoleDto;
import com.yujin.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    //角色列表的方法
    List<SysRole> findByPage(SysRoleDto sysRoleDto);
    //角色添加的方法
    void saveSysRole(SysRole sysRole);
    //角色修改的方法
    void updateSysRole(SysRole sysRole);
    //角色删除的方法
    void deleteById(Long roleId);

}
