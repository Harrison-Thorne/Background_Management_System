<template>
  <div>
    <div style="margin-bottom: 20px">
      <RouterLink to="/manager/test">跳转到Test.vue</RouterLink>|
      <a href="/manager/test">通过a标签跳转到Test.vue</a>
    </div>
    <div style="margin-bottom: 20px">
      <el-button type="primary" @click="router.push('/manager/test')">push跳转到新的页面</el-button>
      <el-button type="primary" @click="router.replace('/manager/test')">replace跳转到新的页面</el-button>
    </div>
    <div style="margin-bottom: 20px">
      <el-button type="primary" @click="router.push({path:'/manager/test',query:{id:2,name:'tom'}})">路由传参等于1</el-button>
    </div>
    <div>
      <el-input v-model="data.input" style="width: 240px" placeholder="Please input"  :suffix-icon="Calendar"/>
      <el-input v-model="data.input" style="width: 240px" placeholder="Please input"  :prefix-icon="Calendar"/>
      <el-input v-model="data.descr" style="width: 240px"  type="textarea" placeholder="Please input"/>
    </div>

    <div>
      <el-select
          clearable
          multiple
          v-model="data.value"
          placeholder="Please Select"
          size="large"
          style="width: 240px"
      >
        <el-option
            v-for="item in data.options"
            :key="item.name"
            :label="item.name"
            :value="item.name"
        />
      </el-select>
    </div>

    <div style="margin:20px">
      <el-radio-group v-model="data.sex">
        <el-radio value="male">男</el-radio>
        <el-radio value="femal">女</el-radio>
      </el-radio-group>
      <el-radio-group marginv-model="radio1" size="large" v-model="data.tag">
        <el-radio-button label="What I post" value="What I post"/>
        <el-radio-button label="What I praise" value="What I praise"/>
        <el-radio-button label="What I collect" value="What I collec"/>
      </el-radio-group>
    </div>
    <div style="margin: 20px">
      <el-checkbox-group v-model="data.checkList">
        <el-checkbox v-for="item in data.cities" :key="item" :label="item" :value="item"/>
      </el-checkbox-group>
    </div>
    <div style="margin: 20px">
      <img src="@/assets/logo.svg" alt="" style="width:100px">
      <el-image :src="url" alt="" style="width:100px;margin-left:100px"/>
      <el-image :src="img" alt="" style="width:100px;margin-left:100px"/>
    </div>
    <div style="margin: 20px">
      <el-carousel height="400px" width="500px">
        <el-carousel-item v-for="item in data.imgs" :key="item">
          <img :src="item" alt="" style="width: 100%">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div>
      <el-table :data="data.tableData" stripe style="width: 100%">
        <el-table-column prop="date" label="Date" width="180" />
        <el-table-column prop="name" label="Name" width="180" />
        <el-table-column prop="address" label="Address" />
        <el-table-column label="操作">
          <template #default="scope">
            {{scope.row}}
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-pagination
            v-model:current-page="data.currentPage"
            :page-size="data.pageSize"
            layout="total, prev, pager, next"
            :total="data.tableData.length"
        />
      </div>
    </div>
  </div>
</template>
<script setup>
import {reactive} from "vue";
import { Calendar, Search } from '@element-plus/icons-vue'
import img from '@/assets/logo.svg'
import img2 from '@/assets/1.jpg'
import router from "@/router/index.js"
import request from "@/utils/request.js";
const data =reactive({
  id:router.currentRoute.value.query.id,
  input:null,
  descr:'lsdjddksjakdjskdjskdjskad',
  value:'',
  options:[{name:"1"},{name:"2"},{name:"3"}],
  sex:"male",
  tag:'What I praise',
  checkList:['Shanghai', 'Beijing'],
  cities : ['Shanghai', 'Beijing', 'Guangzhou', 'Shenzhen'],
  imgs:[img,img2],
  currentPage:1,
  pageSize:5,
  tableData:[
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-04',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-01',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
  ],
  employeeList:[]
})

request.get('/employee/selectAll').then(res => {
  console.log(res)
  data.employeeList=res.data
})
const url = 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
</script>
