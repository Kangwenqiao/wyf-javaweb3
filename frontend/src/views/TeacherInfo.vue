<template>
  <div class="teacher-info">
    <h2>教师基本信息</h2>
    <el-card v-if="teacherInfo && collegeInfo">
      <p><strong>姓名:</strong> {{ teacherInfo.name }}</p>
      <p><strong>工号:</strong> {{ teacherInfo.code }}</p>
      <p><strong>学院:</strong> {{ collegeInfo.name }}</p>
      <p><strong>学院备注:</strong> {{ collegeInfo.remark }}</p>
    </el-card>

    <h2>教授课程信息</h2>
    <el-table :data="courses" style="width: 100%" v-if="courses.length">
      <el-table-column prop="courseName" label="课程名称" width="180"></el-table-column>
      <el-table-column prop="termName" label="学期" width="180"></el-table-column>
    </el-table>
    <p v-else>没有课程信息</p>
  </div>
</template>

<script>
import apiClient from '@/services/api';

export default {
  name: 'TeacherInfo',
  data() {
    return {
      teacherInfo: null,
      collegeInfo: null,
      courses: []
    };
  },
  methods: {
    async fetchTeacherInfo() {
      try {
        const teacherCode = localStorage.getItem('user-code');
        if (!teacherCode) {
          throw new Error('未找到教师工号');
        }

        // 获取教师信息
        const teacherResponse = await apiClient.get(`/teacher/getByCode/${teacherCode}`);
        this.teacherInfo = teacherResponse.data;

        // 获取学院信息
        const collegeResponse = await apiClient.get(`/college/get/${this.teacherInfo.collegeId}`);
        this.collegeInfo = collegeResponse.data;

        // 获取教师教授的课程
        const coursesResponse = await apiClient.get(`/teacherCourse/teacher/${this.teacherInfo.id}/courses`);
        const teacherCourses = coursesResponse.data;

        // 获取课程详情和学期信息
        const coursePromises = teacherCourses.map(async (course) => {
          const termResponse = await apiClient.get(`/terms/${course.termId}`);
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
    this.fetchTeacherInfo();
  }
};
</script>

<style scoped>
.teacher-info {
  padding: 20px;
}
</style>
