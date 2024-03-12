package com.yujin.spzx.manager.service.Impl;

import com.yujin.spzx.manager.service.ValidateCodeService;
import com.yujin.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public ValidateCodeVo generateValidateCode() {
        //通过工具生成图片验证码
        //参数：宽  高  验证码位数 干扰线数量

        return null;
    }
}
