package com.yujin.spzx.manager.service;

import com.yujin.spzx.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {
    // 获取验证码图片
    public abstract ValidateCodeVo generateValidateCode();
}
