import { createStore } from 'vuex'

export default createStore({
  state: {
    resultDataList: [],
    video1: null,
    video2: null,
    video3: null,
    video4: null,
  },
  getters: {
    getResultDataList(state) {
      return state.resultDataList;
    },
  },
  mutations: {
    addResultData(state, data) {
      state.resultDataList.push(data);
    },
    clearResultDataList(state) {
      state.resultDataList = [];
    },
  },
  actions: {
    storeResultData({ commit }, data) {
      commit('addResultData', data);
    },
    clearResultData({ commit }) {
      commit('clearResultDataList');
    },
  },
  modules: {
  }
})
