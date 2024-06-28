// src/services/deanService.js
/**
 * 教务处服务模块，用于与教务处相关的数据交互。
 * 提供获取教务处人员列表、新增、修改和删除教务处人员的功能。
 */
import apiClient from '@/services/api';

const deanService = {
  /**
   * 获取所有教务处人员列表。
   *
   * @returns {Promise} 返回一个包含所有教务处人员信息的Promise对象。
   */
  fetchDeans() {
    return apiClient.get('/dean/all');
  },
  /**
   * 保存教务处人员信息，如果是新人员，则添加；如果是已有人员，则更新信息。
   *
   * @param {Object} deanForm 包含教务处人员信息的对象，如果id存在，则为更新操作。
   * @returns {Promise} 返回一个根据操作结果的Promise对象。
   */
  saveDean(deanForm) {
    const apiCall = deanForm.id ? apiClient.put : apiClient.post;
    const endpoint = deanForm.id ? `/dean/update` : '/dean/add';
    return apiCall(endpoint, deanForm);
  },
  /**
   * 删除指定ID的教务处人员。
   *
   * @param {Number} id 教务处人员的唯一标识ID。
   * @returns {Promise} 返回一个根据操作结果的Promise对象。
   */
  deleteDean(id) {
    return apiClient.delete(`/dean/delete/${id}`);
  }
};

export default deanService;
