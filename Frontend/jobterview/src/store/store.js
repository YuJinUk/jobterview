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
});

export default store;