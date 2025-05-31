package com.my.classtask.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.entity.Account;
import com.my.classtask.springboot.entity.Admin;
import com.my.classtask.springboot.entity.Employee;
import com.my.classtask.springboot.exception.CustomException;
import com.my.classtask.springboot.mapper.EmployeeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.my.classtask.springboot.event.EmployeeUpdateEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Resource
    private EmployeeMapper employeeMapper;
    public void add(Employee employee) {
        String username=employee.getUsername();
        Employee dbEmployee=employeeMapper.selectByUsername(username);
        if(dbEmployee!=null){//账号已存在
            throw  new CustomException("500","Username already exists,Please choose another one");
        }
        Employee dbEmployee1=employeeMapper.selectByNo(employee.getNo());
        if(dbEmployee1!=null){//账号已存在
            throw  new CustomException("500","Job Number already exists,Please choose another one");
        }
        if (StrUtil.isBlank(employee.getPassword())) {
            employee.setPassword("123");//默认密码“123”
        }
        if (StrUtil.isBlank(employee.getName())) {
            employee.setName(employee.getUsername());//默认名称
        }
        employee.setRole("EMP");//员工的角色
        employeeMapper.insert(employee);
    }
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }
    public void deleteBatch(List<Integer> ids) {
        for(Integer id:ids){
            this.deleteById((id));
        }
    }
    public void update(Employee employee) {
        employeeMapper.updateById(employee);
        // 发布事件（观察者模式核心）
        eventPublisher.publishEvent(new EmployeeUpdateEvent(this, employee));
    }

    public List<Employee> selectAll(Employee employee) {
        return employeeMapper.selectAll(employee);
    }
    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }

    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Employee> list=employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }


    public Employee login(Account account) {
        String username=account.getUsername();
        Employee dbEmployee= employeeMapper.selectByUsername(username);
        if(dbEmployee==null){//没查到
            throw new CustomException("500","Account does not exist");
        }
        //存在这个用户
        String password=account.getPassword();
        if(!dbEmployee.getPassword().equals(password)){//输入密码不对
            throw new CustomException("500","Account or password is incorrect");
        }
        return dbEmployee;
    }

    public void register(Employee employee) {
        this.add(employee);
    }

    public void updatePassword(Account account) {
        Integer id=account.getId();
        Employee employee=this.selectById(id);
        if(!employee.getPassword().equals(account.getPassword())){//两个密码不相等
            throw new CustomException("500","Original Password is incorrect");
        }
        employee.setPassword(account.getNewPassword());
        this.update(employee);
    }
}
