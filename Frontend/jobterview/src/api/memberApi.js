import { apiTest } from "./apiTest";

//현재 로그인한 맴버 조회
async function getLoginMemberAPI(success, fail) {
  await apiTest.get(`/member/me`).then(success).catch(fail);
}

//맴버 목록 조회
async function getMemberListAPI({ page, size, sort }, success, fail) {
  await apiTest
    .get(`/member/list?page=${page - 1}&size=${size}&sort=${sort}`)
    .then(success)
    .catch(fail);
}

async function updatePassword(member,success,fail){
  await apiTest.put(`/member`,member).then(success).catch(fail);
}

export { getMemberListAPI, getLoginMemberAPI,updatePassword };
