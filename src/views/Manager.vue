<template>
  <div>
    <!-- 顶部栏 -->
    <div style="height: 60px; background-color: #4285f4;display: flex; align-items: center;">
      <div style="width: 500px; display: flex;align-items: center; padding-left: 10px">
        <img style="width: 40px" src="@/assets/management.png" alt="">
        <span style="font-size: 20px;color:#f5f5f5;">Background Management System
        </span>
      </div>
      <div style="flex:1"></div>
      <div style="width:fit-content; display: flex;align-items: center;padding-right: 10px">
        <img :src="data.user.avatar||'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="" style="width: 40px;height: 40px;border-radius: 50%">
        <span style="color:white;margin-left: 5px">{{data.user.name}}</span>
      </div>
    </div>

    <!-- 内容区域 -->
    <div style="display: flex; height: calc(100vh - 60px);">
      <!-- 左侧菜单 -->
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
        <el-sub-menu index="1">
          <template #title>
            <el-icon><User /></el-icon>
            <span>User Management</span>
          </template>
          <el-menu-item index="/manager/Admin">Admin Information</el-menu-item>
          <el-menu-item index="/manager/Employee">Staff Information</el-menu-item>
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

      <!-- 主体内容 -->
      <div style="flex: 1; padding: 20px; width:0; background-color: #eaeaf1">
        <RouterView @updateUser="updateUser"/>
      </div>
    </div>
  </div>
</template>
<script setup>

import {DataAnalysis, House, SwitchButton, User, UserFilled} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import {reactive} from "vue";

const data=reactive({
  user:JSON.parse(localStorage.getItem('xm-pro-user'))
})
const  logout =()=>{
  localStorage.removeItem('xm-pro-user')//清楚缓存数据
  location.href='/login'
}
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