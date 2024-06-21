<template>
  <div id="app">
    <el-container>
      <el-aside width="200px" class="menu-aside">
        <el-menu :default-active="activeIndex" class="el-menu-vertical-demo" @select="handleSelect" background-color="#333" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1" @click="goHome"><i class="el-icon-house"></i> 主页</el-menu-item>
          <el-menu-item v-if="isDean" index="2" @click="goTeacherManagement"><i class="el-icon-user"></i> 教师管理</el-menu-item>
          <el-menu-item v-if="isDean" index="3" @click="goStudentManagement"><i class="el-icon-user"></i> 学生管理</el-menu-item>
          <el-menu-item v-if="isDean" index="4" @click="goDeanManagement"><i class="el-icon-setting"></i> 教务管理</el-menu-item>
          <el-menu-item v-if="isDean" index="5" @click="goClassManagement"><i class="el-icon-school"></i> 班级管理</el-menu-item>
          <el-menu-item v-if="isStudent" index="6" @click="goStudentInfo"><i class="el-icon-document"></i> 学生信息</el-menu-item>
          <el-menu-item v-if="isStudent" index="7" @click="goStudentCourseManagement"><i class="el-icon-trophy"></i> 我的课程与成绩</el-menu-item>
          <el-menu-item v-if="isTeacher" index="8" @click="goTeacherInfo"><i class="el-icon-user"></i> 教师信息</el-menu-item>
          <el-menu-item v-if="!isLoggedIn" index="9" @click="goLogin"><i class="el-icon-user"></i> 登录</el-menu-item>
          <el-menu-item v-if="isLoggedIn" index="10" @click="logout"><i class="el-icon-switch-button"></i> 注销</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import apiClient from '@/services/api';

export default {
  data() {
    return {
      activeIndex: '1',
      isLoggedIn: !!localStorage.getItem('user-code'),
      isDean: localStorage.getItem('user-type') === 'dean',
      isStudent: localStorage.getItem('user-type') === 'student',
      isTeacher: localStorage.getItem('user-type') === 'teacher'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    goHome() {
      this.$router.push('/');
      this.activeIndex = '1';
    },
    goTeacherManagement() {
      this.$router.push('/teacherManagement');
      this.activeIndex = '2';
    },
    goStudentManagement() {
      this.$router.push('/studentManagement');
      this.activeIndex = '3';
    },
    goDeanManagement() {
      this.$router.push('/deanManagement');
      this.activeIndex = '4';
    },
    goClassManagement() {
      this.$router.push('/classManagement');
      this.activeIndex = '5';
    },
    goStudentInfo() {
      this.$router.push('/studentInfo');
      this.activeIndex = '6';
    },
    goStudentCourseManagement() {
      this.$router.push('/studentCourseManagement');
      this.activeIndex = '7';
    },
    goTeacherInfo() {
      this.$router.push('/teacherInfo');
      this.activeIndex = '8';
    },
    goLogin() {
      this.$router.push('/login');
      this.activeIndex = '9';
    },
    logout() {
      apiClient.get('/logout')
          .then(response => {
            if (response.data.code === 200) {
              localStorage.removeItem('user-code');
              localStorage.removeItem('user-type');
              this.isLoggedIn = false;
              this.isDean = false;
              this.isStudent = false;
              this.isTeacher = false;
              this.$router.push('/login');
              this.$message.success('注销成功');
            } else {
              this.$message.error('注销失败: ' + response.data.msg);
            }
          })
          .catch(error => {
            console.error('注销时出错:', error);
            this.$message.error('注销时发生错误');
          });
    }
  },
  watch: {
    $route() {
      this.isLoggedIn = !!localStorage.getItem('user-code');
      this.isDean = localStorage.getItem('user-type') === 'dean';
      this.isStudent = localStorage.getItem('user-type') === 'student';
      this.isTeacher = localStorage.getItem('user-type') === 'teacher';
    }
  }
};
</script>

<style>
.el-menu-vertical-demo {
  height: 100%;
  border-right: none;
}

.menu-aside {
  background-color: #333;
  color: #fff;
}

.el-icon {
  margin-right: 8px;
}
</style>
