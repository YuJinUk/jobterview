//요청을 보낼 url => 백엔드 url
const apiUrl = "https://i9a701.p.ssafy.io/testbe";
// const apiUrl = "http://localhost:8060";

//prefix url 집에서 테스트시에는 /로함면됨.
const apiUrlPrefix = "/testfe";
// 시그널링 서버로 연결
const socketUrl = "https://i9a701.p.ssafy.io"
module.exports = { apiUrl, apiUrlPrefix,socketUrl};
