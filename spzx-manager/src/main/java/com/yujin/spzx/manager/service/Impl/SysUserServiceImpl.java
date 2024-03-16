package com.yujin.spzx.manager.service.Impl;

import cn.hutool.core.util.StrUtil;
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
import org.springframework.data.redis.core.ValueOperations;
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
        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key
        // 从Redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);
        if(StrUtil.isEmpty(redisCode)) {
            throw new CustomException(ResultCodeEnum.VALIDATECODE_TIMEOUT) ;
        }
        if(!StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            throw new CustomException(ResultCodeEnum.VALIDATECODE_ERROR) ;
        }
        // 验证通过删除redis中的验证码
        redisTemplate.delete("user:validate" + codeKey) ;
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
    //获取当前登录的用户信息
    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }
    //用户退出
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }
}
