package com.yujin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yujin.spzx.model.dto.system.LoginDto;
import com.yujin.spzx.model.dto.system.SysUserDto;
import com.yujin.spzx.model.entity.system.SysUser;
import com.yujin.spzx.model.vo.system.LoginVo;



public interface SysUserService {
    //用户登录
    public LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);
    //用户列表分页查询
    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);
    //用户添加
    void saveSysUser(SysUser sysUser);
    //用户修改
    void updateSysUser(SysUser sysUser);
    //删除接口
    void deleteById(Integer userId);
}
