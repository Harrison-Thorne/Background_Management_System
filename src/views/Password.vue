<template>
  <div class="card" style="width:60%; padding:40px 20px;">
    <el-form ref="formRef" :rules="data.rules"  :model="data.form" label-width="200px" style="padding-right: 50px; padding-top: 15px">
      <el-form-item label="Original Password" prop="password">
        <el-input show-password v-model="data.form.password" autocomplete="off" placeholder="Please enter the original password"/>
      </el-form-item>
      <el-form-item label="New Password" prop="newPassword">
        <el-input show-password v-model="data.form.newPassword" autocomplete="off" placeholder="Please enter the new password"/>
      </el-form-item>
      <el-form-item label="Confirm New Password" prop="confirmPassword" required>
        <el-input show-password v-model="data.form.confirmPassword" autocomplete="off" placeholder="Please enter the new password again"/>
      </el-form-item>
      <div style="text-align: center">
        <el-button @click="updatePassword" type="primary">Update Password</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>

import {ref,reactive} from "vue";
import {ElMessage} from "element-plus";
import request from "@/utils/request.js";

const validateConfirmPassword = (rule, value, callback) => {
  const newPass = data.form.newPassword;
  const confirmPass = value;

  if (!newPass && !confirmPass) {
    callback(new Error("New Password and Confirm Password cannot both be empty"));
  } else if (newPass && !confirmPass) {
    callback(new Error("Please confirm the password again"));
  } else if (confirmPass !== newPass) {
    callback(new Error("Two inputs don't match!"));
  } else {
    callback();
  }
};

const formRef=ref()
const data =reactive({
  user:JSON.parse(localStorage.getItem('xm-pro-user')),
  form:{},
  rules: {
    password: [
      {required: true, message: 'Please enter Original password', trigger: 'blur'}
    ],
    newPassword: [
      {required: true, message: 'Please enter new password', trigger: 'blur'}
    ],
    confirmPassword: [
      {validator:validateConfirmPassword, trigger: 'blur'}
    ],
  }
})
const updatePassword=()=>{
  data.form.id = data.user.id
  data.form.role=data.user.role
  formRef.value.validate((valid)=>{
    if(valid){
      request.put('/updatePassword',data.form).then(res=>{
        if(res.code==='200'){
          ElMessage.success("Modify Successfully")
          localStorage.removeItem('xm-pro-user')
          location.href='/login'
        }else{
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
</script>

<style scoped>

</style>