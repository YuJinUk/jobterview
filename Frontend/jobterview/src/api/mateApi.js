import { apiTest } from "./apiTest";

//메이트 목록 조회
async function getMateListWithPagingApi({ page, size, sort, nickname }, success, fail) {
  await apiTest
    .get(`/mate/me?page=${page-1}&size=${size}&sort=${sort}&nickname=${nickname}`)
    .then(success)
    .catch(fail);
}

//메이트 목록 조회 페이징 없음
async function getMateListApi({ nickname }, success, fail) {
  await apiTest
    .get(`/mate/list?nickname=${nickname}`)
    .then(success)
    .catch(fail);
}

//메이트 맺기
async function makeMateApi({ fromNickname, toNickname }, success, fail) {
  await apiTest
    .post(`/mate?fromNickname=${fromNickname}&toNickname=${toNickname}`)
    .then(success)
    .catch(fail);
}

//메이트 끊기
async function breakMateApi({ fromNickname, toNickname }, success, fail) {
  await apiTest
    .delete(`/mate?fromNickname=${fromNickname}&toNickname=${toNickname}`)
    .then(success)
    .catch(fail);
}

export { getMateListWithPagingApi, getMateListApi, makeMateApi, breakMateApi };
