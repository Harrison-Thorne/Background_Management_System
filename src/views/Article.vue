<template>
  <div>
    <div>
      <div class="card" style="margin-bottom: 5px">
        <el-input v-model="data.title" style="width:240px; margin-right:15px " placeholder="Please enter a title query" prefix-icon="Search"></el-input>
        <el-button type="primary" @click="load">Query</el-button>
        <el-button type="warning" @click="reset">Reset</el-button>
      </div>
      <div class="card" style="margin-bottom: 5px">
        <el-button type="primary" @click="handleAdd" v-if="role === 'ADMIN'">Add</el-button>
        <el-button type="danger" @click="delBatch" v-if="role === 'ADMIN'">Batch Delete</el-button>
      </div>
      <div class="card" style="margin-bottom: 5px" >
        <el-table :data="data.tableData" stripe  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" v-if="role === 'ADMIN'"/>
          <el-table-column label="Title" prop="title"/>
          <el-table-column label="Cover">
            <template #default="scope">
              <el-image v-if="scope.row.img" :src="scope.row.img" alt="" :preview-src-list=[scope.row.img] preview-teleported style="width:100px;height: 60px;"></el-image>
            </template>
          </el-table-column>
          <el-table-column label="Description" prop="description" show-overflow-tooltip/>
          <el-table-column label="Content">
            <template #default="scope">
              <el-button type="primary" @click="view(scope.row.content)">View Content</el-button>
            </template>
          </el-table-column>
          <el-table-column label="Release Time" prop="time"/>
          <el-table-column label="Operate">
            <template #default="scope">
              <el-button v-if="role === 'ADMIN'" @click="handleUpdate(scope.row)" link type="primary" :icon="Edit">Edit</el-button>
              <el-button v-if="role === 'ADMIN'" @click="del(scope.row.id)" link type="warning" :icon="Delete">Delete</el-button>
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

    <el-dialog v-model="data.formVisible" title="New Title Information" width="50%" destroy-on-close>
      <el-form ref="formRef"  :model="data.form" label-width="100px" style="padding-right: 50px; padding-top: 15px">
        <el-form-item label="Title" prop="title">
          <el-input  v-model="data.form.title" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Cover">
          <el-upload
              action="http://localhost:9090/files/upload"
              list-type="picture"
              :on-success="handleImgSuccess"
          >
            <el-button type="primary">Upload Cover</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input text="textarea" :rows="3" v-model="data.form.description" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Content">
          <div style="border: 1px solid #ccc; width: 100%">
            <Toolbar
                style="border-bottom: 1px solid #ccc"
                :editor="editorRef"
                :mode="mode"
            />
            <Editor
                style="height: 500px; overflow-y: hidden;"
                v-model="data.form.content"
                :mode="mode"
                :defaultConfig="editorConfig"
                @onCreated="handleCreated"
            />
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">Cancel</el-button>
          <el-button type="primary" @click="save">Confirm</el-button>
        </div>
      </template>
    </el-dialog>

    <!--close-on-click-modal当设置为 false 时，用户点击对话框外部的遮罩层时，对话框不会关闭。-->
    <el-dialog
        title="Content"
        v-model="data.viewVisible"
        width="50%"
        :close-on-click-modal="false"
        destroy-on-close
    >
      <div
          class="editor-content-view"
          style="padding: 20px"
          v-html="data.content"
      ></div>

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="data.viewVisible = false">
        Close
      </el-button>
    </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive} from "vue";
import {Edit,Delete} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { onBeforeUnmount, shallowRef } from "vue";
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'


const userInfo = JSON.parse(localStorage.getItem('xm-pro-user') || '{}')
const role = userInfo.role || 'EMP'   // 默认 EMP

const data = reactive({
  title: null,
  tableData: [],
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  ids: [],
  viewVisible: false,
  content: null,
})

/* wangEditor5 初始化开始 */
const baseUrl = 'http://localhost:9090'
//shallowRef用于保存第三方富文本编辑器的 JavaScript 实例，以便在 Vue 组件中对其进行程序化控制
const editorRef = shallowRef() // 编辑器实例，必须用 shallowRef shallowRef() 只会使其 .value 属性本身具有响应式。如果 .value 是一个对象，Vue 不会对其内部属性进行深层响应式转换
const mode = 'default'
const editorConfig = { MENU_CONF: {} }

// 图片上传配置 editorConfig 对象是 wangEditor 的一个配置容器，通过修改其 MENU_CONF 属性，你可以定制编辑器的各种行为，例如图片上传、视频上传、链接插入等。
editorConfig.MENU_CONF['uploadImage'] = {
  server: baseUrl + '/files/wang/upload', // 服务端图片上传接口
  fieldName: 'file' // 服务端图片上传接口参数
}


// 会在 Vue 组件即将被卸载（unmounted）或销毁之前被调用 清理wangEditor在 DOM 中创建的所有元素和绑定的事件，释放内存。
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

// 记录 editor 实例，重要！
const handleCreated = (editor) => {
  editorRef.value = editor//editorRef 就持有了富文本编辑器的 JavaScript 实例，你可以在组件的生命周期钩子中（如 onBeforeUnmount）或者其他需要操作编辑器的地方使用它。
}
/* wangEditor5 初始化结束 */
const view=(content)=>{
  data.content=content
  data.viewVisible=true
}

const load = () => {
  request.get('/article/selectPage', {
        params:{
          title: data.title,
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
  data.title=null;
  load()
}
const handleImgSuccess=(res) =>{ //后台返回的后端生成的图片链接存在data.form.img里面
  console.log(res.data);
  data.form.img=res.data;
}
const handleAdd=()=>{
  data.formVisible=true;
  data.form={};
}

const save=()=>{//两种情况一个是新增一个是编辑
  data.form.id ? update() :add()
}
const add=()=>{
  request.post('/article/add',data.form).then(res =>{
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
  request.put('/article/update',data.form).then(res =>{
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
    request.delete('/article/deleteById/' + id).then(res => {
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
    request.delete('/article/deleteBatch',{data:data.ids}).then(res => {
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