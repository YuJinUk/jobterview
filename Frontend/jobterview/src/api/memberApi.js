import {apiTest} from "./apiTest";

//맴버 목록 조회
async function getMemberListAPI({ page, size, sort }, success, fail) {
  await apiTest
    .get(`/admin/members?page=${page-1}&size=${size}&sort=${sort}`)
    .then(success)
    .catch(fail);
}

export { getMemberListAPI };
