import { loginAPI,logoutAPI } from '@/api/authApi';
import router from "../router";

export default {
    namespaced: true,
    state: {
        isLogin: false,
        loginNickname: "",
    },
    mutations: {
        
        SET_Login: function (state, nickname) {
            
            state.isLogin = true;
            state.loginNickname = nickname;
        },
        User_Logout: function (state) {
            state.isLogin = false;
            state.loginNickname ="";
        },
        SET_LOGIN_USER: function (state, user) {
            state.isLogin = true;
            state.loginNickname = user.nickname;
        },
        
    },
    getters: {
        getLogin: function (state) {
            return state.isLogin;
        },
    },
    actions: {
      
        async setLoginUser({ commit }, user) {
          await loginAPI(user,
              ({data}) => {
                  if (data.email == null) {
                    alert("로그인 실패");
                    } else {
                      alert("로그인 성공!");
                      commit("SET_LOGIN_USER", data);
                      router.push("/");
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
              router.push("/");
            },
            (error) => {
                console.log(error);
            })
    },
    }
}