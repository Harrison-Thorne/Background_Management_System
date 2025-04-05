package com.my.classtask.springboot.mapper;

import com.my.classtask.springboot.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectAll(Employee employee);
    @Select("SELECT * FROM `EMPLOYEE` WHERE ID= #{id}")//用select或者xml中两种模式写查询
    Employee selectById(Integer id);

    void insert(Employee employee);

    void updateById(Employee employee);

    @Delete("delete from `employee` where id= #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM `EMPLOYEE` WHERE username= #{username}")
    Employee selectByUsername(String username);

    @Select("SELECT * FROM `EMPLOYEE` WHERE no= #{no}")
    Employee selectByNo(String no);
}
