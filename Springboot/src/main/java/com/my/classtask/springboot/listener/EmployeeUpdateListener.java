package com.my.classtask.springboot.listener;

import com.my.classtask.springboot.event.EmployeeUpdateEvent;
import com.my.classtask.springboot.entity.Employee;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EmployeeUpdateListener {

    @EventListener
    public void onEmployeeUpdate(EmployeeUpdateEvent event) {
        Employee employee = event.getEmployee();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("【监听事件 | 员工修改】"
                + "\n  时间：" + now
                + "\n  ID：" + employee.getId()
                + "\n  用户名：" + employee.getUsername()
                + "\n  密码：" + employee.getPassword()
                + "\n  角色：" + employee.getRole()
                + "\n  姓名：" + employee.getName()
                + "\n  性别：" + employee.getSex()
                + "\n  工号：" + employee.getNo()
                + "\n  年龄：" + employee.getAge()
                + "\n  简介：" + employee.getDescription()
                + "\n  部门ID：" + employee.getDepartmentId()
                + "\n  头像：" + employee.getAvatar()
                + "\n--------------------------------------"
        );
    }
}
