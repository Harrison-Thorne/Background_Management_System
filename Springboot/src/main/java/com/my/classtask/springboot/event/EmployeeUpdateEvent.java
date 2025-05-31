package com.my.classtask.springboot.event;

import com.my.classtask.springboot.entity.Employee;
import org.springframework.context.ApplicationEvent;

public class EmployeeUpdateEvent extends ApplicationEvent {
    private final Employee employee;

    public EmployeeUpdateEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
