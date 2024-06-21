<template>
  <div class="dean-management">
    <el-button type="primary" @click="fetchDeans">获取所有教务信息</el-button>
    <el-button type="success" @click="openAddDeanDialog">添加教务</el-button>
    <el-input
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <el-table
        :data="deans.filter(dean => !search || dean.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="code" label="工号" width="180"></el-table-column>
      <el-table-column prop="remark" label="备注" width="180"></el-table-column>
      <el-table-column label="操作" width="180">
        <template v-slot="scope">
          <el-button @click="editDean(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteDean(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加/编辑教务对话框 -->
    <el-dialog v-model="dialogVisible" title="教务表单">
      <el-form :model="deanForm" :rules="rules" ref="deanFormRef">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="deanForm.name"></el-input>
        </el-form-item>
        <el-form-item label="工号" prop="code">
          <el-input v-model="deanForm.code"></el-input>
        </el-form-item>
        <el-form-item v-if="!deanForm.id" label="密码" prop="password">
          <el-input type="password" v-model="deanForm.password"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="deanForm.remark"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveDean">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import apiClient from '@/services/api';

export default {
  name: 'DeanManagement',
  data() {
    return {
      deans: [],
      deanForm: {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        remark: ''
      },
      dialogVisible: false,
      search: '',
      rules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        code: [{ required: true, message: '请输入工号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        remark: [{ required: true, message: '请输入备注', trigger: 'blur' }],
      }
    };
  },
  methods: {
    fetchDeans() {
      apiClient.get('/dean/all')
          .then(response => {
            console.log('Fetched deans:', response.data); // Debug line
            this.deans = response.data;
          })
          .catch(error => {
            console.error('获取教务信息时出错:', error);
          });
    },
    openAddDeanDialog() {
      this.deanForm = {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        remark: ''
      };
      this.dialogVisible = true;
    },
    editDean(dean) {
      this.deanForm = { ...dean, password: '' }; // Clear password field
      this.dialogVisible = true;
    },
    saveDean() {
      this.$refs.deanFormRef.validate((valid) => {
        if (valid) {
          const apiCall = this.deanForm.id ? apiClient.put : apiClient.post;
          const endpoint = this.deanForm.id ? `/dean/update` : '/dean/add';

          apiCall(endpoint, this.deanForm)
              .then(() => {
                this.$message.success('教务信息保存成功');
                this.dialogVisible = false;
                this.fetchDeans(); // 更新成功后刷新数据
              })
              .catch(error => {
                console.error('保存教务信息时出错:', error);
                this.$message.error('保存教务信息时发生错误');
              });
        } else {
          this.$message.error('请填写完整信息');
          return false;
        }
      });
    },
    deleteDean(id) {
      this.$confirm('此操作将永久删除该教务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        apiClient.delete(`/dean/delete/${id}`)
            .then(() => {
              this.$message.success('教务信息删除成功');
              this.fetchDeans(); // 删除成功后刷新数据
            })
            .catch(error => {
              console.error('删除教务信息时出错:', error);
              this.$message.error('删除教务信息时发生错误');
            });
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    }
  },
  mounted() {
    this.fetchDeans();
  }
};
</script>

<style scoped>
.dean-management {
  padding: 20px;
}
</style>
