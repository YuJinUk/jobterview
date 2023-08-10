//요청을 보낼 url => local에서는 localhost:8060으로 가고 배포될때는 우리 서버로감
const apiUrl = process.env.NODE_ENV === 'production'? "https://i9a701.p.ssafy.io/testbe" : "http://localhost:8060";

//prefix url 집에서 테스트시에는 /로함면됨.
const apiUrlPrefix = "/testfe";

// 시그널링 서버로 연결
const socketUrl = "https://i9a701.p.ssafy.io"

// ML url
const MLUrl =  process.env.NODE_ENV === 'production' ? "https://i9a701.p.ssafy.io/": "http://localhost:5000/";

module.exports = { apiUrl, apiUrlPrefix,socketUrl, MLUrl };
