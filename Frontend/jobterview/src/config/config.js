//요청을 보낼 로컬에서는 localhost:8060 으로가고 master로 보내면 우리서버로 감
const apiUrl = process.env.NODE_ENV === 'production' ? "https://i9a701.p.ssafy.io/testbe": "http://localhost:8060";

//prefix url 집에서 테스트시에는 /로함면됨.
const apiUrlPrefix = "/testfe";
// 시그널링 서버로 연결
const socketUrl = "https://i9a701.p.ssafy.io"
module.exports = { apiUrl, apiUrlPrefix,socketUrl};
