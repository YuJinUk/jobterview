import { sendMessageAPI, receiveMessageAPI } from '@/api/messageApi';


export default {
    namespaced: true,
    state: {
        receiveMessage: null,
        sendMessage: null,
        totalReceivePage: 1,
        totalSendPage: 1,
        currentReceivePage: 1,
        currentSendPage: 1,
    },
    mutations: {
        SET_RECEIVE_MESSAGE: (state, messages) => {
            state.receiveMessage = messages;
        },
        SET_SEND_MESSAGE: (state, messages) => {   
            state.sendMessage = messages;
        },
        SET_TOTAL_SEND_PAGE: (state, pageNum) => {
            state.totalSendPage = pageNum;
        },
        SET_TOTAL_RECEIVE_PAGE: (state, pageNum) => {
            state.totalReceivePage = pageNum;
        },
        SET_CURRENT_RECEIVE_PAGE: (state, pageNum) => {
            state.currentReceivePage = pageNum;
        },
        SET_CURRENT_SEND_PAGE: (state, pageNum) => {
            state.currentSendPage = pageNum;
        },
    },
    getters: {
        getReceiveMessage: function (state) {
            return state.receiveMessage;
        },
        getSendMessage: function (state) {
            return state.sendMessage;
        },
        getTotalSendPage: function (state) {
            return state.totalSendPage;
        },
        getTotalReceivePage: function (state) {
            return state.totalReceivePage;
        },
        getCurrentSendPage: function (state) {
            return state.currentSendPage;
        },
        getCurrentReceivePage: function (state) {
            return state.currentReceivePage;
        },
    },
    actions: {
        async getReceiveMessages({ commit }, {nickname, page}) {
            await receiveMessageAPI({nickname, page},
                ({data}) => {
                    commit('SET_RECEIVE_MESSAGE', data.content);
                    commit('SET_TOTAL_RECEIVE_PAGE', data.totalPages);
                    console.log(data);
                    console.log(page);
                },
                (error) => {
                    console.log(error);
                })
        },
        async getSendMessages({ commit }, {nickname, page}) {
            await sendMessageAPI({nickname, page},
                ({data}) => {
                    commit('SET_SEND_MESSAGE', data.content);
                    commit('SET_TOTAL_SEND_PAGE', data.totalPages);
                    console.log(data.content);
                },
                (error) => {
                    console.log(error);
                })
        },

    }
}

