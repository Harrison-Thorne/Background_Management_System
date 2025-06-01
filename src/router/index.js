import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path:'/', redirect:'/login'},
    { path:'/manager', component:()=>import('../views/Manager.vue'),children:[
        { path: 'home', name: 'home', meta:{title:'Home Page'},component: () => import('../views/Home.vue'),},
        { path: 'test', name: 'test', meta:{title:'Test Data Page'},component: () => import('../views/Test.vue'),},
        { path: 'data', name: 'data', meta:{title:'Data Display Page'},component: () => import('../views/Data.vue'),},
        { path: 'employee', name: 'employee', meta:{title:'Staff Information Page'},component: () => import('../views/Employee.vue'),},
        { path: 'admin', name: 'admin', meta:{title:'Admin Information Page'},component: () => import('../views/Admin.vue'),},
        { path: 'person', name: 'person', meta:{title:'Self Information Page'},component: () => import('../views/Person.vue'),},
        { path: 'password', name: 'password', meta:{title:'Change Password'},component: () => import('../views/Password.vue'),},
        { path: 'article', name: 'article', meta:{title:'Article Information'},component: () => import('../views/Article.vue'),},
        { path: 'department', name: 'department', meta:{title:'Department Information'},component: () => import('../views/Department.vue'),},
    ]},
      { path: '/login', name: 'Login', meta:{title:'Login System'},component: () => import('../views/Login.vue'),},
      { path: '/register', name: 'Register', meta:{title:'Register System'},component: () => import('../views/Register.vue'),},
      { path: '/404', name: 'NotFound', meta:{title:'Can not Found'},component: () => import('../views/404.vue'),},
      { path:'/:pathMatch(.*)',redirect:'/404'}
  ],
})

router.beforeEach((to,from,next)=>{
    document.title=to.meta.title
    next()
})
export default router
