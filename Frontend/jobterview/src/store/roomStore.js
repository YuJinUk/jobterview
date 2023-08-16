import { receiveRoomList, receiveSearchRoomList } from "@/api/roomApi";
//import router from '@/router';

export default {
  namespaced: true,
  state: {
    selectedQuestions: [],
    receiveRooms: null,
    totalReceivePage: 1,
    currentReceivePage: 1,
    readRoomName: null,
    readNowMember: null,
    readMaxMember: null,
    readRoomPassword: null,
    selectGen: "",
  },
  getters: {
    //선택된 질문 목록 반환
    getSelectedQuestions(state) {
      return state.selectedQuestions;
    },

    getReceiveRooms: function (state) {
      return state.receiveRooms;
    },
    getTotalReceivePage: function (state) {
      return state.totalReceivePage;
    },
    getCurrentReceivePage: function (state) {
      return state.currentReceivePage;
    },
    getRoomName: function (state) {
      return state.readRoomName;
    },
    getNowMember: function (state) {
      return state.readNowMember;
    },
    getMaxMembe: function (state) {
      return state.readMaxMembe;
    },
  },
  mutations: {
    SET_SELECTED_QUESTIONS(state, questions) {
      state.selectedQuestions = questions;
    },
    SET_RECEIVE_ROOMS: (state, rooms) => {
      state.receiveRooms = rooms;
    },
    SET_TOTAL_RECEIVE_PAGE: (state, pageNum) => {
      state.totalReceivePage = pageNum;
    },
    SET_CURRENT_RECEIVE_PAGE: (state, pageNum) => {
      state.currentReceivePage = pageNum;
    },
    SET_READ_ROOMNAME: (state, roomName) => {
      state.readRoomName = roomName;
    },
    SET_READ_Now_Member: (state, nowMember) => {
      state.readNowMember = nowMember;
    },

    SET_READ_MAX_Member: (state, maxMember) => {
      state.readMaxMember = maxMember;
    },
    SET_READ_ROOM_PASSWORD: (state, roomPassword) => {
      state.readRoomPassword = roomPassword;
    },
    EMPTY_READ_ROOM_PASSWORD: (state) => {
      state.readRoomPassword = " ";
    },
    SET_SELECT_GEN: (state, gen) => {
      state.selectGen = gen;
    },
  },
  actions: {
    setSelectGen({ commit }, gen) {
      commit("SET_SELECT_GEN", gen); // SET_SELECT_GEN 뮤테이션 호출
    },
    //선택된 질문 목록 갱신
    setSelectedQuestions({ commit }, questions) {
      commit("SET_SELECTED_QUESTIONS", questions);
    },

    async getReceiveRooms({ commit }, page) {
      await receiveRoomList(
        page,
        ({ data }) => {
          commit("SET_RECEIVE_ROOMS", data.content);
          commit("SET_TOTAL_RECEIVE_PAGE", data.totalPages);
          commit("SET_CURRENT_RECEIVE_PAGE", page + 1);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async SearchRoomList({ commit }, { keyword, page }) {
      await receiveSearchRoomList(
        { keyword, page },
        ({ data }) => {
          commit("SET_RECEIVE_ROOMS", data.content);
          commit("SET_TOTAL_RECEIVE_PAGE", data.totalPages);
          commit("SET_CURRENT_RECEIVE_PAGE", page + 1);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
