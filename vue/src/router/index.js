import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import AdminView from "@/views/AdminView.vue";
import LayoutView from "@/views/Layout.vue";
import RegisterView from "@/views/RegisterView";
import RecordView from "@/views/RecordView";
import FormView from "@/views/FormView";
import AuditView from "@/views/AuditView";
import StudentView from "@/views/StudentView";
import NoticeView from "@/views/NoticeView";
import LogView from "@/views/LogView";

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView
  },
  {
    path: '/',
    name: 'Layout',
    component: LayoutView,
    children: [
      {
        path: '',
        name: 'home',
        component: HomeView
      },
      {
        path: 'admin',
        name: 'admin',
        component: AdminView
      },
      {
        path: 'record',
        name: 'record',
        component: RecordView
      },
      {
        path: 'form',
        name: 'form',
        component: FormView
      },
      {
        path: 'audit',
        name: 'audit',
        component: AuditView
      },
      {
        path: 'student',
        name: 'student',
        component: StudentView
      },
      {
        path: 'notice',
        name: 'notice',
        component: NoticeView
      },
      {
        path: 'log',
        name: 'log',
        component: LogView
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to ,from, next) => {
  const user = localStorage.getItem("user");
  if (!user && to.path !== '/login' && to.path !== '/register') {
    return next("/login");
  }
  next();
})

export default router
