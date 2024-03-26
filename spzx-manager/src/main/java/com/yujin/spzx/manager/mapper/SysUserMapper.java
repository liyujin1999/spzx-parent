package com.yujin.spzx.manager.mapper;


import com.github.pagehelper.PageInfo;
import com.yujin.spzx.model.dto.system.SysUserDto;
import com.yujin.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {

    public SysUser selectByUserName(String userName);
    //用户列表分页查询
    List<SysUser> findByPage(SysUserDto sysUserDto);
    //用户添加
    void saveSysUser(SysUser sysUser);
    //根据用户名查询用户是否存在
    SysUser findByName(String name);
    //用户修改
    void updateSysUser(SysUser sysUser);
//    删除接口
    void deleteById(Integer userId);
}
