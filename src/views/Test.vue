<template>
  <div>
    <div>
      <el-button type="primary" @click="router.back()">返回主页</el-button>
    </div>
    <div style="background-color: red">
      hello world
    </div>
    <div style="font-size: 30px">
      {{data.a}}
    </div>
    <div>
      <input v-model="data.a">
    </div>
    <div>
      <span style="color:red" v-if="data.name==='Tom'">hello_Tom</span>
    </div>
    <div>
      <div @click="show(item)" style="width:300px; height:300px;background-color: aqua;margin-bottom: 10px" v-for="item in data.arr">{{item}}</div>
    </div>
    <select style="width:200px">
      <option v-for="item in data.arr">{{item}}</option>
    </select>
    <div>
      <button @click="click1">pick me</button>
    </div>
    <div style="margin-bottom: 30px">
      <div :style="data.box"></div>
      <div>
        <img :src="data.img" alt="">
      </div>
    </div>
    <div style="margin:30px">
      <el-button>Default</el-button>
      <el-button type="primary">Primary</el-button>
      <el-button type="success">Success</el-button>
      <el-button type="info">Info</el-button>
      <el-button type="warning">Warning</el-button>
      <el-button type="danger">Danger</el-button>
    </div>
    <span style="margin:30px">
      <el-icon :size="20">
        <Edit />
      </el-icon>
    </span>
    <span style="margin:30px">
      <el-icon :size="20" style="top:4px">
        <View />
      </el-icon>
      100
    </span>
    <el-button type="primary" :icon="Delete" />
  </div>
</template>

<script setup>
import {reactive,ref,onMounted}  from "vue"
import {Delete} from "@element-plus/icons-vue"
import router from "@/router/index.js"
const a= ref(1)
const data=reactive({
  a:123,
  b:456,
  name:'Tom',
  arr:[1,2,3],
  box:{
    width:'100px',
    height:'100px',
    backgroundColor:'red'
  },
  img:'https://img-s.msn.cn/tenant/amp/entityid/AA1p7Xvi?w=612&h=304&q=60&m=6&f=jpg&u=t'
})
const click1 = () =>{
  alert("lucky")
}
const show = (value) =>{
  alert(value)
}
onMounted(()=>{
  console.log("have done")
})
</script>
