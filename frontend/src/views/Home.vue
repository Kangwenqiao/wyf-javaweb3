<template>
  <!-- 主容器，包含整个主页页面 -->
  <div class="home">
    <!-- 欢迎标题 -->
    <h1>欢迎使用计算机学院管理系统</h1>
    <p>赋能未来创新者</p>
    <!-- 功能模块容器 -->
    <div class="features">
      <!-- 本院学生模块 -->
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
      <!-- 本院教学人员模块 -->
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
      <!-- 本院行政人员模块 -->
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
import studentService from '@/services/studentService';
import teacherService from '@/services/teacherService';
import deanService from '@/services/deanService';

export default {
  name: 'HomeView',
  data() {
    return {
      // 存储学生信息
      students: [],
      // 存储教师信息
      teachers: [],
      // 存储院长信息
      deans: [],
    };
  },
  created() {
    // 组件创建时获取学生、教师和院长信息
    this.fetchStudents();
    this.fetchTeachers();
    this.fetchDeans();
  },
  methods: {
    // 获取学生信息
    async fetchStudents() {
      try {
        const response = await studentService.fetchStudents();
        this.students = response.data;
      } catch (error) {
        console.error('获取学生数据时出错:', error);
      }
    },
    // 获取教师信息
    async fetchTeachers() {
      try {
        const response = await teacherService.fetchTeachers();
        this.teachers = response.data;
      } catch (error) {
        console.error('获取教师数据时出错:', error);
      }
    },
    // 获取院长信息
    async fetchDeans() {
      try {
        const response = await deanService.fetchDeans();
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
