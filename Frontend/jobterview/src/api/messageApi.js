import apiTest from "apiTest";

// 수신 메시지 조회
async function getReceivedMessage(nickname, success, fail) {
    await apiTest.get(`/message/me?nickname=${nickname}`).then(success).catch(fail);
}
// 송신 메시지 조회

// 수신 메시지 삭제

// 송신 메시지 삭제

// 
