import axios from "axios";
import {apiUrl} from "../config/config.js";


// API 개발 사용 헤더 및 URL 설정
const apiTest = axios.create({
    baseURL: apiUrl,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
    withCredentials: true,
});

const apiTest2 = axios.create({
   baseURL: apiUrl,
});

const apiTest3 = axios.create({
    baseURL: apiUrl,
    withCredentials: true,
  });
 

export { apiTest, apiTest2 ,apiTest3};


