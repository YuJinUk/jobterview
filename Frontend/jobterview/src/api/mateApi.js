import {apiTest} from "./apiTest";

//메이트 목록 조회
async function getMateListApi({nickname} , success, fail) {
  await apiTest
    .get(`/mate/list?nickname=${nickname}`)
    .then(success)
    .catch(fail);
}

export { getMateListApi };