/*
 * 文件: router/index.js (或相应路径)
 * 用途: 定义Vue应用的路由配置
 */

import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/Home.vue';
import Login from '../views/Login.vue';
import TeacherManagement from '../views/TeacherManagement.vue';
import StudentManagement from '../views/StudentManagement.vue';
import DeanManagement from '../views/DeanManagement.vue';
import StudentInfo from '../views/StudentInfo.vue';
import StudentCourseManagement from '../views/StudentCourseManagement.vue';
import TeacherInfo from '../views/TeacherInfo.vue';
import ClassManagement from '../views/ClassManagement.vue'; // 导入班级管理组件

// 路由配置数组，定义了各个页面的路径、名称及对应的组件
const routes = [
    // 登录页面路由
    { path: '/login', name: 'Login', component: Login },

    // 首页路由，需要认证
    { path: '/', name: 'Home', component: HomeView, meta: { requiresAuth: true } },

    // 教师管理路由，仅院长可访问
    { path: '/teacherManagement', name: 'TeacherManagement', component: TeacherManagement, meta: { requiresAuth: true, role: 'dean' } },

    // 学生管理路由，仅院长可访问
    { path: '/studentManagement', name: 'StudentManagement', component: StudentManagement, meta: { requiresAuth: true, role: 'dean' } },

    // 院长管理路由，仅院长可访问
    { path: '/deanManagement', name: 'DeanManagement', component: DeanManagement, meta: { requiresAuth: true, role: 'dean' } },

    // 学生信息路由，学生可访问
    { path: '/studentInfo', name: 'StudentInfo', component: StudentInfo, meta: { requiresAuth: true, role: 'student' } },

    // 学生课程管理路由，学生可访问
    { path: '/studentCourseManagement', name: 'StudentCourseManagement', component: StudentCourseManagement, meta: { requiresAuth: true, role: 'student' } },

    // 教师信息路由，教师可访问
    { path: '/teacherInfo', name: 'TeacherInfo', component: TeacherInfo, meta: { requiresAuth: true, role: 'teacher' } },

    // 班级管理路由，新增，仅院长可访问
    { path: '/classManagement', name: 'ClassManagement', component: ClassManagement, meta: { requiresAuth: true, role: 'dean' } },
];

// 创建路由器实例，使用HTML5历史模式
const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

// 全局前置守卫，用于处理路由跳转前的权限验证
router.beforeEach((to, from, next) => {
    // 判断目标路由是否需要认证
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
    const isAuthenticated = !!localStorage.getItem('user-code');
    const userType = localStorage.getItem('user-type');

    // 如果需要认证且未认证，则重定向到登录页
    if (requiresAuth && !isAuthenticated) {
        next('/login');
        // 如果路由有角色限制且当前用户类型不符，则重定向到首页
    } else if (to.meta.role && to.meta.role !== userType) {
        next('/');
        // 其他情况正常跳转
    } else {
        next();
    }
});

// 导出路由器
export default router;
