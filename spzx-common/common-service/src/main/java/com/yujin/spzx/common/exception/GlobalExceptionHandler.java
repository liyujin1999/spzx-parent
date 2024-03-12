package com.yujin.spzx.common.exception;

import com.yujin.spzx.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return Result.build(null, 201, "出现异常了");
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result error(CustomException customException){
        return Result.build(null, customException.getResultCodeEnum());
    }
}

