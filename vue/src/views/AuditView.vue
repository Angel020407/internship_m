<template>
  <div>
    <div style="margin-bottom: 15px">
      <el-input v-model="params.name" style="width: 200px" placeholder="请输入请假原由"></el-input>
      <el-button type="warning" style="margin-left: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="warning" style="margin-left: 10px" @click="reset()">清空</el-button>
      <el-button type="primary" style="margin-left: 10px" @click="add()" v-if="user.role === 'ROLE_STUDENT' || user.role === 'ROLE_ADMIN'">新增</el-button>
    </div>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="请假原由"></el-table-column>
        <el-table-column prop="time" label="请假日期"></el-table-column>
        <el-table-column prop="day" label="截止日期"></el-table-column>
        <el-table-column prop="userName" label="姓名"></el-table-column>
        <el-table-column prop="userNumber" label="学号"></el-table-column>
        <el-table-column prop="userSchool" label="所属学校"></el-table-column>
        <el-table-column prop="userEschool" label="实习学校"></el-table-column>
        <el-table-column prop="status" label="审核状态"></el-table-column>
        <el-table-column prop="reason" label="审核意见"></el-table-column>
        <el-table-column label="操作" v-if="user.role !== 'ROLE_TEACHER'">
          <template slot-scope="scope">
            <el-button type="primary" @click="edit(scope.row)" v-if="user.role === 'ROLE_STUDENT' || user.role === 'ROLE_ADMIN'">编辑</el-button>
            <el-button type="success" @click="audit(scope.row)" v-if="user.role === 'ROLE_TEACHER2' || user.role === 'ROLE_ADMIN'">审核</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="danger" style="margin-left: 5px" v-if="user.role === 'ROLE_STUDENT' || user.role === 'ROLE_ADMIN'">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="margin-top: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="params.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="params.pageSize"
          layout="total, sizes, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
    <div>
      <el-dialog title="请填写信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="请假原由" label-width="15%">
            <el-input v-model="form.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="请假日期" label-width="15%">
            <el-date-picker v-model="form.time" type="date" style="width: 90%" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
          <el-form-item label="截止日期" label-width="15%">
            <el-date-picker v-model="form.day" type="date" style="width: 90%" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="请审核" :visible.sync="auditVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="审核状态" label-width="15%">
            <el-radio v-model="form.status" label="审核通过"></el-radio>
            <el-radio v-model="form.status" label="审核不通过"></el-radio>
          </el-form-item>
          <el-form-item label="审核意见" label-width="15%">
            <el-input v-model="form.reason" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="auditVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
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
      params: {
        name: '',
        pageNum: 1,
        pageSize: 5
      },
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      auditVisible: false,
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  created() {
    this.findBySearch();
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    findBySearch() {
      request.get("/audit/search", {
        params: this.params
      }).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    add() {
      this.form = {};
      this.form.userId = this.user.id;
      this.form.userSchool = this.user.school;
      this.form.userEschool = this.user.eschool;
      this.dialogFormVisible = true;
    },
    edit(obj) {
      this.form = obj;
      this.dialogFormVisible = true;
    },
    audit(obj) {
      this.form = obj;
      this.auditVisible = true;
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
        name: '',
      }
      this.findBySearch();
    },
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
    submit() {
      request.post("/audit", this.form).then(res => {
        if (res.code === '0') {
          this.$message.success("操作成功")
          this.dialogFormVisible = false;
          this.auditVisible = false;
          this.findBySearch();
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    del(id) {
      request.delete("/audit/" + id).then(res => {
        if (res.code === '0') {
          this.$message.success("删除成功")
          this.findBySearch();
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>