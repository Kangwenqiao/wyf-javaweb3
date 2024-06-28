<template>
  <!-- 主容器，包含整个教师信息页面 -->
  <div class="teacher-info">
    <h2>教师基本信息</h2>
    <!-- 教师信息卡片 -->
    <el-card v-if="teacherInfo && collegeInfo">
      <p><strong>姓名:</strong> {{ teacherInfo.name }}</p>
      <p><strong>工号:</strong> {{ teacherInfo.code }}</p>
      <p><strong>学院:</strong> {{ collegeInfo.name }}</p>
      <p><strong>学院备注:</strong> {{ collegeInfo.remark }}</p>
    </el-card>

    <h2>教授课程信息</h2>
    <!-- 教授课程信息表格 -->
    <el-table :data="courses" style="width: 100%" v-if="courses.length">
      <el-table-column prop="courseName" label="课程名称" width="180"></el-table-column>
      <el-table-column prop="termName" label="学期" width="180"></el-table-column>
    </el-table>
    <!-- 没有课程信息时显示的提示 -->
    <p v-else>没有课程信息</p>
  </div>
</template>

<script>
import teacherService from '@/services/teacherService';

export default {
  name: 'TeacherInfo',
  data() {
    return {
      // 存储教师信息
      teacherInfo: null,
      // 存储学院信息
      collegeInfo: null,
      // 存储教授课程信息
      courses: []
    };
  },
  methods: {
    // 获取教师信息
    async fetchTeacherInfo() {
      try {
        const teacherCode = localStorage.getItem('user-code');
        if (!teacherCode) {
          throw new Error('未找到教师工号');
        }

        // 获取教师信息
        const teacherResponse = await teacherService.getTeacherByCode(teacherCode);
        this.teacherInfo = teacherResponse.data;

        // 获取学院信息
        const collegeResponse = await teacherService.getCollegeById(this.teacherInfo.collegeId);
        this.collegeInfo = collegeResponse.data;

        // 获取教师教授的课程
        const coursesResponse = await teacherService.getTeacherCourses(this.teacherInfo.id);
        const teacherCourses = coursesResponse.data;

        // 获取课程详情和学期信息
        const coursePromises = teacherCourses.map(async (course) => {
          const termResponse = await teacherService.getTermById(course.termId);
          const termData = termResponse.data;

          return {
            courseName: course.name,
            termName: termData.name
          };
        });

        this.courses = await Promise.all(coursePromises);
      } catch (error) {
        console.error('获取教师信息时出错:', error);
        this.$message.error('获取教师信息时发生错误');
      }
    }
  },
  mounted() {
    // 组件挂载时获取教师信息
    this.fetchTeacherInfo();
  }
};
</script>

<style scoped>
.teacher-info {
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

h2 {
  margin-top: 20px;
  color: #2c3e50;
}
</style>
