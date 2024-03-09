<template>
  <div>
    <div style="margin-bottom: 15px; font-weight: bold; font-size: 30px;">系统公告</div>
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item v-for="item in data" :title="item.name" :name="item.id">
          <div>{{ item.content }}</div>
        </el-collapse-item>
      </el-collapse>
    </div>
</template>

<script>

import request from '@/utils/request'

export default {
  name: 'HomeView',
  
  data() {
    return {
      activeName: '1',
      data: []
    };
  },
  mounted() {
    this.findNotice();
  },
  methods: {
    findNotice() {
      request.get("/notice/").then(res => {
        if(res.code === '0'){
          this.data = res.data
          this.activeName = res.data[0].id
        } else {
        this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>
