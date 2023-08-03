import axios from "axios";

// API 개발 사용 헤더 및 URL 설정
const apiTest = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
    withCredentials: true,
});

const apiTest2 = axios.create({
   baseURL: "http://localhost:8080",
});

const apiTest3 = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true,
  });
 

export { apiTest, apiTest2 ,apiTest3};


