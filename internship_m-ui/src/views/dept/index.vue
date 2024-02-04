<template>
  <div style="margin-top: 20px; margin: 50px; margin-right: 100px">
    <!-- 按钮 -->

    <br>
    <!-- 数据表格 -->
    <template>
      <el-table
        highlight-current-row
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        border
      >
        <el-table-column type="index" width="100" label="序号" header-align="center" align="center"> </el-table-column>
        <el-table-column prop="name" label="听课记录" header-align="center" align="center"></el-table-column>
        <el-table-column prop="sorce" label="" header-align="center" align="center"></el-table-column>

      </el-table>
    </template>

  </div>
</template>

<script>
import { findAll, add, update, deleteById, selectById } from "@/api/dept.js";

export default {
  data() {
    return {
      formLabelWidth: "120px",
      //dialogFormVisible: false, //控制对话框是否可见
      dept: {
        name: "",
      },
      tableData: [],
    };
  },

  methods: {

    //初始化 - 查询全部
    init() {
      findAll().then((result) => {
        console.log(result);
        if (result.data.code == 1) {
          this.tableData = result.data.data;
        }
      });
    },
  },
  mounted() {
    //当页面加载完成后自动执行。
    this.init();
  },
};
</script>
<style>

</style>