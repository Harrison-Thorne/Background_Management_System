package com.my.classtask.springboot.mapper;

import com.my.classtask.springboot.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {
    List<Admin> selectAll(Admin admin);
    @Select("SELECT * FROM `admin` WHERE ID= #{id}")//用select或者xml中两种模式写查询
    Admin selectById(Integer id);

    void insert(Admin admin);

    void updateById(Admin admin);

    @Delete("delete from `admin` where id= #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM `admin` WHERE username= #{username}")
    Admin selectByUsername(String username);
}
