import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/Home.vue';
import Login from '../views/Login.vue';
import TeacherManagement from '../views/TeacherManagement.vue';
import StudentManagement from '../views/StudentManagement.vue';
import DeanManagement from '../views/DeanManagement.vue';
import StudentInfo from '../views/StudentInfo.vue';
import StudentCourseManagement from '../views/StudentCourseManagement.vue';
import TeacherInfo from '../views/TeacherInfo.vue';
import ClassManagement from '../views/ClassManagement.vue'; // Import the ClassManagement component

const routes = [
    { path: '/', name: 'Home', component: HomeView, meta: { requiresAuth: true } },
    { path: '/teacherManagement', name: 'TeacherManagement', component: TeacherManagement, meta: { requiresAuth: true, role: 'dean' } },
    { path: '/studentManagement', name: 'StudentManagement', component: StudentManagement, meta: { requiresAuth: true, role: 'dean' } },
    { path: '/deanManagement', name: 'DeanManagement', component: DeanManagement, meta: { requiresAuth: true, role: 'dean' } },
    { path: '/studentInfo', name: 'StudentInfo', component: StudentInfo, meta: { requiresAuth: true, role: 'student' } },
    { path: '/studentCourseManagement', name: 'StudentCourseManagement', component: StudentCourseManagement, meta: { requiresAuth: true, role: 'student' } },
    { path: '/teacherInfo', name: 'TeacherInfo', component: TeacherInfo, meta: { requiresAuth: true, role: 'teacher' } },
    { path: '/classManagement', name: 'ClassManagement', component: ClassManagement, meta: { requiresAuth: true, role: 'dean' } }, // Add new route
    { path: '/login', name: 'Login', component: Login }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

// Navigation guards
router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
    const isAuthenticated = !!localStorage.getItem('user-code');
    const userType = localStorage.getItem('user-type');

    if (requiresAuth && !isAuthenticated) {
        next('/login');
    } else if (to.meta.role && to.meta.role !== userType) {
        next('/');
    } else {
        next();
    }
});

export default router;
