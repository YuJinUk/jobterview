import {apiTest} from "./apiTest";

async function join(member,success,fail){
    await apiTest.post(`/member/join`,member
    ).then(success).catch(fail);
}
async function checkEmail(email,success,fail){
    console.log(email);
    console.log(apiTest);
    await apiTest.get(`/member/emailCheck?email=${email}`).then(success).catch(fail);
}
async function checkNickname(nickname,success,fail){
    await apiTest.get(`/member/nicknameCheck?nickname=${nickname}`,nickname).then(success).catch(fail);
}
async function sendEmail(email, success, fail) {
    await apiTest.post(`/member/emailconfirm?email=${email}`).then(success).catch(fail);
}

export {join,checkEmail,checkNickname,sendEmail};
