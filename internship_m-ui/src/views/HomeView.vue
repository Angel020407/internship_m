<template>
  <div style="height:100%;">
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']" style="min-height:100%; overflow-x:hidden"
         background-color=rgb(48,65,86)
         text-color=#ccc
         active-text-color=red
        >
        <div style="height:60px; line-height:60px; text-align:center">
          <img src="../assets/logo.png" style="width:20px;position:relative;top:5px;margin-right:5px"/>
          <b style="color:white">后台管理系统</b>
        </div>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航一</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>导航三</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      
      <el-container>
        <el-header style="text-align: right; font-size: 12px; border-bottom: 1px solid red; line-height:60px">
          <el-dropdown style="width:100px; cursor:pointer">
           <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left:5px"></i>
              <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>       
        </el-header>
        
        <el-main>
         <div style="padding:10px">
          <el-input style="width:250px" suffix-icon="el-icon-search" placeholder="请输入名称搜索"></el-input>
          <el-input style="width:250px" suffix-icon="el-icon-email" placeholder="请输入邮箱搜索"></el-input>
          <el-input style="width:250px" suffix-icon="el-icon-position" placeholder="请输入地址搜索"></el-input>
          <el-button style="margin-left:5px" type="primary">搜索</el-button>
        </div>
        <div style="margin:10px">
           <el-button type="primary">新增<i class="el-icon-circle-plus"></i></el-button>
           <el-button type="danger">批量删除<i class="el-icon-remove"></i></el-button>
           <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
           <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
        </div>
          <el-table :data="tableData">
            <el-table-column prop="id" label="ID " width="80">
            </el-table-column>
            <el-table-column prop="username" label="姓名 " width="80">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="120">
            </el-table-column>
             <el-table-column prop="phone" label="电话">
            </el-table-column>
            <el-table-column prop="nickname" label="昵称">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>         
            <el-table-column fixed="right" label="操作" width="240">                         
              <template slot-scope="scope">
                <el-button type="success" size="small" icon="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
                <el-button type="danger" size="small"  icon="el-icon-delete">删除</el-button>
              </template>
            </el-table-column>         
          </el-table>
          <div style="padding:10px">
           <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
           </el-pagination>
         </div>
        </el-main>
        
      </el-container>
    </el-container>
  </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import HelloWorld from '/src/components/HelloWorld.vue'
  
  export default {
    name: 'HomeView',
    methods: {
        handleSizeChange(val) {
          console.log(`每页 ${val} 条`);
          this.pageSize=val;
        },
        handleCurrentChange(val) {
          console.log(`当前页: ${val}`);
          this.pageNum=val;
        }
      },
    data(){    
        return {
          tableData:[],
          total:0,
          pageNum:1,
          pageSize:5
        }
    },
      created(){
        //请求分页查询数据
        this.load();
    },
     methods: {
        edit(row){
        console.log(row);
        },  
        handleSizeChange(val) {/*传递过来当前是第几页*/
          console.log(`每页 ${val} 条`);
          this.pageSize=val;  //获取当前每页显示条数
          this.load();
        },
        handleCurrentChange(val) {/*传递过来当前是第几页*/
          console.log(`当前页: ${val}`);
          this.pageNum=val;   //获取当前第几页
          this.load();
        },
        //将请求数据封装为一个方法
        load() {
          //请求分页查询数据
            fetch("http://localhost:8081/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"").then(res=>res.json()).then(res=>{
            console.log(res)
            this.tableData=res.data
            this.total=res.total
            })
          }
      },
  
  }
  </script>
  <style>  
    .el-main {
      text-align: left;
    }
  </style>
  