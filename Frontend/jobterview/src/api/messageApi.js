import apiTest from "./apiTest";

// 수신 메시지 조회
async function receiveMessageAPI({nickname, page}, success, fail) {
    await apiTest.get(`/message/me?nickname=${nickname}&page=${page}`).then(success).catch(fail);
}
// 송신 메시지 조회
async function sendMessageAPI({nickname, page}, success, fail) {
    await apiTest.get(`/message/send?nickname=${nickname}&page=${page}`).then(success).catch(fail);
}
// 수신 메시지 삭제

// 송신 메시지 삭제

// 

export { receiveMessageAPI, sendMessageAPI };