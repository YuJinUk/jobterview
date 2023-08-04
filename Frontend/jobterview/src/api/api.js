import axios from "axios";
import {apiUrl} from "../config/config.js";
// API 실제 사용 헤더 및 URL 설정
export default axios.create({
    baseURL: apiUrl,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin" : "*",
    },
});