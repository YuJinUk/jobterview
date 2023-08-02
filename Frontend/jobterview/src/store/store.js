import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

import messageStore from './messageStore';
import loginStore from './loginStore';
import roomStore from './roomStore';
import homeStore from './homeStore';

const store = createStore({
    modules: {
        homeStore,
        roomStore,
        messageStore,
        loginStore,
    },
    plugins: [
        createPersistedState()
    ],
    state: {
        bannerText: '공지사항입니다.', 
      },
});

export default store;