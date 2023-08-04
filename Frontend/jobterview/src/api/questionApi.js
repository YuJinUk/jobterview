import {apiTest} from "./apiTest";

//카테고리 목록 조회
async function getQuestionListAPI(success, fail) {
  await apiTest.get(`/question/list`).then(success).catch(fail);
}

export { getQuestionListAPI };
