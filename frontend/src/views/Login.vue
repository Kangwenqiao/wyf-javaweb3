<template>
  <div class="login-container">
    <div class="login-card">
      <h2 class="login-title">用户登录</h2>
      <el-form :model="loginForm" @submit.prevent="onSubmit" ref="loginForm">
        <el-form-item label="工号" prop="code" :rules="[{ required: true, message: '请输入工号', trigger: 'blur' }]">
          <el-input v-model="loginForm.code" autocomplete="off" placeholder="请输入工号"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" prop="type" :rules="[{ required: true, message: '请选择用户类型', trigger: 'change' }]">
          <el-select v-model="loginForm.type" placeholder="选择用户类型">
            <el-option label="教务" value="dean"></el-option>
            <el-option label="教师" value="teacher"></el-option>
            <el-option label="学生" value="student"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" :loading="loading">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import apiClient from '@/services/api';

export default {
  name: 'UserLogin',
  data() {
    return {
      loginForm: {
        code: '',
        password: '',
        type: 'dean'
      },
      loading: false
    };
  },
  methods: {
    onSubmit() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          apiClient.post('/login', this.loginForm)
              .then(response => {
                this.loading = false;
                if (response.data.code === 200) {
                  localStorage.setItem('user-code', this.loginForm.code);
                  localStorage.setItem('user-type', this.loginForm.type);
                  localStorage.setItem('user-id', response.data.id); // Assuming backend returns user ID
                  this.$router.push('/');
                } else {
                  this.$message.error('登录失败: ' + response.data.msg);
                }
              })
              .catch(error => {
                this.loading = false;
                console.error('登录时出错:', error);
                this.$message.error('登录时发生错误');
              });
        }
      });
    }
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 30px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.login-title {
  margin-bottom: 20px;
  text-align: center;
  font-weight: bold;
  color: #333;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input__inner {
  border-radius: 4px;
}

.el-button--primary {
  width: 100%;
  border-radius: 4px;
}
</style>