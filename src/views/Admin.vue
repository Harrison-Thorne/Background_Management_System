<template>
  <div>
    <div>
      <div class="card" style="margin-bottom: 5px">
        <el-input v-model="data.name" style="width:240px; margin-right:15px " placeholder="Please enter a search query" prefix-icon="Search"></el-input>
        <el-button type="primary" @click="load">Query</el-button>
        <el-button type="warning" @click="reset">Reset</el-button>
      </div>
      <div class="card" style="margin-bottom: 5px">
        <el-button type="primary" @click="handleAdd">Add</el-button>
        <el-button type="danger" @click="delBatch">Batch Delete</el-button>
<!--        <el-button type="success">Import</el-button>-->
<!--        <el-button type="success">Export</el-button>-->
      </div>
      <div class="card" style="margin-bottom: 5px" >
        <el-table :data="data.tableData" stripe  @selection-change="handleSelectionChange">
          <el-table-column type="selection"  width="55" />
          <el-table-column label="Username" prop="username"/>
          <el-table-column label="Avatar">
            <template #default="scope">
              <img v-if="scope.row.avatar" :src="scope.row.avatar" alt="" style="width:40px;height: 40px;border-radius: 50%">
            </template>
          </el-table-column>
          <el-table-column label="Name" prop="name"/>
          <el-table-column label="Operate">
            <template #default="scope">
              <el-button @click="handleUpdate(scope.row)" link type="primary" :icon="Edit">Edit</el-button>
              <el-button @click="del(scope.row.id)" link type="warning" :icon="Delete">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 15px">
          <el-pagination
              @size-change="load"
              @current-change="load"
              v-model:current-page="data.pageNum"
              v-model:page-size="data.pageSize"
              :page-sizes="[ 5 , 10 , 15, 20]"
              background
              layout="total, sizes, prev, pager, next, jumper"
              :total="data.total"
          />
        </div>
      </div>
    </div>

    <el-dialog v-model="data.formVisible" title="New Admin Information" width="500" destroy-on-close>
      <el-form ref="formRef" :rules="data.rules"  :model="data.form" label-width="100px" style="padding-right: 50px; padding-top: 15px">
        <el-form-item label="Username" prop="username">
          <el-input :disabled="data.form.id" v-model="data.form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Avatar">
          <el-upload
              action="http://localhost:9090/files/upload"
              list-type="picture"
              :on-success="handleAvatarSuccess"
          >
            <el-button type="primary">Upload Avatar</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="Name" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">Cancel</el-button>
          <el-button type="primary" @click="save">Confirm</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
import {Search,Edit,Delete} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const formRef = ref(null);  // 确保引用正确
const data =reactive({
  name:null,
  tableData: [], 
  pageNum:1,
  pageSize:10,
  total:0,
  formVisible:false,
  form:{},
  ids:[],
  rules:{
    username:[
      {required:true,message:'Please enter account',trigger:'blur'}
    ],
    name:[
      {required:true,message:'Please enter your name',trigger:'blur'}
    ],
  }
})
const load = () => {
  request.get('/admin/selectPage', {
        params:{
          name:data.name,
          pageNum:data.pageNum,
          pageSize:data.pageSize
        }
      }).then(res =>{
        data.tableData=res.data.list;
        data.total=res.data.total
  })
}
load()
const reset=()=>{
  data.name=null;
  load()
}
const handleAvatarSuccess=(res) =>{//后台返回的后端生成的图片链接
  console.log(res.data);
  data.form.avatar=res.data;
}
const handleAdd=()=>{
  data.formVisible=true;
  data.form={};
}

const save=()=>{//两种情况一个是新增一个是编辑
  formRef.value.validate((valid)=>{
    if(valid){
      data.form.id ? update() :add()
    }
  })
}
const add=()=>{
  request.post('/admin/add',data.form).then(res =>{
        if ( res.code === '200' ){
          data.formVisible=false
          ElMessage.success("Operating Successful")
          load()//新增后加载新数据
        }else{
          ElMessage.error(res.msg)
        }
      }
  )
}

const update=()=>{
  request.put('/admin/update',data.form).then(res =>{
        if ( res.code === '200' ){
          data.formVisible=false
          ElMessage.success("Operating Successful")
          load()//新增后加载新数据
        }else{
          ElMessage.error(res.msg)
        }
      }
  )
}

const handleUpdate=(row)=>{
  data.form=JSON.parse(JSON.stringify(row))//深拷贝一个新的对象来编辑
  data.formVisible=true
}

const del =(id)=> {
  ElMessageBox.confirm('Data cannot be restored after deletion. Confirm deletion?', 'Confirm Deletion', {type: 'warning'}).then(() => {
    request.delete('/admin/deleteById/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success("Operating Successful")
        load()//删除后加载新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

const handleSelectionChange =(rows) =>{ //返回所有选中的行对象数组
  //从选中行数组中的id组成一个新的数组
  data.ids=rows.map(row => row.id)
  console.log(data.ids)
}
const delBatch=()=>{
  if (data.ids.length === 0) {
    ElMessage.warning('Please Select Data!')
    return
  }
  ElMessageBox.confirm('Data cannot be restored after deletion. Confirm deletion?', 'Confirm Deletion', {type: 'warning'}).then(() => {
    request.delete('/admin/deleteBatch',{data:data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success("Operating Successful")
        load()//删除后加载新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}
</script>


<style scoped>

</style>