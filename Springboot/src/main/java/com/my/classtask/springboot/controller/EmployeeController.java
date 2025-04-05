package com.my.classtask.springboot.controller;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.service.EmployeeService;
import com.my.classtask.springboot.entity.Employee;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.zip.Deflater;

@RestController //表明返回为json
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    //增加数据
    @PostMapping("/add")
    public Result add(@RequestBody Employee employee) {
        employeeService.add(employee);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return Result.success();
    }
    //批量删除数据
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        employeeService.deleteBatch(ids);
        return Result.success();
    }
    //更新数据
    @PutMapping("/update")
    public Result update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return Result.success();
    }
    //查询所有数据
    @GetMapping("/selectAll")
    public Result selectAll(Employee employee){
       List<Employee> list= employeeService.selectAll(employee);
       return Result.success(list);
    }
    //根据id查询传参ParaVariable
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Employee employee= employeeService.selectById(id);
        return Result.success(employee);
    }
    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,
                             @RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize){
        PageInfo<Employee> pageInfo=employeeService.selectPage(employee,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}


