import apiTest from "./apiTest";

//카테고리 목록 조회
async function getQuestionListAPI(category, success, fail) {
  await apiTest.get(`/question?category=${category}`).then(success).catch(fail);
}

export { getQuestionListAPI };
