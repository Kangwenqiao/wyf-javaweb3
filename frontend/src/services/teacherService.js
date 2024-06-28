// src/services/teacherService.js
/**
 * 教师服务模块，提供与教师相关的一系列数据操作接口。
 */
import apiClient from '@/services/api';

const teacherService = {
    /**
     * 获取所有教师信息。
     * @returns {Promise} 返回包含所有教师信息的Promise对象。
     */
    fetchTeachers() {
        return apiClient.get('/teacher/all');
    },
    /**
     * 获取所有学院信息。
     * @returns {Promise} 返回包含所有学院信息的Promise对象。
     */
    fetchColleges() {
        return apiClient.get('/college/all');
    },
    /**
     * 保存教师信息。如果教师存在ID，则更新教师信息；否则，添加新教师。
     * @param {Object} teacherForm 包含教师信息的表单对象。
     * @returns {Promise} 返回执行保存操作的Promise对象。
     */
    saveTeacher(teacherForm) {
        const apiCall = teacherForm.id ? apiClient.put : apiClient.post;
        const endpoint = teacherForm.id ? '/teacher/update' : '/teacher/add';
        return apiCall(endpoint, teacherForm);
    },
    /**
     * 删除指定ID的教师。
     * @param {Number} id 教师的唯一标识ID。
     * @returns {Promise} 返回执行删除操作的Promise对象。
     */
    deleteTeacher(id) {
        return apiClient.delete(`/teacher/delete/${id}`);
    },
    /**
     * 获取指定教师已分配的课程信息，并包含课程对应的学期信息。
     * @param {Number} teacherId 教师的唯一标识ID。
     * @returns {Promise} 返回包含已分配课程信息的Promise对象，每个课程包含学期信息。
     */
    fetchAssignedCourses(teacherId) {
        return apiClient.get(`/teacherCourse/teacher/${teacherId}/courses`)
            .then(response => {
                const coursePromises = response.data.map(course =>
                    apiClient.get(`/terms/${course.termId}`)
                        .then(termResponse => ({
                            ...course,
                            term: termResponse.data
                        }))
                );
                return Promise.all(coursePromises);
            });
    },
    /**
     * 获取所有可用的课程信息。
     * @returns {Promise} 返回包含所有可用课程信息的Promise对象。
     */
    fetchAvailableCourses() {
        return apiClient.get('/courses');
    },
    /**
     * 为教师分配课程。
     * @param {Object} courseAssignment 包含课程分配信息的对象。
     * @returns {Promise} 返回执行课程分配操作的Promise对象。
     */
    addCourse(courseAssignment) {
        return apiClient.post('/teacherCourse/add', courseAssignment);
    },
    /**
     * 为教师移除指定的课程。
     * @param {Number} teacherId 教师的唯一标识ID。
     * @param {Number} courseId 课程的唯一标识ID。
     * @returns {Promise} 如果课程存在，则返回执行删除操作的Promise对象。
     */
    removeCourse(teacherId, courseId) {
        return apiClient.get(`/teacherCourse/teacher/${teacherId}/teacherCourses`)
            .then(response => {
                const assignment = response.data.find(c => c.courseId === courseId);
                if (assignment) {
                    return apiClient.delete(`/teacherCourse/delete/${assignment.id}`);
                }
            });
    },
    /**
     * 根据教师代码获取教师信息。
     * @param {String} teacherCode 教师的唯一识别代码。
     * @returns {Promise} 返回包含指定教师信息的Promise对象。
     */
    getTeacherByCode(teacherCode) {
        return apiClient.get(`/teacher/getByCode/${teacherCode}`);
    },
    /**
     * 根据学院ID获取学院信息。
     * @param {Number} collegeId 学院的唯一标识ID。
     * @returns {Promise} 返回包含指定学院信息的Promise对象。
     */
    getCollegeById(collegeId) {
        return apiClient.get(`/college/get/${collegeId}`);
    },
    /**
     * 获取指定教师的所有课程。
     * @param {Number} teacherId 教师的唯一标识ID。
     * @returns {Promise} 返回包含指定教师所有课程信息的Promise对象。
     */
    getTeacherCourses(teacherId) {
        return apiClient.get(`/teacherCourse/teacher/${teacherId}/courses`);
    },
    /**
     * 根据学期ID获取学期信息。
     * @param {Number} termId 学期的唯一标识ID。
     * @returns {Promise} 返回包含指定学期信息的Promise对象。
     */
    getTermById(termId) {
        return apiClient.get(`/terms/${termId}`);
    }
};

export default teacherService;
