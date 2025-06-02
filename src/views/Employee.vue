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
        <!--文件从前端上传到后端先-->
        <el-upload
            style="display : inline-block; margin:0 10px"
            action="http://localhost:9090/employee/import"
            :show-file-list="false"
            :on-success="importSuccess"
        >
          <el-button type="info">Import</el-button>
        </el-upload>
        <el-button type="success" @click="exportData">Export</el-button>
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
          <el-table-column label="Sex" prop="sex"/>
          <el-table-column label="Job number" prop="no"/>
          <el-table-column label="Age" prop="age"/>
          <el-table-column label="Self-Description" prop="description" show-overflow-tooltip/>
          <el-table-column label="DepartmentName" prop="departmentName"/>
          <el-table-column label="Operate">
            <template #default="scope">
              <el-button @click="handleUpdate(scope.row)" link type="primary" :icon="Edit"></el-button>
              <el-button @click="del(scope.row.id)" link type="warning" :icon="Delete"></el-button>
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

    <el-dialog v-model="data.formVisible" title="New Staff Information" width="500" destroy-on-close>
      <el-form ref="formRef" :rules="data.rules"  :model="data.form" label-width="130px" style="padding-right: 50px; padding-top: 15px">
        <el-form-item label="Username" prop="username">
          <el-input :disabled="data.form.id" v-model="data.form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Department" prop="department">
          <el-select style="width:100%"  v-model="data.form.departmentId">
            <el-option v-for="item in data.departmentList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
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
        <el-form-item label="Sex">
          <el-radio-group  v-model="data.form.sex" >
            <el-radio value="male" label="male"></el-radio>
            <el-radio value="female" label="female"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Job Number" prop="no">
          <el-input v-model="data.form.no" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Age" >
          <el-input-number v-model="data.form.age" min="18" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Self-Intro" >
          <el-input :rows="3" type="textarea" v-model="data.form.description" autocomplete="off" />
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
import {Search, Edit, Delete, Plus} from "@element-plus/icons-vue"
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
  departmentList:[],
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
const load = () => {
  request.get('/employee/selectPage', {
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

request.get('/department/selectAll').then(res=>{
    data.departmentList=res.data
    }
)
const handleAdd=()=>{
  data.formVisible=true;
  data.form={};
}

const exportData=()=>{
  //导出数据 通过流下载excel 打开流的链接浏览器自动下载文件
  window.open('http://localhost:9090/employee/export')
}
const importSuccess=()=>{
  ElMessage.success('Import Success!')
  load()
}
const save=()=>{//两种情况一个是新增一个是编辑
  formRef.value.validate((valid)=>{
    if(valid){
      data.form.id ? update() :add()
    }
  })
}
const add=()=>{
  request.post('/employee/add',data.form).then(res =>{
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
  request.put('/employee/update',data.form).then(res =>{
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
const handleAvatarSuccess=(res) =>{//后台返回的后端生成的图片链接
  console.log(res.data);
  data.form.avatar=res.data;
}
const del =(id)=> {
  ElMessageBox.confirm('Data cannot be restored after deletion. Confirm deletion?', 'Confirm Deletion', {type: 'warning'}).then(() => {
    request.delete('/employee/deleteById/' + id).then(res => {
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
    request.delete('/employee/deleteBatch',{data:data.ids}).then(res => {
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