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
    public Result deleteBatch(@RequestBody List<Integer> ids) {
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
    public Result selectById(@PathVariable Integer id){
        Department department= departmentService.selectById(id);
        return Result.success(department);
    }
    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Department department,
                             @RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize){
        PageInfo<Department> pageInfo=departmentService.selectPage(department,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}


