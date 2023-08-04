const roomStore = {
  namespaced: true,
  state: {
    selectedQuestions: [],
    enterRoomName : null, 
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
    SET_ROOM_NAME(state, roomName) {
      state.enterRoomName = roomName;
    }
  },
  actions: {
    //선택된 질문 목록 갱신
    setSelectedQuestions({ commit }, questions)  {
      commit("SET_SELECTED_QUESTIONS", questions);
    },
  }
};

export default roomStore;
