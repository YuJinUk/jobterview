import {apiTest} from "./apiTest";

//카테고리 목록 조회
async function getQuestionListAPI(success, fail) {
  await apiTest.get(`/question/list`).then(success).catch(fail);
}

// 수신 메시지 조회
async function receiveRoomList(page, success, fail) {
  await apiTest.get(`/room?page=${page}`).then(success).catch(fail);
}

export { getQuestionListAPI,receiveRoomList };
