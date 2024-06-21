import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080', // 确保这个URL是正确的
    withCredentials: true, // 确保发送cookie
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

export default apiClient;
