import { getQuestionListAPI } from "@/api/roomApi";

const roomStore = {
  namespaced: true,
  state: {
    // selectedCategory: "",
    // categoryList: [
    //     { value: "VISION", name: "비전 / 목표 질문"},
    //     { value: "ADAPT", name: "적응력 질문"},
    //     { value: "VALUES", name: "가치관 질문"},
    //     { value: "PRESSURE", name: "압박 질문"},
    //     { value: "JOB", name: "직무 질문"},
    // ],
    selectedQuestions: [],
    questionList: [],
  },
  getters: {
    // //선택된 카테고리 반환
    // getSelectedCategory(state) {
    //   return state.selectedCategory;
    // },
    // //카테고리 목록 반환
    // getCategoryList(state) {
    //   return state.categoryList;
    // },
    //선택된 질문 목록 반환
    getSelectedQuestions(state) {
      return state.selectedQuestions;
    },
    //질문 목록 반환
    getQuestionList(state) {
      console.log(state.questionList);
      return state.questionList;
    },
  },
  mutations: {
    // SET_SELECTED_CATEGORY(state, category) {
    //   state.selectedCategory = category;
    // },
    // SET_SELECTED_CATEGORY_LIST(state, categoryList) {
    //   state.categoryList = categoryList;
    // },
    SET_SELECTED_QUESTIONS(state, questions) {
      state.selectedQuestions = questions;
    },
    SET_SELECTED_QUESTION_LIST(state, questionList) {
      console.log(questionList);
    },
  },
  actions: {
    //카테고리 선택시 질문 리스트, 선택된 카테고리 갱신
    async setCategoryAndQuestionList({ commit }, category) {
      //해당 카테고리의 질문 리스트 조회
      await getQuestionListAPI(
        category,
        ({ data }) => {
          //질문 리스트 갱신
          commit("SET_SELECTED_QUESTION_LIST", data.content);
          console.log('getQuestionListAPI',data.content);
        },
        (error) => console.log(error)
      );
      // //선택된 카테고리 갱신
      // commit("SET_SELECTED_CATEGORY", category);
      // console.log('SET_SELECTED_CATEGORY', category);
    },
  },

  //선택된 질문 목록 갱신
  setSelectedQuestions({ commit }, { questions }) {
    commit("SET_SELECTED_QUESTIONS", questions);
    console.log(questions);
  },
};

export default roomStore;
