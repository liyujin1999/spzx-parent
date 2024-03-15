package com.yujin.spzx.manager.controller;



import com.yujin.spzx.manager.service.SysUserService;
import com.yujin.spzx.manager.service.ValidateCodeService;
import com.yujin.spzx.model.dto.system.LoginDto;
import com.yujin.spzx.model.entity.system.SysUser;
import com.yujin.spzx.model.vo.common.Result;
import com.yujin.spzx.model.vo.common.ResultCodeEnum;
import com.yujin.spzx.model.vo.system.LoginVo;
import com.yujin.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    //获取当前登录的用户信息
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
        System.out.println(token);
        //根据token查redis中信息
        SysUser sysUser = sysUserService.getUserInfo(token) ;
        System.out.println(sysUser);
        return Result.build(sysUser , ResultCodeEnum.SUCCESS) ;
    }
    //生成验证码
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }
    //用户登录
    @Operation(summary = "登录的方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
