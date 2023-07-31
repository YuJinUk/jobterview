import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

import roomStore from './roomStore';
import homeStore from './homeStore';

const store = createStore({
    modules: {
        homeStore,
        roomStore,
    },
    plugins: [
        createPersistedState()
    ],
});

export default store;