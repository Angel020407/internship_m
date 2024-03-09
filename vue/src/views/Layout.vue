<template>
  <div>
    <el-container>
      <el-header style="background-color: #4c535a">
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px;">
        <span style="font-size: 20px; margin-left: 15px; color: white">学生实习信息管理系统</span>
        <el-dropdown style="float: right; height: 60px; line-height: 60px">
          <span class="el-dropdown-link" style="color: white; font-size: 16px">{{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="logout">退出登录</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
    </el-container>

    <el-container>
      <el-aside style="overflow: hidden; min-height: 100vh; background-color: #545c64; width: 250px">
        <el-menu :default-active="$route.path" router background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i><span>用户管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin" v-if="user.role ==='ROLE_ADMIN'">用户信息</el-menu-item>
              <el-menu-item index="/student" v-if="user.role !='ROLE_STUDENT'">学生信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i><span>教学设计管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/record">听课记录提交</el-menu-item>
              <el-menu-item index="3-2">yyy信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-location"></i><span>请销假管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/audit">请假审核</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="5" v-if="user.role ==='ROLE_ADMIN'">
            <template slot="title">
              <i class="el-icon-location "></i><span>公告管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/notice">系统公告</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Layout",

  data () {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },

  methods: {
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    }
  }

}
</script>

<style>
.el-menu{
  border-right: none !important;
}
</style>