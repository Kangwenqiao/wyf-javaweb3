<template>
  <div class="home">
    <h1>欢迎使用计算机学院管理系统</h1>
    <p>赋能未来创新者</p>
    <div class="features">
      <div class="feature">
        <h2>本院学生</h2>
        <p v-if="students.length">总数：{{ students.length }}</p>
        <p v-else>加载学生数据中...</p>
        <el-carousel v-if="students.length" :interval="2000" type="card" height="200px" indicator-position="none">
          <el-carousel-item v-for="student in students" :key="student.student.id">
            <p>{{ student.student.name }} - {{ student.classGroup.name }} - {{ student.speciality.name }} - {{ student.college.name }}</p>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="feature">
        <h2>本院教学人员</h2>
        <p v-if="teachers.length">总数：{{ teachers.length }}</p>
        <p v-else>加载教师数据中...</p>
        <el-carousel v-if="teachers.length" :interval="2000" type="card" height="200px" indicator-position="none">
          <el-carousel-item v-for="teacher in teachers" :key="teacher.teacher.id">
            <p>{{ teacher.teacher.name }} - {{ teacher.college.name }}</p>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="feature">
        <h2>本院行政人员</h2>
        <p v-if="deans.length">总数：{{ deans.length }}</p>
        <p v-else>加载院长数据中...</p>
        <el-carousel v-if="deans.length" :interval="2000" type="card" height="200px" indicator-position="none">
          <el-carousel-item v-for="dean in deans" :key="dean.id">
            <p>{{ dean.name }} - {{ dean.remark }}</p>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HomeView',
  data() {
    return {
      students: [],
      teachers: [],
      deans: [],
    };
  },
  created() {
    this.fetchStudents();
    this.fetchTeachers();
    this.fetchDeans();
  },
  methods: {
    async fetchStudents() {
      try {
        const response = await axios.get('http://localhost:8080/students');
        this.students = response.data;
      } catch (error) {
        console.error('获取学生数据时出错:', error);
      }
    },
    async fetchTeachers() {
      try {
        const response = await axios.get('http://localhost:8080/teacher/all');
        this.teachers = response.data;
      } catch (error) {
        console.error('获取教师数据时出错:', error);
      }
    },
    async fetchDeans() {
      try {
        const response = await axios.get('http://localhost:8080/dean/all');
        this.deans = response.data;
      } catch (error) {
        console.error('获取院长数据时出错:', error);
      }
    },
  },
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
  font-family: 'Arial', sans-serif;
  color: #2c3e50;
}

h1 {
  font-size: 2.5em;
  margin-bottom: 10px;
}

p {
  font-size: 1.2em;
  margin-bottom: 30px;
}

.features {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.feature {
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.feature h2 {
  font-size: 1.5em;
  margin-bottom: 10px;
}

.feature p {
  font-size: 1em;
  color: #666;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.el-carousel__indicators {
  display: none;
}
</style>