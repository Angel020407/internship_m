<template>
    <div>
      <div>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="number" label="学工号"></el-table-column>
          <el-table-column prop="password" label="密码"></el-table-column>
          <el-table-column prop="sex" label="性别"></el-table-column>
          <el-table-column prop="age" label="年龄"></el-table-column>
          <el-table-column prop="phone" label="电话"></el-table-column>
          <el-table-column prop="school" label="所属学校"></el-table-column>
          <el-table-column prop="eschool" label="实习学校"></el-table-column>
          <el-table-column prop="role" label="角色"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
              <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
                <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div>
        <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form">
            <el-form-item label="姓名" label-width="15%">
              <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="学工号" label-width="15%">
              <el-input v-model="form.number" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="密码" label-width="15%">
              <el-input v-model="form.password" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="性别" label-width="15%">
              <el-radio v-model="form.sex" label="男">男</el-radio>
              <el-radio v-model="form.sex" label="女">女</el-radio>
            </el-form-item>
            <el-form-item label="年龄" label-width="15%">
              <el-input v-model="form.age" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="电话" label-width="15%">
              <el-input v-model="form.phone" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="学校" label-width="15%">
              <el-input v-model="form.school" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
            <el-form-item label="实习学校" label-width="15%">
              <el-input v-model="form.eschool" autocomplete="off" style="width: 90%"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="submit">确定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </template>
  
  <script>
  import request from "@/utils/request";
  
  export default {
    data() {
      return {
        tableData: [],
        dialogFormVisible: false,
        form: {}
      }
    },
    created() {
      this.findBySearch();
    },
    methods: {
      findBySearch() {
        request.get("/person/search").then(res => {
          if (res.code === '0') {
            this.tableData = res.data.list;
          } else {
            this.$message({
              message: res.msg,
              type: 'success'
            });
          }
        })
      },
      add() {
        this.form = {};
        this.dialogFormVisible = true;
      },
      edit(obj) {
        this.form = obj;
        this.dialogFormVisible = true;
      },
      submit() {
        request.post("/person", this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              message: '操作成功',
              type: 'success'
            });
            this.dialogFormVisible = false;
            this.findBySearch();
          } else {
            this.$message({
              message: res.msg,
              type: 'error'
            });
          }
        })
      },
      del(id) {
        request.delete("/person/" + id).then(res => {
          if (res.code === '0') {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.findBySearch();
          } else {
            this.$message({
              message: res.msg,
              type: 'success'
            });
          }
        })
      }
    }
  }
  </script>
  