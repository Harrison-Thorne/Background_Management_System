<template>
  <div style="padding: 24px;">
    <el-card>
      <h2>Welcome to the Background Management System</h2>
      <p>
        This system provides a unified platform for managing internal announcements, visualizing employee and article data with charts, and handling all major business modules, including:
      </p>
      <ul>
        <li>System Announcements</li>
        <li>Employee statistics and article data visualization with charts</li>
        <li>Article Management (with rich text editor for editing content)</li>
        <li>Department Management</li>
        <li>User and Administrator Management</li>
        <li>Personal Information Management</li>
        <li>Password Change</li>
        <li>Logout</li>
      </ul>
      <el-divider />
      <h4>User Permissions</h4>
      <ul>
        <li>
          <b>ADMIN</b> users have access to all system features, including department/user/administrator management, and full article CRUD operations.
        </li>
        <li>
          <b>EMP</b> users <b>cannot</b> manage departments, users, or administrators, and can only <b>view articles</b> (not add, edit, or delete).
        </li>
      </ul>
      <el-divider />
      <h4>Key Features</h4>
      <ul>
        <li>
          <b>Article editing</b> supports a rich text editor for advanced content formatting.
        </li>
        <li>
          <b>User data batch import/export</b> supported in <b>xlsx</b> format. When importing, please follow the sample format below.
          <br />
          <div style="margin-top: 12px;">
            <b>Sample format (employee_sample.xlsx):</b>
            <el-table :data="sampleData" size="small" border style="width: 920px; margin-top: 6px;">
              <el-table-column prop="Username" label="Username" />
              <el-table-column prop="Password" label="Password" />
              <el-table-column prop="Name" label="Name" />
              <el-table-column prop="Sex" label="Sex" />
              <el-table-column prop="No" label="No" />
              <el-table-column prop="Age" label="Age" />
              <el-table-column prop="Description" label="Description" />
              <el-table-column prop="DepartmentName" label="DepartmentName" />
              <el-table-column prop="DepartmentId" label="DepartmentId" />
            </el-table>
          </div>
        </li>
        <li>
          <b>Avatar</b> and <b>article images</b> support upload and download. Image size is limited to <b>1MB</b> or less.
        </li>
      </ul>
      <el-divider />
      <p>
        For more information, please use the navigation menu on the left. If you need assistance, please contact your system administrator.
      </p>
    </el-card>
  </div>
</template>

<script setup>


const sampleData = [
  { Username: 'william841', Password: 'Pass1704', Name: 'William', Sex: 'Male', No: 'EMP10000',Age:'24',Description:'Problem solver.',DepartmentName:'Finance',DepartmentId:'4' },
]
</script>
