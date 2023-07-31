import apiTest from "apiTest";
// 비밀번호 찾기 메일 전송
async function sendEmail(email,success,fail) {
    await apiTest.post(`/member/me?nickname=${nickname}`).then(success).catch(fail);
}