<template>
  <div>
    <!-- 顶部栏 -->
    <div style="height: 60px; background-color: #4285f4;display: flex; align-items: center;">
      <div style="width: 500px; display: flex;align-items: center; padding-left: 10px">
        <img style="width: 40px" src="@/assets/management.png" alt="">
        <span style="font-size: 20px;color:#f5f5f5;">Background Management System</span>
      </div>
      <div style="flex:1"></div>
      <div style="width:fit-content; display: flex;align-items: center;padding-right: 10px">
        <img :src="data.user.avatar||'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="" style="width: 40px;height: 40px;border-radius: 50%">
        <span style="color:white;margin-left: 5px">{{data.user.name}}</span>
      </div>
    </div>

    <!-- 内容区域 -->
    <div style="display: flex; height: calc(100vh - 60px);">
      <!-- 左侧菜单 router控制跳转 default-active标明当前路径高亮 default-openeds默认展开有子菜单的-->
      <el-menu
          :default-active="router.currentRoute.value.path"
          style="width: 230px; border-right: 1px solid #ddd;"
          background-color="#ffffff"
          text-color="#000"
          active-text-color="#4285f4"
          router
          :default-openeds="['1']"
      >
        <el-menu-item index="/manager/Home">
          <el-icon><House /></el-icon>
          System Dashboard
        </el-menu-item>
        <el-menu-item index="/manager/Data">
          <el-icon><DataAnalysis /></el-icon>
          Data Statistics
        </el-menu-item>
        <el-menu-item index="/manager/Article" >
          <el-icon><Reading /></el-icon>
          Article Management
        </el-menu-item>
        <el-menu-item index="/manager/Department" v-if="role === 'ADMIN'">
          <el-icon><OfficeBuilding /></el-icon>
          Department Management
        </el-menu-item>
        <el-sub-menu index="1" v-if="role === 'ADMIN'">
          <template #title>
            <el-icon><User /></el-icon>
            <span>User Management</span>
          </template>
          <el-menu-item index="/manager/Admin" >Admin Information</el-menu-item>
          <el-menu-item index="/manager/Employee" >Staff Information</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/manager/person">
          <el-icon><UserFilled /></el-icon>
          Personal Information
        </el-menu-item >
        <el-menu-item index="/manager/password">
          <el-icon><Lock /></el-icon>
          Change Password
        </el-menu-item >
        <el-menu-item index="/login" @click="logout">
          <el-icon><SwitchButton /></el-icon>
          Log Out
        </el-menu-item>

      </el-menu>

      <!-- 主体内容 RouterView渲染子组件 @updateUser监听子组件中比如更新头像出来的emit信息则调用updateUser函数 -->
      <div style="flex: 1; padding: 20px; width:0; background-color: #eaeaf1">
        <RouterView @updateUser="updateUser"/>
      </div>
    </div>
  </div>
</template>
<script setup>

import {DataAnalysis, House, OfficeBuilding, Reading, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import {reactive} from "vue";

//获取当前页面的是ADMIN还是EMP决定展示多少
const role = JSON.parse(localStorage.getItem('xm-pro-user') || '{}').role || 'EMP'

//初始化新头像数据 reactive是让其为代理变量，当user改变时候就会通知上面显示的data.user.avatar也该变了
const data=reactive({
  user:JSON.parse(localStorage.getItem('xm-pro-user'))
})

const  logout =()=>{
  localStorage.removeItem('xm-pro-user')//清楚缓存数据
  location.href='/login'
}

//更新头像数据
const updateUser=()=>{
  data.user=JSON.parse(localStorage.getItem('xm-pro-user'))
}
</script>
<style>
.el-menu .is-active{
  background-color: #e6ecf7!important;
}
.el-sub-menu__title{
  background-color: white!important;
}
</style>