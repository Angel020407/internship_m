<template>
  <div>
    <div style="margin-bottom: 15px">
      <el-input v-model="params.type" style="width: 200px" placeholder="请输入作业类型"></el-input>
      <el-input v-model="params.num" style="width: 200px; margin-left: 5px" placeholder="请输入序号"></el-input>
      <el-button type="warning" style="margin-left: 10px" @click="findBySearch()">查询</el-button>
      <el-button type="warning" style="margin-left: 10px" @click="reset()">清空</el-button>
      <el-button type="primary" style="margin-left: 10px" @click="add()">新增</el-button>
    </div>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="type" label="作业类型"></el-table-column>
        <el-table-column prop="num" label="序号"></el-table-column>
        <el-table-column label="显示文件">
          <template v-slot="scope">
            <el-image
                style="width: 70px; height: 70px; border-radius: 50%"
                :src="'http://localhost:8080/api/files/' + scope.row.img"
                :preview-src-list="['http://localhost:8080/api/files/' + scope.row.img]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column label="上传文件">
          <template slot-scope="scope">
            <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="down(scope.row.img)">下载</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="danger" style="margin-left: 5px">删除</el-button>
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
          <el-form-item label="作业类型" label-width="20%">
            <el-select v-model="form.type" placeholder="请选择" style="width: 90%">
              <el-option label="听课记录" value="听课记录"></el-option>
              <el-option label="教学设计" value="教学设计"></el-option>
              <el-option label="主题班会" value="主题班会"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="序号" label-width="20%">
            <el-input v-model="form.num" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="选择附件" label-width="20%">
            <el-upload action="http://localhost:8080/api/files/upload" :on-success="successUpload">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
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
        type: '',
        num: '',
        pageNum: 1,
        pageSize: 5
      },
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      form: {}
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  created() {
    this.findBySearch();
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    findBySearch() {
      request.get("/record/search", {
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
      this.dialogFormVisible = true;
    },
    edit(obj) {
      this.form = obj;
      this.dialogFormVisible = true;
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
        type: '',
        num: ''
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
      request.post("/record", this.form).then(res => {
        if (res.code === '0') {
          this.$message.success("操作成功");
          this.dialogFormVisible = false;
          this.findBySearch();
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    del(id) {
      request.delete("/record/" + id).then(res => {
        if (res.code === '0') {
          this.$message.success("删除成功");
          this.findBySearch();
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    successUpload(res) {
      this.form.img = res.data;
    },
    down(flag) {
      location.href = 'http://localhost:8080/api/files/' + flag
    }

  }
}
</script>
