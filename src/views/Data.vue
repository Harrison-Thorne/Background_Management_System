<template>
  <div>
    <div>
      <div class="card" style="margin-bottom: 5px">
        <el-input v-model="data.name" style="width:240px; margin-right:15px " placeholder="Please enter a search query" prefix-icon="Search"></el-input>
        <el-button type="primary">Query</el-button>
        <el-button type="warning">Reset</el-button>
      </div>
      <div class="card" style="margin-bottom: 5px">
        <el-button type="primary">Add</el-button>
        <el-button type="warning">Batch Delete</el-button>
<!--        <el-button type="success">Import</el-button>-->
<!--        <el-button type="success">Export</el-button>-->
      </div>
      <div class="card" style="margin-bottom: 5px" >
        <el-table :data="data.tableData" stripe>
          <el-table-column label="Date" prop="date"/>
          <el-table-column label="Name" prop="name"/>
          <el-table-column label="Address" prop="address"/>
        </el-table>
        <div style="margin-top: 15px">
          <el-pagination
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
  </div>
</template>

<script setup>
import {reactive} from "vue";
import {Search} from "@element-plus/icons-vue"
const data =reactive({
  name:null,
  tableData: [
    { id: 1, date: '2025-01-01', name: 'Tom', address: 'New York, USA' },
    { id: 2, date: '2025-01-02', name: 'Emma', address: 'London, UK' },
    { id: 3, date: '2025-01-03', name: 'Zhang Wei', address: 'Beijing, China' },
    { id: 4, date: '2025-01-04', name: 'Anna', address: 'Berlin, Germany' },
    { id: 5, date: '2025-01-05', name: 'Li Na', address: 'Shanghai, China' },
    { id: 6, date: '2025-01-06', name: 'John', address: 'Sydney, Australia' },
    { id: 7, date: '2025-01-07', name: 'Yuki', address: 'Tokyo, Japan' }
  ],
  pageNum:1,
  pageSize:10,
  total:50,
})
</script>

<style scoped>

</style>