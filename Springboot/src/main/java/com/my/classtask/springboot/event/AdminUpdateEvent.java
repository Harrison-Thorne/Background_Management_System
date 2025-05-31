package com.my.classtask.springboot.event;

import com.my.classtask.springboot.entity.Admin;
import org.springframework.context.ApplicationEvent;

// 事件类：管理员被更新
public class AdminUpdateEvent extends ApplicationEvent {
    private final Admin admin;

    public AdminUpdateEvent(Object source, Admin admin) {
        super(source);
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }
}
