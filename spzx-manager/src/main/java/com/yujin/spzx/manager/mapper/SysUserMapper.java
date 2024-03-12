package com.yujin.spzx.manager.mapper;


import com.yujin.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper {

    public SysUser selectByUserName(String userName);
}
