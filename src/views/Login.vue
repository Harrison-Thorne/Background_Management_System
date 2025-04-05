<template>
  <div class="login-container">
    <div class="login-box">
      <div style="padding: 20px; background-color: white; margin-left: 130px; box-shadow: 0 0 10px rgba(0,0,0,0.2)">
        <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 450px">
          <div style="margin-bottom: 30px; font-size: 24px; color: cornflowerblue; font-weight: bold">
            Welcome to the Admin Dashboard
          </div>
          <el-form-item prop="username">
            <el-input
                size="large"
                v-model="data.form.username"
                placeholder="Enter your username"
                prefix-icon="User"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
                show-password
                size="large"
                v-model="data.form.password"
                placeholder="Enter your password"
                prefix-icon="Lock"
            ></el-input>
          </el-form-item>
          <el-form-item prop="role">
            <el-select v-model="data.form.role" style="width: 100%" size="large">
              <el-option value="ADMIN" label="admin"></el-option>
              <el-option value="EMP" label="employee"></el-option>
            </el-select>
          </el-form-item>
          <div style="margin-bottom: 20px">
            <el-button @click="login" size="large" style="width: 100%" type="primary">Sign In</el-button>
          </div>
          <div style="text-align: right">
            Don't have an account?
            <a href="/register" style="color: cornflowerblue; text-decoration: none">Register now</a>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>


<script setup>
import {reactive,ref} from "vue";
import {Management, User,Lock} from "@element-plus/icons-vue"
import {ElMessage} from "element-plus";
import request from "@/utils/request.js";


const data=reactive({
  form:{role:'ADMIN'},
  rules:{
    username:[
      {required:true,message:'Please enter your account',trigger:'blur'}
    ],
    password:[
      {required:true,message:'Please enter your password',trigger:'blur'}
    ],
  }
})
const login =()=>{
  formRef.value.validate((valid)=>{
    if(valid){
      request.post('/login',data.form).then(res =>{
        if(res.code==='200'){
          //存储数据
          localStorage.setItem('xm-pro-user',JSON.stringify(res.data))
          ElMessage.success('Login successfully')
          location.href='/manager/home'
        }
        else{
          ElMessage.error((res.msg))
        }
      })
    }
  })
}
const formRef=ref()
</script>

<style scoped>
.login-container{
  height:100vh;
  overflow: hidden;
  background-image: url("@/assets/bg.jpg");
  background-size: 80% 100%;
  background-position: 0 -40px;
}
.login-box{
  background-color: white;
  width:50%;
  right: 0;
  height: 100%;
  display: flex;
  align-items: center;
  position:absolute;
}
</style>