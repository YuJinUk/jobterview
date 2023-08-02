import apiTest from './apitest';

// 전체 맴버 수 조회
async function getTotalMemberCntAPI(success, fail) {
    await apiTest.get('/admin/members/cnt').then(success).catch(fail);
}

export { getTotalMemberCntAPI };
