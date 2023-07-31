import axios from "axios";

// API 실제 사용 헤더 및 URL 설정
export default axios.create({
    baseURL: "http://localhost:3000",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin" : "*",
    },
});
