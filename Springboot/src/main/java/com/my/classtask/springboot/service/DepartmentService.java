package com.my.classtask.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.entity.Account;
import com.my.classtask.springboot.entity.Department;
import com.my.classtask.springboot.exception.CustomException;
import com.my.classtask.springboot.mapper.DepartmentMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Resource
    private DepartmentMapper departmentMapper;
    public void add(Department department) {

        departmentMapper.insert(department);
    }
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }
    public void deleteBatch(List<Integer> ids) {
        for(Integer id:ids){
            this.deleteById((id));
        }
    }
    public void update(Department department) {
        departmentMapper.updateById(department);
    }

    public List<Department> selectAll(Department department) {
        return departmentMapper.selectAll(department);
    }
    public Department selectById(Integer id) {
        return departmentMapper.selectById(id);
    }

    public PageInfo<Department> selectPage(Department department, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//它即将拦截你接下来执行的第一个 MyBatis 查询
        List<Department> list=departmentMapper.selectAll(department);
        return PageInfo.of(list);//PageInfo.of(list) 则会将这个“当前页的数据列表”以及 PageHelper 自动统计的“总记录数”等信息封装起来，方便返回给前端。
    }
    
}
