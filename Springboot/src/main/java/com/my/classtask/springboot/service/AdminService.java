package com.my.classtask.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.entity.Account;
import com.my.classtask.springboot.entity.Admin;
import com.my.classtask.springboot.exception.CustomException;
import com.my.classtask.springboot.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.my.classtask.springboot.event.AdminUpdateEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    public void add(Admin admin) {
        String username=admin.getUsername();
        Admin dbAdmin=adminMapper.selectByUsername(username);
        if(dbAdmin!=null){//账号已存在
            throw  new CustomException("500","Username already exists,Please choose another one");
        }
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword("admin");//默认密码“admin”
        }
        if (StrUtil.isBlank(admin.getName())) {
            admin.setName(admin.getUsername());//默认名称
        }
        admin.setRole("ADMIN");//管理员的角色
        adminMapper.insert(admin);
    }
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }
    public void deleteBatch(List<Integer> ids) {
        for(Integer id:ids){
            this.deleteById((id));
        }
    }

    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> list=adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }


    public Admin login(Account account) {
        String username=account.getUsername();
        Admin dbAdmin= adminMapper.selectByUsername(username);
        if(dbAdmin==null){//没查到
            throw new CustomException("500","Account does not exist");
        }
        //存在这个用户
        String password=account.getPassword();
        if(!dbAdmin.getPassword().equals(password)){//输入密码不对
            throw new CustomException("500","Account or password is incorrect");
        }
        return dbAdmin;
    }

    public void updatePassword(Account account) {
        Integer id=account.getId();
        Admin admin=this.selectById(id);
        if(!admin.getPassword().equals(account.getPassword())){//两个密码不相等
            throw new CustomException("500","Original Password is incorrect");
        }
        admin.setPassword(account.getNewPassword());
        this.update(admin);
    }

    public void update(Admin admin) {
        adminMapper.updateById(admin);
        // 发布事件，供监听器处理
        eventPublisher.publishEvent(new AdminUpdateEvent(this, admin));
    }
}
