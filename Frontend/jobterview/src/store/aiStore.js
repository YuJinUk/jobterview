export default ({
  namespaced: true,
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
    getvideo1(state) {
      return state.video1;
    },
    getvideo2(state) {
      return state.video2;
    },
    getvideo3(state) {
      return state.video3;
    },
    getvideo4(state) {
      return state.video4;
    },
  },
  mutations: {
    addResultData(state, data) {
      state.resultDataList.push(data);
    },
    clearResultDataList(state) {
      state.resultDataList = [];
    },
    addvideo1(state, data) {
      state.video1 = data;
    },
    addvideo2(state, data) {
      state.video2 = data;
    },
    addvideo3(state, data) {
      state.video3 = data;
    },
    addvideo4(state, data) {
      state.video4 = data;
    },
  },
  actions: {
    storeResultData({ commit }, data) {
      commit("addResultData", data);
    },
    clearResultData({ commit }) {
      commit("clearResultDataList");
    },
    storeVideo1({ commit }, data) {
      commit("addvideo1", data);
    },
    storeVideo2({ commit }, data) {
      commit("addvideo2", data);
    },
    storeVideo3({ commit }, data) {
      commit("addvideo3", data);
    },
    storeVideo4({ commit }, data) {
      commit("addvideo4", data);
    },
  },
  modules: {},
});
