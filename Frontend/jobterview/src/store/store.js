import { createStore } from 'vuex';
import messageStore from './messageStore';
import loginStore from './loginStore';
import createPersistedState from 'vuex-persistedstate';
const store = createStore({
    modules: {
        messageStore,
        loginStore,
    },
    plugins: [
        createPersistedState()
    ],
    getters: {
        isLogin: state => {
          return state.loginStore.isLogin; // loginStore 모듈의 isLogin 상태 반환
        }
    }
});

export default store;