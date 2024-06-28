<template>
  <!-- 主容器，包含整个学生课程与成绩管理页面 -->
  <div class="student-course-management">
    <!-- 标题 -->
    <h2>我的课程与成绩</h2>
    <!-- 获取课程与成绩按钮 -->
    <el-button type="primary" @click="fetchCoursesAndScores">获取我的课程与成绩</el-button>

    <!-- 课程信息标题 -->
    <h3>课程信息</h3>
    <!-- 课程信息表格 -->
    <el-table :data="courses" style="width: 100%" v-if="courses.length">
      <el-table-column prop="courseName" label="课程名称" width="180"></el-table-column>
      <el-table-column prop="termName" label="学期" width="180"></el-table-column>
      <el-table-column prop="courseRemark" label="课程注释" width="180"></el-table-column>
    </el-table>
    <!-- 没有课程信息时显示的提示 -->
    <p v-else>没有课程信息</p>

    <!-- 成绩信息标题 -->
    <h3>成绩信息</h3>
    <!-- 成绩信息表格 -->
    <el-table :data="scores" style="width: 100%" v-if="scores.length">
      <el-table-column prop="courseName" label="课程名称" width="180"></el-table-column>
      <el-table-column prop="termName" label="学期" width="180"></el-table-column>
      <el-table-column prop="score" label="成绩" width="180"></el-table-column>
      <el-table-column prop="scoreRemark" label="成绩备注" width="180"></el-table-column>
    </el-table>
    <!-- 没有成绩信息时显示的提示 -->
    <p v-else>没有成绩信息</p>
  </div>
</template>

<script>
import studentCourseService from '@/services/studentCourseService';

export default {
  name: 'StudentCourseManagement',
  data() {
    return {
      // 存储课程信息
      courses: [],
      // 存储成绩信息
      scores: []
    };
  },
  methods: {
    // 获取课程与成绩信息
    async fetchCoursesAndScores() {
      try {
        // 从本地存储中获取学生学号
        const studentCode = localStorage.getItem('user-code');
        if (!studentCode) {
          throw new Error('未找到学生学号');
        }

        // 获取学生信息
        const studentResponse = await studentCourseService.getStudentByCode(studentCode);
        const studentData = studentResponse.data;
        const classId = studentData.classGroup.id;
        const studentId = studentData.student.id;

        // 获取班级课程
        const classCourseResponse = await studentCourseService.getClassCourses(classId);
        const classCourses = classCourseResponse.data;

        // 获取课程详情和学期信息
        const coursePromises = classCourses.map(async (classCourse) => {
          const courseResponse = await studentCourseService.getCourseDetails(classCourse.courseId);
          const courseData = courseResponse.data;

          const termResponse = await studentCourseService.getTermDetails(courseData.termId);
          const termData = termResponse.data;

          return {
            courseName: courseData.name,
            termName: termData.name,
            courseRemark: courseData.remark
          };
        });

        this.courses = await Promise.all(coursePromises);

        // 获取学生成绩
        const scoreResponse = await studentCourseService.getStudentScores(studentId);
        let studentScores = scoreResponse.data;

        // 确保 studentScores 是数组
        if (!Array.isArray(studentScores)) {
          studentScores = [studentScores];
        }

        // 获取每门课程的详情和学期信息
        const scorePromises = studentScores.map(async (studentScore) => {
          const courseResponse = await studentCourseService.getCourseDetails(studentScore.courseId);
          const courseData = courseResponse.data;

          const termResponse = await studentCourseService.getTermDetails(courseData.termId);
          const termData = termResponse.data;

          return {
            courseName: courseData.name,
            termName: termData.name,
            score: studentScore.score,
            scoreRemark: studentScore.remark
          };
        });

        this.scores = await Promise.all(scorePromises);
      } catch (error) {
        console.error('获取课程与成绩信息时出错:', error);
        this.$message.error('获取课程与成绩信息时发生错误');
      }
    }
  },
  mounted() {
    // 组件挂载时获取课程与成绩信息
    this.fetchCoursesAndScores();
  }
};
</script>

<style scoped>
.student-course-management {
  padding: 20px;
}
</style>
