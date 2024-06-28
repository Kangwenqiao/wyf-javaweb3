// src/services/studentCourseService.js
/**
 * 学生课程服务模块，用于提供与学生课程相关的一系列接口调用。
 * 该模块通过import引入apiClient，从而实现对后端API的封装和调用。
 */
import apiClient from '@/services/api';

/**
 * 学生课程服务对象，包含多个方法用于访问与学生课程相关的API。
 */
const studentCourseService = {
    /**
     * 根据学生代码获取学生信息。
     * @param {string} studentCode - 学生的唯一标识代码。
     * @returns 返回一个Promise，解析后的结果是来自API的学生信息。
     */
    getStudentByCode(studentCode) {
        return apiClient.get(`/students/${studentCode}`);
    },
    /**
     * 根据班级ID获取班级的课程列表。
     * @param {number} classId - 班级的唯一标识ID。
     * @returns 返回一个Promise，解析后的结果是来自API的班级课程列表。
     */
    getClassCourses(classId) {
        return apiClient.get(`/classCourse/getByClassId/${classId}`);
    },
    /**
     * 根据课程ID获取课程的详细信息。
     * @param {number} courseId - 课程的唯一标识ID。
     * @returns 返回一个Promise，解析后的结果是来自API的课程详细信息。
     */
    getCourseDetails(courseId) {
        return apiClient.get(`/courses/${courseId}`);
    },
    /**
     * 根据学期ID获取学期的详细信息。
     * @param {number} termId - 学期的唯一标识ID。
     * @returns 返回一个Promise，解析后的结果是来自API的学期详细信息。
     */
    getTermDetails(termId) {
        return apiClient.get(`/terms/${termId}`);
    },
    /**
     * 根据学生ID获取学生的课程成绩信息。
     * @param {number} studentId - 学生的唯一标识ID。
     * @returns 返回一个Promise，解析后的结果是来自API的学生课程成绩信息。
     */
    getStudentScores(studentId) {
        return apiClient.get(`/studentCourseScore/get/${studentId}`);
    }
};

export default studentCourseService;
