import { sendMessageList, receiveMessageList, postMessage } from '@/api/messageApi';
import router from '@/router';

export default {
    namespaced: true,
    state: {
        receiveMessage: null,
        sendMessage: null,
        totalReceivePage: 1,
        totalSendPage: 1,
        currentReceivePage: 1,
        currentSendPage: 1,
        readContent: null,
        readCreatedDate: null,
        readSenderNickname: null,
        readReceiverNickname: null,
        readId: null,
        replyNickname: null,
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
        SET_READ_CONTENT: (state, content) => {
            state.readContent = content;
        },
        SET_READ_CREATED_DATE: (state, createdDate) => {
            state.readCreatedDate = createdDate;
        },
        SET_READ_SENDER_NICKNAME: (state, senderNickname) => {
            state.readSenderNickname = senderNickname;
        },
        SET_READ_RECEIVER_NICKNAME: (state, receiverNickname) => {
            state.readReceiverNickname = receiverNickname;
        },
        SET_READ_ID: (state, id) => {
            state.readId = id;
        },
        SET_REPLY_NICKNAME: (state, nickname) => {
            state.replyNickname = nickname;
        }
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
            await receiveMessageList({nickname, page},
                ({data}) => {
                    commit('SET_RECEIVE_MESSAGE', data.content);
                    commit('SET_TOTAL_RECEIVE_PAGE', data.totalPages);
                    commit('SET_CURRENT_RECEIVE_PAGE', page + 1);
                },
                (error) => {
                    console.log(error);
                })
        },
        async getSendMessages({ commit }, {nickname, page}) {
            await sendMessageList({nickname, page},
                ({data}) => {
                    commit('SET_SEND_MESSAGE', data.content);
                    commit('SET_TOTAL_SEND_PAGE', data.totalPages);
                    commit('SET_CURRENT_RECEIVE_PAGE', page + 1);   
                },
                (error) => {
                    console.log(error);
                })
        },     
        async postMessages(context, message) {
            await postMessage(message, () => {
                router.push({name:"MessageList"});
            },
            (error) => {
                console.log(error);
                alert("존재하지 않는 유저입니다");
            })
        }   
    }
}

