package com.yujin.spzx.manager.service.Impl;

import com.alibaba.fastjson.JSON;
import com.yujin.spzx.common.exception.CustomException;
import com.yujin.spzx.manager.mapper.SysUserMapper;
import com.yujin.spzx.manager.service.SysUserService;
import com.yujin.spzx.model.dto.system.LoginDto;
import com.yujin.spzx.model.entity.system.SysUser;
import com.yujin.spzx.model.vo.common.ResultCodeEnum;
import com.yujin.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    //用户登录
    @Override
    public LoginVo login(LoginDto loginDto) {
        // 根据用户名查询用户
        String userName = loginDto.getUserName();
        SysUser sysUser = sysUserMapper.selectByUserName(userName);
        if (sysUser == null){
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR01);
        }
        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(sysUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR02) ;
        }
        //生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token , JSON.toJSONString(sysUser) , 30 , TimeUnit.MINUTES);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }
}
