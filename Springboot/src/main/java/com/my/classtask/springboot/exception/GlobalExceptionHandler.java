package com.my.classtask.springboot.exception;
import com.my.classtask.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice表示这个增强器只对 com.my.classtask.springboot.controller 包及其子包下的所有 Controller 生效，只要从调用栈向上冒泡到了 Controller 方法执行的那个点，GlobalExceptionHandler 就能捕获到它
@ControllerAdvice("com.my.classtask.springboot.controller")
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class) //@ExceptionHandler用于指定当前方法能够处理哪种类型的异常
    @ResponseBody//转换为json，作为 HTTP 响应体发送回前端
    public Result error(Exception e){
        e.printStackTrace();//将完整的异常堆栈信息打印到控制台
        return Result.error();// 返回一个默认的错误 Result 对象（例如 code: "500", msg: "系统错误"）
    }
    // 专门捕获 CustomException
    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回json
    public Result error(CustomException e){
        return Result.error(e.getCode(),e.getMsg());
    }
}