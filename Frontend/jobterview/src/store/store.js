import { createStore } from 'vuex';
import messageStore from './messageStore';
import createPersistedState from 'vuex-persistedstate';
const store = createStore({
    modules: {
        messageStore,
    },
    plugins: [
        createPersistedState()
    ],
});

export default store;