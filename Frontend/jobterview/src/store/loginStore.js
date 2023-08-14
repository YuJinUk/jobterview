import { loginAPI, logoutAPI } from '@/api/authApi';
import { isAdmin } from '@/api/memberApi';
import router from "../router";

export default {
    namespaced: true,
    state: {
        isLogin: false,
        loginNickname: "",
        isAdmin: false,
    },
    mutations: {
        SET_Login: function (state, nickname) {

            state.isLogin = true;
            state.loginNickname = nickname;
        },
        User_Logout: function (state) {
            state.isLogin = false;
            state.loginNickname = "";
        },
        SET_LOGIN_USER: function (state, user) {
            state.isLogin = true;
            state.loginNickname = user.nickname;
        },
        SET_IS_ADMIN: function (state, isAdmin) {
            state.isAdmin = isAdmin;
        }
    },
    getters: {
        getLogin: function (state) {
            return state.isLogin;
        },
        getLoginMemberNickname: function (state) {
            return state.loginNickname;
        },
        getIsAdmin: function (state) {
            return state.isAdmin;
        }
    },
    actions: {
        async setLoginUser({ commit }, user) {
            await loginAPI(user,
                ({ data }) => {

                    if (data.email == null) {
                        alert("로그인 실패");
                    } 
                    else if(data.roles[0] == "ROLE_UNVERIFIED"){
                        alert("메일인증을 진행해주세요");
                    }else if(data.roles[0] == "ROLE_REPORTED_LOCAL")
                    {
                        alert("신고당한 유저입니다. 관리자에게 문의 해주세요.");
                    }else{
                        alert("로그인 성공!");
                        commit("SET_LOGIN_USER", data);
                        //   수정
                        router.push({ name: "Home" });
                    }
                },
                (error) => {
                    console.log(error);
                })
        },
        async UserLogout({ commit }) {
            await logoutAPI(
                () => {
                    document.cookie = "JSESSIONID=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
                    alert("로그아웃");
                    commit("User_Logout");
                    //  수정
                    router.push({ name: "Home" });
                },
                (error) => {
                    console.log(error);
                })
        },
        async getMemberRole({ commit, state }) {
            await isAdmin(state.loginNickname,
                ({ data }) => {

                    console.log(data)
                    commit("SET_IS_ADMIN", data);
                },
                (error) => {
                    console.log(error);
                })
        },
    }
}
