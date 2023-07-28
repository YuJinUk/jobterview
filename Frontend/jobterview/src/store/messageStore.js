import { getSendMessage, getReceivedMessage } from '@/api/messageApi';


export default {
    state: {
        receivedMessage: null,
        sendMessage: null,
        totalPages: 1,
        currentReceivePage: 1,
    },
    mutations: {

    },
    getters: {

    },
    actions: {
        async getReceivedMessages({ commit }, nickname) {
            await getReceivedMessage(nickname,
                ({data}) => {
                    
                })
        },
    },
}

