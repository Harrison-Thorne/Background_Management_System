


# Spring Boot 3 + Vue 3 后台管理系统

本项目为**前后端分离**的通用后台管理系统，涵盖用户/员工管理、部门管理、文章管理、数据统计、富文本编辑、文件上传等常用功能，适合课程设计、中后台产品或管理工具开发。



## 🏗️ 技术栈

| 前端                                    | 后端                                       | 数据库   |
|-----------------------------------------|--------------------------------------------|----------|
| Vue 3 / Vite / Element Plus / Axios     | Spring Boot 3 / MyBatis / PageHelper / Hutool / EasyExcel | MySQL 8  |

- 富文本：Element Plus + 富文本编辑器
- 图表展示：ECharts
- Excel导入导出：EasyExcel（后端）
- 其它：Spring MVC、MyBatis XML

---

## 📚 功能模块

- **管理员管理**：管理员信息的增删改查
- **员工管理**：员工信息管理、工号、部门、头像、简介等
- **部门管理**：部门信息维护
- **文章管理**：支持富文本内容，图片上传
- **数据展示**：统计报表（折线/柱状/饼图等）
- **文件上传/下载**：图片/Excel文件上传与下载
- **用户注册登录**：基础账户认证，密码修改
- **异常与统一响应处理**：全局异常与标准接口返回格式
- **国际化与美化**：中文界面，图标组件丰富

---

## 📁 目录结构(总体)

```

├── backend/                   # Spring Boot 后端
│   ├── controller/            # 控制层（如 AdminController、ArticleController）
│   ├── service/               # 业务层（如 AdminService、ArticleService）
│   ├── mapper/                # MyBatis Mapper接口（如 AdminMapper）
│   ├── entity/                # 实体类（如 Admin、Employee、Department）
│   ├── resources/
│   │   ├── mapper/            # MyBatis XML文件
│   │   └── application.yml    # 配置文件
│   └── config/                # 配置类（如 CorsConfig）
├── frontend/                  # Vue 3 前端
│   ├── src/
│   │   ├── views/             # 页面（如 Home.vue、Admin.vue）
│   │   ├── components/        # 公共组件
│   │   ├── router/            # 路由配置（如 index.js）
│   │   ├── utils/             # axios请求封装（如 request.js）
│   │   └── assets/            # 静态资源和样式
│   └── package.json
├── README.md                  # 项目说明文档
└── ...
```


---

## 🗄️ 数据库表结构简述

### `admin` 管理员表

| 字段名      | 类型      | 说明      |
|-------------|-----------|-----------|
| id          | int       | 主键      |
| username    | varchar   | 用户名    |
| password    | varchar   | 密码      |
| name        | varchar   | 姓名      |
| role        | varchar   | 角色      |
| avatar      | varchar   | 头像      |

### `employee` 员工表

| 字段名         | 类型      | 说明          |
|----------------|-----------|---------------|
| id             | int       | 主键          |
| username       | varchar   | 用户名        |
| password       | varchar   | 密码          |
| role           | varchar   | 角色（默认EMP）|
| name           | varchar   | 姓名          |
| sex            | varchar   | 性别          |
| no             | varchar   | 工号          |
| age            | int       | 年龄          |
| description    | varchar   | 个人简介      |
| department_id  | int       | 部门id        |
| avatar         | varchar   | 头像          |

### `department` 部门表

| 字段名    | 类型      | 说明      |
|-----------|-----------|-----------|
| id        | int       | 主键      |
| name      | varchar   | 部门名称  |

### `article` 文章表

| 字段名      | 类型      | 说明      |
|-------------|-----------|-----------|
| id          | int       | 主键      |
| title       | varchar   | 标题      |
| img         | varchar   | 封面图    |
| description | varchar   | 简介      |
| content     | longtext  | 富文本内容|
| time        | varchar   | 发布时间  |

---

## 💻 前端快速启动

1. 进入前端目录 `cd frontend`
2. 安装依赖 `npm install`
3. 启动开发服务 `npm run dev`
4. 访问 [http://localhost:5173](http://localhost:5173)

---

## 🚀 后端快速启动

1. 导入 `backend` 到 IDEA
2. 修改数据库配置（`resources/application.yml`）
3. 初始化数据库（执行 `manager_system.sql`）
4. 运行主类 `Application.java` 启动服务
5. 默认接口端口 [http://localhost:9090](http://localhost:9090)

---

## 🔗 路由结构说明（部分）

- `/login` 登录页
- `/register` 注册页
- `/manager/home` 管理端首页
- `/manager/employee` 员工信息
- `/manager/admin` 管理员信息
- `/manager/department` 部门信息
- `/manager/article` 文章管理
- `/manager/data` 统计报表
- `/manager/person` 个人信息
- `/manager/password` 修改密码

---

## 🧩 主要依赖和插件

- **后端依赖**
  - Spring Boot 3
  - MyBatis & MyBatis-Plus
  - PageHelper 分页插件
  - Hutool 工具库
  - EasyExcel
  - MySQL Connector/J
  - Lombok
  - Echarts (前端可视化)

- **前端依赖**
  - Vue 3
  - Element Plus（UI库）
  - Axios
  - ECharts

---

## ⚙️ 主要后端核心配置/处理

- **CORS** 跨域配置（`CorsConfig.java`）
- **全局异常捕获**（`GlobalExceptionHandler.java`）
- **自定义返回体**（`Result.java`）
- **统一接口格式/统一异常处理**
- **MyBatis多表关联与动态查询**（XML/注解）
- **文件上传/下载支持**
- **富文本内容存储与展示**

---

## 📝 常用命令/端口

- 前端启动：`npm run dev` 端口 `5173`
- 后端启动：运行主类，端口 `9090`
- 数据库：MySQL 8

---

## 🖼️ 示例功能页（建议截图补充）

| 页面/功能   | 说明            |
|-------------|-----------------|
| 登录注册页  | 账户管理        |
| 员工管理    | 列表/新增/编辑  |
| 部门管理    | 部门维护        |
| 文章管理    | 富文本/封面上传 |
| 数据统计    | 图表可视化      |

---

## 🙋 常见问题

- **依赖问题**：注意后端要求 JDK 17+，MySQL 8，前端 node 18+。
- **端口冲突**：修改 `vite.config.js` 和 `application.yml`。
- **数据库连接失败**：请检查 `application.yml` 数据库参数及 MySQL 运行状态。

---

## 🤝 贡献和交流

欢迎提交 PR、issue 和建议，也欢迎 fork 二次开发！

---

© 2025 缪言


