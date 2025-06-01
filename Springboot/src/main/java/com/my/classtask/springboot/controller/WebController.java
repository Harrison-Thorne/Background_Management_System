package com.my.classtask.springboot.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.entity.Account;
import com.my.classtask.springboot.entity.Article;
import com.my.classtask.springboot.entity.Employee;
import com.my.classtask.springboot.exception.CustomException;
import com.my.classtask.springboot.service.AdminService;
import com.my.classtask.springboot.service.ArticleService;
import com.my.classtask.springboot.service.EmployeeService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class WebController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private AdminService adminService;

    @Resource
    private ArticleService articleService;

    //@RequestBody告诉 Spring，将 HTTP 请求体中的 JSON 数据如data.form 对象（包含 username、password 和 role）自动转换（反序列化）成一个 Account 类型的 Java 对象
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
    @GetMapping("/barData")
    public Result getBarData(){
        Map<String,Object>map =new HashMap<>();
        List<Employee> employeeList= employeeService.selectAll(null);
        Set<String> departmentNameSet= employeeList.stream().map(Employee::getDepartmentName).collect(Collectors.toSet());//set字符串已经去重
        map.put("department",departmentNameSet);//x轴数据
        List<Long> countList=new ArrayList<>();
        for(String departmentName : departmentNameSet){
            //统计这个部门下面员工数量
            long count= employeeList.stream().filter(employee -> employee.getDepartmentName().equals(departmentName)).count();
            countList.add(count);
        }
        map.put("count",countList);//y轴员工数量数据
        return Result.success(map);
    }

    @GetMapping("/lineData")
    public Result getLineData(){
        Map<String,Object>map =new HashMap<>();

        Date date=new Date();//今天当前时间
        DateTime start= DateUtil.offsetDay(date,-7);//起始日期
        List<DateTime>dateTimeList= DateUtil.rangeToList(start,date, DateField.DAY_OF_YEAR);
        //把DateTime类型日期转换成字符串类型数据["10.22"]
        List<String>dateStrList= dateTimeList.stream().map(dateTime -> DateUtil.format(dateTime,"MM.dd")).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        map.put("date",dateStrList);//x轴数据

        List<Integer> countList=new ArrayList<>();
        for(DateTime day :dateTimeList){
            String dayFormat= DateUtil.formatDate(day);//2024-10-22
            Integer count=articleService.selectCountByDate(dayFormat);
            countList.add(count);
        }

        map.put("count",countList);//y轴发布文章数量数据
        return Result.success(map);
    }

    @GetMapping("/pieData")
    public Result getPieData(){
        List<Map<String,Object>> list=new ArrayList<>();
        List<Employee> employeeList= employeeService.selectAll(null);
        Set<String> departmentNameSet= employeeList.stream().map(Employee::getDepartmentName).collect(Collectors.toSet());//set字符串已经去重
        List<Long> countList=new ArrayList<>();
        for(String departmentName : departmentNameSet){
            HashMap<String,Object> map =new HashMap<>();
            map.put("name",departmentName);
            long count= employeeList.stream().filter(employee -> employee.getDepartmentName().equals(departmentName)).count();
            map.put("value",count);
            list.add(map);
        }
        return Result.success(list);
    }
}
