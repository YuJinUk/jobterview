import {apiTest} from "./apiTest";


async function getRoomListWithPaging({page}, success, fail) {
  await apiTest.get(`/room?page=${page}`).then(success).catch(fail);
}

//같은방 이름 조회
async function receiveRoomName(roomName,success, fail) {
  await apiTest.get(`/room/name?roomName=${roomName}`).then(success).catch(fail);
}

// 수신 메시지 조회
async function receiveRoomList(page, success, fail) {
  await apiTest.get(`/room?page=${page}`).then(success).catch(fail);
}

// 수신 메시지 조회
async function receiveSearchRoomList({keyword,page}, success, fail) {
  await apiTest.get(`/room/search?keyword=${keyword}&page=${page}`).then(success).catch(fail);
}

export { getRoomListWithPaging, receiveRoomList ,receiveRoomName,receiveSearchRoomList};
