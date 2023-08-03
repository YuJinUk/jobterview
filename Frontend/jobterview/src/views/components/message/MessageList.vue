<template>
    <div class="container" style="width: 1080px;">
        <div class="row justify-content-end mt-3">
            <button class="btn btn-primary col-1" @click="toSendMessage()" id="write">작성</button>     
        </div>
        <div class="row justify-content-center1">
            <ul class="nav nav-tabs" id="messageTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="receive-tab" data-bs-toggle="tab" data-bs-target="#receive"
                        type="button" role="tab" aria-controls="receive" aria-selected="true"
                        @click="changeReceivePage(1)">받은 쪽지함</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="send-tab" data-bs-toggle="tab" data-bs-target="#send" type="button"
                        role="tab" aria-controls="send" aria-selected="false" @click="changeSendPage(1)">보낸 쪽지함</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active mt-2" id="receive" role="tabpanel" aria-labelledby="receive-tab">
                    <table class="table table-striped content-justify-center text-center">
                        <thead>
                            <tr>
                                <th class="col-6 ">내용</th>
                                <th class="col-2">수신 일자</th>
                                <th class="col-2">보낸 사람</th>
                                <th class="col-1"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="data in receiveMessage" :key="data.id">
                                <td @click="toReadMessage(data, 'receive')">{{ data.content }}</td>
                                <td>{{ data.createdDate }}</td>
                                <td>{{ data.senderNickname }}</td>
                                <td><button class="btn btn-danger deleteButton"
                                        @click="deleteReceiveMessage(data)">삭제</button></td>
                            </tr>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === 1 }">
                                <a class="page-link" href="#" @click="changeReceivePage(1)" aria-label="first">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === 1 }">
                                <a class="page-link" href="#" @click="changeReceivePage(currentReceivePage - 1)"
                                    aria-label="Previous">
                                    <span aria-hidden="true">&lt;</span>
                                </a>
                            </li>
                            <template v-if="currentReceivePage === 1">
                                <li v-for="firstPageNumber in Math.min(totalReceivePage, 3)" :key="firstPageNumber"
                                    :class="{ 'active': currentReceivePage === firstPageNumber }">
                                    <a class="page-link" href="#" @click="changeReceivePage(firstPageNumber)">{{
                                        firstPageNumber }}</a>
                                </li>
                            </template>
                            <template v-else-if="currentReceivePage === totalReceivePage">
                                <li v-for="LastPageNumber in receiveLastPageRange" :key="LastPageNumber"
                                    :class="{ 'active': currentReceivePage === LastPageNumber }">
                                    <a class="page-link" href="#" @click="changeReceivePage(LastPageNumber)">{{
                                        LastPageNumber }}</a>
                                </li>
                            </template>
                            <template v-else>
                                <li v-for="pageNumber in receivePageRange" :key="pageNumber"
                                    :class="{ 'active': currentReceivePage === pageNumber }">
                                    <a class="page-link" href="#" @click="changeReceivePage(pageNumber)">{{ pageNumber
                                    }}</a>
                                </li>
                            </template>
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === totalReceivePage }">
                                <a class="page-link" href="#" @click="changeReceivePage(currentReceivePage + 1)"
                                    aria-label="Next">
                                    <span aria-hidden="true">&gt;</span>
                                </a>
                            </li>
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === totalReceivePage }">
                                <a class="page-link" href="#" @click="changeReceivePage(totalReceivePage)"
                                    aria-label="Last">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="tab-pane fade mt-2" id="send" role="tabpanel" aria-labelledby="send-tab">
                    <table class="table table-striped content-justify-center text-center">
                        <thead>
                            <tr>
                                <th class="col-6 ">내용</th>
                                <th class="col-2">송신 일자</th>
                                <th class="col-2">받는 사람</th>
                                <th class="col-1"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="data in sendMessage" :key="data.id">
                                <td @click="toReadMessage(data, 'send')">{{ data.content }}</td>
                                <td>{{ data.createdDate }}</td>
                                <td>{{ data.receiverNickname }}</td>
                                <td><button class="btn btn-danger deleteButton" @click="deleteSendMessage(data)">삭제</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item" :class="{ 'disabled': currentSendPage === 1 }">
                                <a class="page-link" href="#" @click="changeSendPage(1)" aria-label="first">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item" :class="{ 'disabled': currentSendPage === 1 }">
                                <a class="page-link" href="#" @click="changeSendPage(currentSendPage - 1)"
                                    aria-label="Previous">
                                    <span aria-hidden="true">&lt;</span>
                                </a>
                            </li>
                            <template v-if="currentSendPage === 1">
                                <li v-for="firstPageNumber in Math.min(totalSendPage, 3)" :key="firstPageNumber"
                                    :class="{ 'active': currentSendPage === firstPageNumber }">
                                    <a class="page-link" href="#" @click="changeSendPage(firstPageNumber)">{{
                                        firstPageNumber }}</a>
                                </li>
                            </template>
                            <template v-else-if="currentSendPage === totalSendPage">
                                <li v-for="LastPageNumber in sendLastPageRange" :key="LastPageNumber"
                                    :class="{ 'active': currentSendPage === LastPageNumber }">
                                    <a class="page-link" href="#" @click="changeSendPage(LastPageNumber)">{{
                                        LastPageNumber }}</a>
                                </li>
                            </template>
                            <template v-else>
                                <li v-for="pageNumber in sendPageRange" :key="pageNumber"
                                    :class="{ 'active': currentSendPage === pageNumber }">
                                    <a class="page-link" href="#" @click="changeSendPage(pageNumber)">{{ pageNumber
                                    }}</a>
                                </li>
                            </template>
                            <li class="page-item" :class="{ 'disabled': currentSendPage === totalSendPage }">
                                <a class="page-link" href="#" @click="changeSendPage(currentSendPage + 1)"
                                    aria-label="Next">
                                    <span aria-hidden="true">&gt;</span>
                                </a>
                            </li>
                            <li class="page-item" :class="{ 'disabled': currentSendPage === totalSendPage }">
                                <a class="page-link" href="#" @click="changeSendPage(totalSendPage)" aria-label="Last">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import router from '@/router';
import { computed } from 'vue';
import { useStore } from 'vuex';
import { deleteReceivedMessage, deleteSentMessage } from '@/api/messageApi';
export default {
    name: 'MessageList',
    mounted() {
        this.fetchReceiveMessage();
        this.fetchSendMessage();
    },
    setup() {
        const store = useStore();
        const currentReceivePage = computed(() => store.state.messageStore.currentReceivePage);
        const totalReceivePage = computed(() => store.state.messageStore.totalReceivePage);
        const receiveMessage = computed(() => store.state.messageStore.receiveMessage);

        const currentSendPage = computed(() => store.state.messageStore.currentSendPage);
        const totalSendPage = computed(() => store.state.messageStore.totalSendPage);
        const sendMessage = computed(() => store.state.messageStore.sendMessage);   

        const nickname = computed(() => store.state.loginStore.loginNickname);

        let receivePageRange = computed(() => {
            return Array.from({ length: 3 }, (_, index) => currentReceivePage.value - 1 + index);
        })

        let receiveLastPageRange = computed(() => {
            if (totalReceivePage.value == 2) {
                return [1, 2];
            } else {
                return Array.from({ length: currentReceivePage.value - (currentReceivePage.value - 2) + 1 }, (_, index) => currentReceivePage.value - 2 + index);
            }
        })

        // 화면에서 페이지 누르면 현재 페이지 변경
        function changeReceivePage(pageNumber) {
            store.commit('messageStore/SET_CURRENT_RECEIVE_PAGE', pageNumber);
            console.log(currentReceivePage.value);
            fetchReceiveMessage();
        }


        async function fetchReceiveMessage() {
            await store.dispatch('messageStore/getReceiveMessages', { nickname: nickname.value, page: currentReceivePage.value - 1 });
        }

        let sendPageRange = computed(() => {
            return Array.from({ length: 3 }, (_, index) => currentSendPage.value - 1 + index);
        })

        let sendLastPageRange = computed(() => {
            if (totalSendPage.value == 2) {
                return [1, 2];
            } else {
                return Array.from({ length: currentSendPage.value - (currentSendPage.value - 2) + 1 }, (_, index) => currentSendPage.value - 2 + index);
            }
        })

        // 화면에서 페이지 누르면 현재 페이지 변경
        function changeSendPage(pageNumber) {
            store.commit('messageStore/SET_CURRENT_SEND_PAGE', pageNumber);
            fetchSendMessage();
        }


        async function fetchSendMessage() {
            await store.dispatch('messageStore/getSendMessages', { nickname: nickname.value, page: currentSendPage.value - 1 });
        }

        function toReadMessage(data, isReceive) {
            store.commit('messageStore/SET_READ_CONTENT', data.content);
            store.commit('messageStore/SET_READ_CREATED_DATE', data.createdDate);
            store.commit('messageStore/SET_READ_SENDER_NICKNAME', data.senderNickname);
            store.commit('messageStore/SET_READ_RECEIVER_NICKNAME', data.receiverNickname);
            store.commit('messageStore/SET_READ_ID', data.id);
            console.log(data);
            router.push({ name: 'MessageRead', params: { category: isReceive } });
        }

        function toSendMessage() {
            router.push({ name: 'MessageSend' });
        }

        async function deleteReceiveMessage(data) {
            if(!confirm("삭제하시겠습니까?")) {
                console.log('취소');
            } else {
                console.log(data.id);
                deleteReceivedMessage(data.id, (response) => {
                    console.log(response);
                    fetchReceiveMessage();
                }, (error) => {
                    console.log(error);
                })
            }
            
        }

        async function deleteSendMessage(data) {
            if(!confirm("삭제하시겠습니까?")) {
                console.log('취소');
            } else {
                console.log(data.id);
                deleteSentMessage(data.id, (response) => {
                    console.log(response);
                    fetchSendMessage();
                }, (error) => {
                    console.log(error);
                })
            }
        }

        return {
            currentReceivePage,
            totalReceivePage,
            receivePageRange,
            receiveLastPageRange,
            receiveMessage,
            fetchReceiveMessage,
            changeReceivePage,
            currentSendPage,
            totalSendPage,
            sendPageRange,
            sendLastPageRange,
            sendMessage,
            fetchSendMessage,
            changeSendPage,
            toReadMessage,
            toSendMessage,
            deleteReceiveMessage,
            deleteSendMessage,
        };
    }
}
</script>

<style>
.pagination {
    justify-content: center;
}

.deleteButton {
    border-radius: 10px;
}

#write {
    background-color: #0f4471;
    font: 700 16px/18px "Lato", sans-serif;
}
</style>