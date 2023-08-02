import {apiTest} from "./apiTest";

async function join(member,success,fail){
    await apiTest.post(`/member/join`,member
    ).then(success).catch(fail);
}
async function checkEmail(email,success,fail){
    await apiTest.get(`/member/emailCheck?email=${email}`,email).then(success).catch(fail);

}
async function checkNickname(nickname,success,fail){
    await apiTest.get(`/member/nicknameCheck?nickname=${nickname}`,nickname).then(success).catch(fail);
}



export {join,checkEmail,checkNickname};
