package com.yujin.spzx.manager.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yujin.spzx.manager.mapper.SysRoleMapper;
import com.yujin.spzx.manager.mapper.SysRoleUserMapper;
import com.yujin.spzx.manager.service.SysRoleService;
import com.yujin.spzx.model.dto.system.SysRoleDto;
import com.yujin.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    //角色列表的方法
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit){
        PageHelper.startPage(current, limit);
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);
        return pageInfo;
    }
    //角色添加的方法
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }
    //角色修改的方法
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }
    //角色删除的方法
    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    //查询所有角色
    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //1.查询所有角色
        List<SysRole> allRoles = sysRoleMapper.findAllRoles();
        Map<String, Object> map = new HashMap<>();
        map.put("allRolesList", allRoles);
        //2.根据userId查询为当前用户分配过的角色
        List<Long> roleIds = sysRoleUserMapper.selectIdsByUserId(userId);
        map.put("sysUserRoles", roleIds);
        return map;
    }
}
