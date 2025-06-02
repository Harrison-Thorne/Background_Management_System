package com.my.classtask.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.entity.Department;
import com.my.classtask.springboot.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //表明返回为json
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;
    //增加数据
    @PostMapping("/add")
    public Result add(@RequestBody Department department) {
        departmentService.add(department);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return Result.success();
    }
    //批量删除数据
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {//后端通常会接收一个 List<Integer> 或 List<String> 来处理批量 ID。
        departmentService.deleteBatch(ids);
        return Result.success();
    }
    //更新数据
    @PutMapping("/update")
    public Result update(@RequestBody Department department) {
        departmentService.update(department);
        return Result.success();
    }
    //查询所有数据
    @GetMapping("/selectAll")
    public Result selectAll(Department department){
       List<Department> list= departmentService.selectAll(department);
       return Result.success(list);
    }
    //根据id查询传参ParaVariable
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){//@PathVariable用于从 URL 的路径部分中提取变量。这些变量通常是 RESTful 风格 URL 的一部分，用于标识资源的特定实例。
        Department department= departmentService.selectById(id);
        return Result.success(department);
    }
    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Department department,
                             @RequestParam(defaultValue="1") Integer pageNum,//用于从 URL 的查询参数部分（Query Parameters，即 URL 中 ? 后面的部分）或 表单数据 中提取参数。
                             @RequestParam(defaultValue="10") Integer pageSize){
        PageInfo<Department> pageInfo=departmentService.selectPage(department,pageNum,pageSize);//PageInfo包含很多信息包括但不限于当前页和总页数等等
        return Result.success(pageInfo);
    }

}


