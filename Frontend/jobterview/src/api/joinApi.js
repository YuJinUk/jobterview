import { apiTest } from "./apiTest";

async function join(member, success, fail) {
  await apiTest.post(`/member/join`, member).then(success).catch(fail);
}
async function reJoin(member, success, fail) {
  await apiTest.post(`/member/reJoin`, member).then(success).catch(fail);
}
async function checkEmail(email, success, fail) {
  await apiTest
    .get(`/member/emailCheck?email=${email}`)
    .then(success)
    .catch(fail);
}
async function checkNickname(nickname, success, fail) {
  await apiTest
    .get(`/member/nicknameCheck?nickname=${nickname}`, nickname)
    .then(success)
    .catch(fail);
}
async function sendEmail(email, success, fail) {
  await apiTest
    .post(`/member/emailconfirm?email=${email}`)
    .then(success)
    .catch(fail);
}

async function sendPasswordEmail(email, success, fail) {
  await apiTest
    .post(`/member/passwordEmailConfirm?email=${email}`)
    .then(success)
    .catch(fail);
}

export {
  join,
  reJoin,
  checkEmail,
  checkNickname,
  sendEmail,
  sendPasswordEmail,
};
