package com.my.classtask.springboot.event;

import com.my.classtask.springboot.entity.Admin;
import org.springframework.context.ApplicationEvent;

// 将“管理员更新”这个行为抽象成一个独立的事件对象，使得发布者可以不关心谁会处理这个事件，而监听器可以不关心事件是如何被触发的，只需要关注事件本身和其携带的数据。
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
