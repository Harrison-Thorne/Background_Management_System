package com.my.classtask.springboot.exception;
import com.my.classtask.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.my.classtask.springboot.controller")
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }
    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回json
    public Result error(CustomException e){
        return Result.error(e.getCode(),e.getMsg());
    }
}