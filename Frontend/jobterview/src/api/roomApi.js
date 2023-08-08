import {apiTest} from "./apiTest";

//카테고리 목록 조회
async function getQuestionListAPI(success, fail) {
  await apiTest.get(`/question/list`).then(success).catch(fail);
}

//같은방 이름 조회
async function receiveRoomName(roomName,success, fail) {
  await apiTest.get(`/room/name?roomName=${roomName}`).then(success).catch(fail);
}

// 수신 메시지 조회
async function receiveRoomList(page, success, fail) {
  await apiTest.get(`/room?page=${page}`).then(success).catch(fail);
}

export { getQuestionListAPI,receiveRoomList ,receiveRoomName};
