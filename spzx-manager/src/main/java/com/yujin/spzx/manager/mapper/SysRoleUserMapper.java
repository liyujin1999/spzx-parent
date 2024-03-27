package com.yujin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {
    //重新分配角色
    void doAssign(Long userId, Long roleId);
    //根据userID 删除之前分配过的角色
    void deleteByUserId(Long userId);
    //根据userId查询为当前用户分配过的角色
    List<Long> selectIdsByUserId(Long userId);
}
