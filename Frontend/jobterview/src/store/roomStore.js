const roomStore = {
  namespaced: true,
  state: {
    selectedQuestions: [],
  },
  getters: {
    //선택된 질문 목록 반환
    getSelectedQuestions(state) {
      return state.selectedQuestions;
    },
  },
  mutations: {
    SET_SELECTED_QUESTIONS(state, questions) {
      state.selectedQuestions = questions;
    },
  },
  actions: {
    //선택된 질문 목록 갱신
    setSelectedQuestions({ commit }, questions)  {
      commit("SET_SELECTED_QUESTIONS", questions);
    },
  }
};

export default roomStore;
