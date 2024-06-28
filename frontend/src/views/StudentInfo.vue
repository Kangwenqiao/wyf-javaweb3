<template>
  <!-- 主容器，包含整个学生信息页面 -->
  <div class="student-info">
    <h2>学生基本信息</h2>
    <!-- 学生信息卡片 -->
    <el-card v-if="studentInfo">
      <p><strong>姓名:</strong> {{ studentInfo.student.name }}</p>
      <p><strong>学号:</strong> {{ studentInfo.student.code }}</p>
      <p><strong>班级:</strong> {{ studentInfo.classGroup.name }}</p>
      <p><strong>专业:</strong> {{ studentInfo.speciality.name }}</p>
      <p><strong>学院:</strong> {{ studentInfo.college.name }}</p>
    </el-card>
  </div>
</template>

<script>
import studentService from '@/services/studentService';

export default {
  name: 'StudentInfo',
  data() {
    return {
      // 存储学生信息
      studentInfo: null,
    };
  },
  methods: {
    // 获取学生信息
    fetchStudentInfo() {
      const userCode = localStorage.getItem('user-code');
      if (userCode) {
        studentService.fetchStudentInfo(userCode)
            .then(response => {
              this.studentInfo = response.data;
            })
            .catch(error => {
              console.error('获取学生信息时出错:', error);
            });
      }
    },
  },
  mounted() {
    // 组件挂载时获取学生信息
    this.fetchStudentInfo();
  },
};
</script>

<style scoped>
.student-info {
  padding: 20px;
}

.el-card {
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

p {
  font-size: 16px;
  color: #333;
  margin: 10px 0;
}

strong {
  color: #2c3e50;
}
</style>
