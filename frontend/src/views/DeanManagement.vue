<template>
  <!-- 主容器，包含整个教务管理页面 -->
  <div class="dean-management">
    <!-- 获取所有教务信息按钮 -->
    <el-button type="primary" @click="fetchDeans">获取所有教务信息</el-button>
    <!-- 添加教务按钮 -->
    <el-button type="success" @click="openAddDeanDialog">添加教务</el-button>
    <!-- 搜索框 -->
    <el-input
        v-model="search"
        size="default"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <!-- 教务信息表格 -->
    <el-table
        :data="deans.filter(dean => !search || dean.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="code" label="工号" width="180"></el-table-column>
      <el-table-column prop="remark" label="备注" width="180"></el-table-column>
      <el-table-column label="操作" width="180">
        <!-- 操作按钮：编辑、删除 -->
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
import deanService from '@/services/deanService';

export default {
  name: 'DeanManagement',
  data() {
    return {
      // 存储教务信息
      deans: [],
      // 教务表单数据
      deanForm: {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        remark: ''
      },
      // 对话框可见性控制
      dialogVisible: false,
      // 搜索关键字
      search: '',
      // 表单验证规则
      rules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        code: [{ required: true, message: '请输入工号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        remark: [{ required: true, message: '请输入备注', trigger: 'blur' }],
      }
    };
  },
  methods: {
    // 获取教务信息
    fetchDeans() {
      deanService.fetchDeans()
          .then(response => {
            this.deans = response.data;
          })
          .catch(error => {
            console.error('获取教务信息时出错:', error);
          });
    },
    // 打开添加教务对话框
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
    // 编辑教务信息
    editDean(dean) {
      this.deanForm = { ...dean, password: '' }; // 清除密码字段
      this.dialogVisible = true;
    },
    // 保存教务信息
    saveDean() {
      this.$refs.deanFormRef.validate((valid) => {
        if (valid) {
          deanService.saveDean(this.deanForm)
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
    // 删除教务信息
    deleteDean(id) {
      this.$confirm('此操作将永久删除该教务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deanService.deleteDean(id)
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
    // 组件挂载时获取教务信息
    this.fetchDeans();
  }
};
</script>

<style scoped>
.dean-management {
  padding: 20px;
}
</style>
