<template>
  <div>
    <!-- 搜索栏 -->
    <div style="margin-bottom: 16px; display: flex; justify-content: flex-end">
      <el-input
          v-model="searchKeyword"
          placeholder="搜索姓名"
          style="width: 240px"
          clearable
          @input="filterData"
      />
    </div>

    <!-- 表格展示 -->
    <el-table
        :data="paginatedData"
        stripe
        border
        style="width: 100%; margin-bottom: 20px"
        @sort-change="handleSortChange"
    >
      <el-table-column prop="date" label="日期" sortable="custom" width="180" />
      <el-table-column prop="name" label="姓名" sortable="custom" width="180" />
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="content" label="内容">
        <template #default="scope">
          <div v-html="scope.row.content"></div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button type="primary" size="small" @click="openEditor(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
        v-model:current-page="data.currentPage"
        :page-size="data.pageSize"
        layout="total, prev, pager, next"
        :total="filteredData.length"
        @current-change="handlePageChange"
    />

    <!-- 编辑弹窗 -->
    <el-dialog v-model="editorVisible" title="编辑内容" width="600px" destroy-on-close>
      <el-form :model="editForm" label-width="80px">
        <el-form-item label="日期">
          <el-input v-model="editForm.date"/>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="editForm.name"/>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="editForm.address"/>
        </el-form-item>
        <el-form-item label="内容">
          <Toolbar :editor="editorRef" style="border-bottom: 1px solid #ccc"/>
          <Editor
              v-model="editForm.content"
              :defaultConfig="editorConfig"
              mode="default"
              style="height: 200px; border: 1px solid #ccc; margin-top: 10px"
              @onCreated="handleEditorCreated"
          />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="editorVisible = false">取消</el-button>
        <el-button type="primary" @click="saveEdit">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, reactive, computed, shallowRef, onBeforeUnmount} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import '@wangeditor/editor/dist/css/style.css'
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'

// 数据源
const data = reactive({
  currentPage: 1,
  pageSize: 5,
  sortProp: '',
  sortOrder: '',
  tableData: [
    {
      id: 1,
      date: '2024-04-10',
      name: 'Alice',
      address: '123 Main St',
      content: '<p>Hello World</p>',
    },
    {
      id: 2,
      date: '2024-04-11',
      name: 'Bob',
      address: '456 Park Ave',
      content: '<p>Nice to meet you</p>',
    },
    {
      id: 3,
      date: '2024-04-12',
      name: 'Carol',
      address: '789 Broadway',
      content: '<p>This is great!</p>',
    },
  ],
})

const searchKeyword = ref('')
const filteredData = computed(() => {
  return data.tableData.filter(row =>
      row.name.toLowerCase().includes(searchKeyword.value.toLowerCase())
  )
})

const sortedData = computed(() => {
  if (!data.sortProp || !data.sortOrder) return filteredData.value

  return [...filteredData.value].sort((a, b) => {
    const prop = data.sortProp
    if (data.sortOrder === 'ascending') {
      return a[prop] > b[prop] ? 1 : -1
    } else {
      return a[prop] < b[prop] ? 1 : -1
    }
  })
})

const paginatedData = computed(() => {
  const start = (data.currentPage - 1) * data.pageSize
  const end = start + data.pageSize
  return sortedData.value.slice(start, end)
})

// 富文本编辑器
const editorVisible = ref(false)
const editorRef = shallowRef(null)
const editForm = reactive({
  id: null,
  date: '',
  name: '',
  address: '',
  content: '',
})
const editorConfig = {
  placeholder: '请输入内容...',
}

function handleEditorCreated(editor) {
  editorRef.value = editor
}

onBeforeUnmount(() => {
  if (editorRef.value) editorRef.value.destroy()
})

function openEditor(row) {
  Object.assign(editForm, row)
  editorVisible.value = true
}

function saveEdit() {
  const index = data.tableData.findIndex(item => item.id === editForm.id)
  if (index !== -1) {
    data.tableData[index] = {...editForm}
    ElMessage.success('保存成功')
  }
  editorVisible.value = false
}

function del(id) {
  ElMessageBox.confirm('确定要删除这条记录吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  })
      .then(() => {
        data.tableData = data.tableData.filter(item => item.id !== id)
        ElMessage.success('删除成功')
      })
      .catch(() => {
        ElMessage.info('已取消删除')
      })
}

function handlePageChange(page) {
  data.currentPage = page
}

function filterData() {
  data.currentPage = 1
}

function handleSortChange({prop, order}) {
  data.sortProp = prop
  data.sortOrder = order
}
</script>
