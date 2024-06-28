/**
 * 创建一个axios实例，用于与API进行通信。
 *
 * 该实例配置了基础URL、凭据传递选项和请求头，以支持与后端服务的JSON数据交互。
 * - 基础URL指定了服务端的地址。
 * - withCredentials设置为true，确保跨域请求时携带cookie。
 * - 请求头指定了客户端期望的响应格式和发送的数据格式为JSON。
 */
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: true, // 确保发送cookie
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

/**
 * 导出axios实例，供其他模块使用。
 *
 * 这个实例已经配置好了基本地设置，其他模块可以直接使用它来发起HTTP请求。
 */
export default apiClient;
