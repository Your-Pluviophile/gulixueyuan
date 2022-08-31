package com.atguigu.servicebase.exceptionHandler;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    //指定是什么样的异常
    @ExceptionHandler(Exception.class)//全局
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("系统繁忙，请稍后再试");
    }
    //

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
