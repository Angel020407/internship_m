import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import AdminView from "@/views/AdminView.vue";
import LayoutView from "@/views/Layout.vue";
import RegisterView from "@/views/RegisterView";
import RecordView from "@/views/RecordView";

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
  if (to.path ==='/login') {
    next();
  }
  const user = localStorage.getItem("user");
  if (!user && to.path !== '/login') {
    return next("/login");
  }
  next();
})

export default router