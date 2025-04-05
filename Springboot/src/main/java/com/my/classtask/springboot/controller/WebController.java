package com.my.classtask.springboot.controller;

import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.entity.Account;
import com.my.classtask.springboot.entity.Employee;
import com.my.classtask.springboot.exception.CustomException;
import com.my.classtask.springboot.service.AdminService;
import com.my.classtask.springboot.service.EmployeeService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
@CrossOrigin
@RestController
public class WebController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Account account){
        Account result=null;
        if("ADMIN".equals(account.getRole())){
            result=adminService.login(account);
        } else if("EMP".equals(account.getRole())){
            result=employeeService.login(account);
        }
        return Result.success(result);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Employee employee){
        employeeService.register(employee);
        return Result.success();
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account){
        if("ADMIN".equals(account.getRole())){
           adminService.updatePassword(account);
        } else if("EMP".equals(account.getRole())){
           employeeService.updatePassword(account);
        }else{
            throw new CustomException("500","Illegal Input");
        }
        return Result.success();
    }
}
