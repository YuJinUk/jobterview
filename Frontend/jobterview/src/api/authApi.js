import {apiTest2} from "./apiTest";
import {apiTest3} from "./apiTest";


//로칼로긴
async function loginAPI(user, success, fail) {
    await apiTest3.post(`/auth/login`, user).then(success).catch(fail);
}

//로가웃
async function logoutAPI( success, fail) {
    await apiTest2.post(`/auth/logout`).then(success).catch(fail);
}


export {loginAPI,logoutAPI};