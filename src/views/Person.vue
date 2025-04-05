<template>
  <div class="card" style="width: 60%; padding: 40px 0">
    <el-form
        ref="formRef"
        :rules="data.rules"
        :model="data.form"
        label-width="150px"
        style="padding-right: 50px; padding-top: 15px"
    >
      <el-form-item label="Avatar">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:9090/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="data.form.avatar" :src="data.form.avatar" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon">
            <Plus />
          </el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item label="Username" prop="username">
        <el-input disabled v-model="data.form.username" autocomplete="off" />
      </el-form-item>

      <el-form-item label="Name" prop="name">
        <el-input v-model="data.form.name" autocomplete="off" />
      </el-form-item>

      <template v-if="data.user.role === 'EMP'">
        <el-form-item label="Sex">
          <el-radio-group v-model="data.form.sex">
            <el-radio label="male">Male</el-radio>
            <el-radio label="female">Female</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="Job Number" prop="no">
          <el-input disabled v-model="data.form.no" autocomplete="off" />
        </el-form-item>

        <el-form-item label="Age">
          <el-input-number v-model="data.form.age" min="18" />
        </el-form-item>

        <el-form-item label="Self-Intro">
          <el-input type="textarea" :rows="3" v-model="data.form.description" />
        </el-form-item>
      </template>

      <div style="text-align: center">
        <el-button @click="updateUser" type="primary">Update Self Information</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
const formRef=ref()
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
import {Plus} from "@element-plus/icons-vue";

const data=reactive({
  user:JSON.parse(localStorage.getItem('xm-pro-user')),
  form:{},
  rules:{
    username:[
      {required:true,message:'Please enter account',trigger:'blur'}
    ],
    name:[
      {required:true,message:'Please enter your name',trigger:'blur'}
    ],
    no:[
      {required:true,message:'Please enter your job number',trigger:'blur'}
    ]
  }
})
const emit=defineEmits(['updateUser'])

const handleAvatarSuccess=(res) =>{//后台返回的后端生成的图片链接
  console.log(res.data);
  data.form.avatar=res.data;
}
const updateUser=()=>{
  if(data.user.role==="EMP"){
    request.put('/employee/update',data.form).then(res=>{
      if(res.code==='200'){
        ElMessage.success('Update Successfully')
        //更新缓存数据
        localStorage.setItem('xm-pro-user',JSON.stringify(data.form))
        //子组件发出更新父组件数据的请求
        emit('updateUser')
      }else{
        ElMessage.error(res.msg)
      }
    })
  }else{
    request.put('/admin/update',data.form).then(res=>{
      if(res.code==='200'){
        ElMessage.success('Update Successfully')
        //更新缓存数据
        localStorage.setItem('xm-pro-user',JSON.stringify(data.form))
        //子组件发出更新父组件数据的请求
        emit('updateUser')
      }else{
        ElMessage.error(res.msg)
      }
    })
  }
}
if(data.user.role==="EMP"){
  request.get('/employee/selectById/'+data.user.id).then(res=>{
      data.form=res.data
      }
  )
}else{
  data.form=data.user
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>