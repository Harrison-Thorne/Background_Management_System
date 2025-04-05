<template>
  <div class="login-container">
    <div class="login-box">
      <div style="padding: 20px; background-color: white; margin-left: 130px; box-shadow: 0 0 10px rgba(0,0,0,0.2)">
        <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 450px">
          <div style="margin-bottom: 30px; font-size: 24px; color: cornflowerblue; font-weight: bold">
            Welcome to the Register Dashboard
          </div>
          <el-form-item prop="username">
            <el-input
                size="large"
                v-model="data.form.username"
                placeholder="Enter your username"
                prefix-icon="User"
            ></el-input>
          </el-form-item>
          <el-form-item prop="no">
            <el-input
                size="large"
                v-model="data.form.no"

                placeholder="Enter your Job Number"
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
          <el-form-item prop="confirmPassword">
            <el-input
                show-password
                size="large"
                v-model="data.form.confirmPassword"
                placeholder="Confirm your password"
                prefix-icon="Lock"
            ></el-input>
          </el-form-item>
          <div style="margin-bottom: 20px">
            <el-button @click="register" size="large" style="width: 100%" type="primary">Sign Up</el-button>
          </div>
          <div style="text-align: right">
            Already have an account?
            <a href="/login" style="color: cornflowerblue; text-decoration: none">Sign In</a>
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

const validatePass = (rule, value, callback) => {
  if (!value) {
    callback(new Error('Please confirm the password again'))
  }  else if (value !== data.form.password) {
    callback(new Error("Two inputs don't match!"))
  } else {
    callback()
  }
}

const data=reactive({
  form:{},
  rules:{
    username:[
      {required:true,message:'Please enter your account',trigger:'blur'}
    ],
    no:[
      {required:true,message:'Please enter your Job number',trigger:'blur'}
    ],
    password:[
      {required:true,message:'Please enter your password',trigger:'blur'}
    ],
    confirmPassword:[
      {validator: validatePass, trigger:'blur'}
    ]
  }
})
const register =()=>{
  formRef.value.validate((valid)=>{
    if(valid){
      request.post('/register',data.form).then(res =>{
        if(res.code==='200'){
          ElMessage.success('Sign up successfully')
          location.href='/login'
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