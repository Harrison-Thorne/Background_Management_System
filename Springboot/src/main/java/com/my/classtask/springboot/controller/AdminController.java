package com.my.classtask.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.entity.Admin;
import com.my.classtask.springboot.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //表明返回为json
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    //增加数据
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }
    //批量删除数据
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        adminService.deleteBatch(ids);
        return Result.success();
    }
    //更新数据
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }
    //查询所有数据
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin){
       List<Admin> list= adminService.selectAll(admin);
       return Result.success(list);
    }
    //根据id查询传参ParaVariable
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Admin Admin= adminService.selectById(id);
        return Result.success(Admin);
    }
    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize){
        PageInfo<Admin> pageInfo=adminService.selectPage(admin,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}


