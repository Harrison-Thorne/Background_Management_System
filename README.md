# Spring Boot 3 + Vue 3 管理系统

本项目是基于 **Spring Boot 3** 和 **Vue 3** 的前后端分离管理系统，集成了富文本编辑器、Excel 数据导入导出、数据统计图表等功能，适用于中后台管理系统的开发需求。

## ✨ 项目功能

### ✅ 富文本编辑器
- 支持富文本内容的编辑与展示
- 富文本内容保存至数据库，支持持久化
- 图片上传、文字排版、格式化编辑

### ✅ 数据导入导出
- 支持 Excel 表格的批量导入与导出
- 导入时支持数据校验与错误提示
- 导出支持筛选与批量导出功能

### ✅ 数据统计图表
- 使用 ECharts 实现可视化数据展示
- 支持柱状图、饼图、折线图等多种图表
- 图表数据实时更新，提升数据分析效率

## 🖥️ 技术栈

| 前端 | 后端 | 数据库 |
|------|------|--------|
| Vue 3 + Vite + Element Plus | Spring Boot 3 + MyBatis Plus | MySQL 8 |

其他技术包括：
- Axios（前端 HTTP 请求库）
- ECharts（数据可视化）
- EasyExcel（Excel 文件处理）
- Lombok、JPA、Spring Security（后端开发）

## 🧩 项目结构

```
├── backend/           # Spring Boot 后端代码
│   ├── controller/    # 控制层
│   ├── service/       # 业务逻辑
│   ├── entity/        # 实体类
│   └── config/        # 配置类
├── frontend/          # Vue 3 前端代码
│   ├── src/
│   │   ├── views/     # 页面视图
│   │   ├── components/# 公共组件
│   │   ├── api/       # 与后端交互的 API
│   │   └── router/    # 路由配置
```

## 🔧 环境配置

开发环境推荐如下：

- Node.js v20.18.3
- npm v11.2.0
- MySQL 8.0.37
- Navicat Premium 16（数据库工具）
- JDK 17+
- IntelliJ IDEA 2023.3.6
- Vue 开发推荐使用 VSCode

## 🚀 快速启动

### 后端启动

1. 导入 `src_springboot` 目录至 IDEA
2. 配置数据库连接（`application.yml`）
3. 创建数据库并执行 SQL 脚本（若有）
4. 启动主类 `Application.java`

### 前端启动

```bash
# 进入前端目录
cd src_vue

# 安装依赖
npm install

# 运行项目
npm run dev
```

默认端口：
- 前端：http://localhost:5173
- 后端：http://localhost:8080

## 📦 功能预览（截图可自行添加）

| 功能页面     | 说明           |
|--------------|----------------|
| 富文本编辑器 | 可编辑内容并保存 |
| 导入导出     | 支持 Excel 操作 |
| 数据图表     | 动态展示统计图 |

## 🤝 贡献 & 建议

欢迎提 Issue、PR 或建议。希望这个项目能帮你快速搭建一个功能丰富的中后台系统。

---

© 2025 By 缪言
