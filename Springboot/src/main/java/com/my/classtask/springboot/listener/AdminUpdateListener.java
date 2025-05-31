package com.my.classtask.springboot.listener;

import com.my.classtask.springboot.event.AdminUpdateEvent;
import com.my.classtask.springboot.entity.Admin;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AdminUpdateListener {

    @EventListener
    public void onAdminUpdate(AdminUpdateEvent event) {
        Admin admin = event.getAdmin();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("【监听事件 | 管理员修改】"
                + "\n  时间：" + now
                + "\n  ID：" + admin.getId()
                + "\n  用户名：" + admin.getUsername()
                + "\n  密码：" + admin.getPassword()
                + "\n  姓名：" + admin.getName()
                + "\n  角色：" + admin.getRole()
                + "\n  头像：" + admin.getAvatar()
                + "\n--------------------------------------"
        );
    }
}
