<template>
  <div class="student-info">
    <h2>学生基本信息</h2>
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
import apiClient from '@/services/api';

export default {
  name: 'StudentInfo',
  data() {
    return {
      studentInfo: null,
    };
  },
  methods: {
    fetchStudentInfo() {
      const userCode = localStorage.getItem('user-code');
      if (userCode) {
        apiClient.get(`/students/${userCode}`)
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
    this.fetchStudentInfo();
  },
};
</script>

<style scoped>
.student-info {
  padding: 20px;
}
</style>
