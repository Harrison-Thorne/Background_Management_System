package com.my.classtask.springboot.controller;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.service.EmployeeService;
import com.my.classtask.springboot.entity.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
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
    //导出excel
    @GetMapping("/export")
    public void export(HttpServletResponse response)throws Exception{
        //1.拿到所有员工数据
        List<Employee>employeeList=employeeService.selectAll(null);
        //2.构建ExcelWriter
        //在内存操作,写出到浏览器
        ExcelWriter writer= ExcelUtil.getWriter(true);
        //3.设置别名
        writer.addHeaderAlias("username","Username");
        writer.addHeaderAlias("name","Name");
        writer.addHeaderAlias("sex","Sex");
        writer.addHeaderAlias("no","No");
        writer.addHeaderAlias("age","Age");
        writer.addHeaderAlias("description","Description");
        writer.addHeaderAlias("departmentName","DepartmentName");
        writer.addHeaderAlias("departmentId","DepartmentId");
        writer.setOnlyAlias(true);//只导出有别名的字段
        //4.写出数据到writer
        writer.write(employeeList,true);
        //5.设置输出文件名称 以及输出流的头信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        String fileName = URLEncoder.encode("employee_info", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        //6.写出到输出流
        ServletOutputStream os= response.getOutputStream();
        writer.flush(os);
        writer.close();
    }

    //excel导入
    @PostMapping("/import")
    public Result importData(MultipartFile file) throws Exception{
        //1.拿到输入流构建reader
        InputStream inputStream= file.getInputStream();
        ExcelReader reader= ExcelUtil.getReader(inputStream);
        //2.读取excel里面的数据
        reader.addHeaderAlias("Username","username");
        reader.addHeaderAlias("Password","password");
        reader.addHeaderAlias("Name","name");
        reader.addHeaderAlias("Sex","sex");
        reader.addHeaderAlias("No","no");
        reader.addHeaderAlias("Age","age");
        reader.addHeaderAlias("Description","description");
        reader.addHeaderAlias("DepartmentName","departmentName");
        reader.addHeaderAlias("DepartmentId","departmentId");
        List<Employee> employeeList=reader.readAll(Employee.class);
        //3.写入List数据到数据库
        for(Employee employee:employeeList){
            employeeService.add(employee);
        }
        return Result.success();
    }
}


