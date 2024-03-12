package com.yujin.spzx.manager.service;

import com.yujin.spzx.model.dto.system.LoginDto;
import com.yujin.spzx.model.vo.system.LoginVo;



public interface SysUserService {
    //用户登录
    public LoginVo login(LoginDto loginDto);
}
