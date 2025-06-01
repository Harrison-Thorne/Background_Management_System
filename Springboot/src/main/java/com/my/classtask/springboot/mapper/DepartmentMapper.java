package com.my.classtask.springboot.mapper;

import com.my.classtask.springboot.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    List<Department> selectAll(Department department);
    @Select("SELECT * FROM `EMPLOYEE` WHERE ID= #{id}")//用select或者xml中两种模式写查询
    Department selectById(Integer id);

    void insert(Department department);

    void updateById(Department department);

    @Delete("delete from `department` where id= #{id}")
    void deleteById(Integer id);

}
