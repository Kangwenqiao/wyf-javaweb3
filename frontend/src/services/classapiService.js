// src/services/classapiService.js

import apiClient from '@/services/api';

/**
 * 课堂服务类，提供与课堂相关的一系列API调用。
 */
const classapiService = {
    /**
     * 获取用户已加入的所有课堂信息。
     *
     * @returns {Promise} 返回一个包含所有课堂信息的Promise对象。
     */
    fetchClasses() {
        return apiClient.get('/class/joined/all');
    },

    /**
     * 获取所有专业信息。
     *
     * @returns {Promise} 返回一个包含所有专业信息的Promise对象。
     */
    fetchSpecialities() {
        return apiClient.get('/speciality/all');
    },

    /**
     * 保存课堂信息。如果课堂存在id，则更新课堂信息；否则创建新课堂。
     *
     * @param {Object} classData 课堂数据对象，包含可能的id和其他课堂信息。
     * @returns {Promise} 返回一个Promise对象，表示保存操作的结果。
     */
    saveClass(classData) {
        const apiCall = classData.id ? apiClient.put : apiClient.post;
        const endpoint = classData.id ? `/class` : '/class';
        return apiCall(endpoint, classData);
    },

    /**
     * 删除指定id的课堂。
     *
     * @param {Number} id 待删除课堂的id。
     * @returns {Promise} 返回一个表示删除操作结果的Promise对象。
     */
    deleteClass(id) {
        return apiClient.delete(`/class/${id}`);
    },

    /**
     * 获取指定课堂已分配的课程及学期信息。
     *
     * @param {Number} classId 课堂id。
     * @returns {Promise} 返回一个包含已分配课程详细信息的Promise对象。
     */
    fetchAssignedCourses(classId) {
        return apiClient.get(`/classCourse/getByClassId/${classId}`)
            .then(response => {
                const coursePromises = response.data.map(courseAssignment =>
                    apiClient.get(`/courses/${courseAssignment.courseId}`)
                        .then(courseResponse => {
                            const course = courseResponse.data;
                            return apiClient.get(`/terms/${course.termId}`)
                                .then(termResponse => ({
                                    id: courseAssignment.id,
                                    course,
                                    term: termResponse.data
                                }));
                        })
                );
                return Promise.all(coursePromises);
            });
    },

    /**
     * 获取所有可用的课程信息。
     *
     * @returns {Promise} 返回一个包含所有可用课程信息的Promise对象。
     */
    fetchAvailableCourses() {
        return apiClient.get('/courses');
    },

    /**
     * 为指定课堂添加课程分配。
     *
     * @param {Object} courseAssignment 课程分配信息对象。
     * @returns {Promise} 返回一个表示添加操作结果的Promise对象。
     */
    addCourse(courseAssignment) {
        return apiClient.post('/classCourse/add', courseAssignment);
    },

    /**
     * 删除指定课堂的课程分配。
     *
     * @param {Number} assignmentId 课程分配的id。
     * @returns {Promise} 返回一个表示删除操作结果的Promise对象。
     */
    removeCourse(assignmentId) {
        return apiClient.delete(`/classCourse/delete/${assignmentId}`);
    }
};

export default classapiService;
