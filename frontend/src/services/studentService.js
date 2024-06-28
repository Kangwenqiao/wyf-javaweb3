// src/services/studentService.js
/**
 * 学生服务模块，用于处理与学生相关的一切业务逻辑。
 * 该模块提供了获取学生列表、班级分组、保存学生信息、删除学生以及获取学生详细信息的功能。
 */
import apiClient from '@/services/api';

const studentService = {
    /**
     * 获取所有学生列表。
     *
     * @returns {Promise} 返回一个包含所有学生信息的Promise对象。
     */
    fetchStudents() {
        return apiClient.get('/students');
    },
    /**
     * 获取所有班级分组。
     *
     * @returns {Promise} 返回一个包含所有班级分组信息的Promise对象。
     */
    fetchClassGroups() {
        return apiClient.get('/class/all');
    },
    /**
     * 保存学生信息。如果学生存在ID，则更新学生信息；否则，创建新学生。
     *
     * @param {Object} studentForm 包含学生信息的表单对象。
     * @returns {Promise} 返回一个Promise对象，表示保存操作的结果。
     */
    saveStudent(studentForm) {
        const apiCall = studentForm.id ? apiClient.put : apiClient.post;
        const endpoint = studentForm.id ? `/students/${studentForm.id}` : '/students';
        return apiCall(endpoint, studentForm);
    },
    /**
     * 删除指定ID的学生。
     *
     * @param {number} id 待删除学生的ID。
     * @returns {Promise} 返回一个Promise对象，表示删除操作的结果。
     */
    deleteStudent(id) {
        return apiClient.delete(`/students/${id}`);
    },
    /**
     * 根据用户代码获取学生详细信息。
     *
     * @param {string} userCode 学生的用户代码。
     * @returns {Promise} 返回一个包含学生详细信息的Promise对象。
     */
    fetchStudentInfo(userCode) {
        return apiClient.get(`/students/${userCode}`);
    }
};

export default studentService;
