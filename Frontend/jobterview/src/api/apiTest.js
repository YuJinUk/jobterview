import axios from "axios";

// API 개발 사용 헤더 및 URL 설정
export default axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
});
