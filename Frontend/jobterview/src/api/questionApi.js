import {apiTest} from "./apiTest";

//카테고리 목록 조회
async function getQuestionListAPI(success, fail) {
  await apiTest.get(`/question/list`).then(success).catch(fail);
}

//카테고리 목록 조회 페이징 적용
async function getQuestionListWithPagingAPI({page, size, sort},success, fail) {
  await apiTest.get(`/question/list/paging?page=${page-1}&size=${size}&sort=${sort}`).then(success).catch(fail);
}

//카테고리별 목록 조회 페이징 적용
async function getQuestionListByCategoryWithPagingAPI({category, page, size, sort},success, fail) {
  await apiTest.get(`/question?category=${category}&page=${page-1}&size=${size}&sort=${sort}`).then(success).catch(fail);
}


export { getQuestionListAPI, getQuestionListWithPagingAPI, getQuestionListByCategoryWithPagingAPI };
