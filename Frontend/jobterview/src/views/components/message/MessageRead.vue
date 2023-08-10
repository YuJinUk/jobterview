<template>
    <ReportModal v-if="displayModal" @close-modal-event="hideModal" :reporterNickname="this.receiverNickname"
        :reportedNickname="this.senderNickname"></ReportModal>
    <div class="container">
        <!-- 수신 메시지 조회 -->
        <div v-if="$route.params.category == 'receive'">
            <div class="background-box mt-5">
                <div class="background-inbox d-flex flex-column">
                    <div class="row message">
                        <div class="mt-2 message-nickname">
                            <p>From : <span v-text="senderNickname" id="sender"></span></p>
                        </div>
                        <div class="mt-3 message-content">
                            <p v-text="content"></p>
                        </div>
                        <div class="message-date">
                            <p v-text="createdDate"></p>
                        </div>
                    </div>
                </div>
                <div class="row d-flex justify-content-center mt-4">
                    <button class="btn btn-primary col-1" @click="toSend(this.senderNickname)">답장</button>
                    <p class="col-1"></p>
                    <button class="btn btn-primary col-1" @click="showModal()">신고</button>
                    <p class="col-1"></p>
                    <button class="btn btn-primary col-1" @click="deleteReceiveMessage(this.id)">삭제</button>
                </div>
            </div>
        </div>
        <!-- 송신 메시지 조회 -->
        <div v-else>
            <div class="background-box mt-5">
                <div class="background-inbox d-flex flex-column">
                    <!-- <div class="form-group">
                        <label for="to">받는 사람</label>
                        <input type="text" class="form-control" id="to" v-model="receiverNickname" readonly>
                    </div>
                    <div class="form-group">
                        <label for="content">내용</label>
                        <textarea class="form-control" id="content" rows="11" v-model="content" readonly></textarea>
                    </div>
                    <div class="form-group">
                        <label for="to">보낸 날짜</label>
                        <input type="text" class="form-control" id="to" v-model="createdDate" readonly>
                    </div> -->
                    <div class="row message">
                        <div class="mt-2 message-nickname">
                            <p>To : <span v-text="receiverNickname" id="sender"></span></p>
                        </div>
                        <div class="mt-3 message-content">
                            <p v-text="content"></p>
                        </div>
                        <div class="message-date">
                            <p v-text="createdDate"></p>
                        </div>
                    </div>
                </div>
                <div class="row d-flex justify-content-center mt-4">
                    <button class="btn btn-danger col-1" @click="deleteSendMessage(this.id)">삭제</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref } from 'vue';
import router from '@/router';
import { deleteSentMessage, deleteReceivedMessage } from '@/api/messageApi';
import ReportModal from '../ReportModal.vue';
export default {
    name: "MessageRead",
    components: {
        ReportModal,
    },
    mounted() {

    },
    setup() {
        const store = useStore();
        const content = computed(() => store.state.messageStore.readContent);
        const createdDate = computed(() => store.state.messageStore.readCreatedDate);
        const senderNickname = computed(() => store.state.messageStore.readSenderNickname);
        const receiverNickname = computed(() => store.state.messageStore.readReceiverNickname);
        const id = computed(() => store.state.messageStore.readId);
        const displayModal = ref(false);

        async function deleteReceiveMessage(id) {
            if (!confirm("삭제하시겠습니까?")) {
                console.log('취소');
            } else {
                console.log(id);
                deleteReceivedMessage(id, (response) => {
                    console.log(response);
                    router.push({ name: "MessageList" });
                }, (error) => {
                    console.log(error);
                })
            }

        }

        async function deleteSendMessage(id) {
            if (!confirm("삭제하시겠습니까?")) {
                console.log('취소');
            } else {
                console.log(id);
                deleteSentMessage(id, (response) => {
                    console.log(response);
                    router.push({ name: "MessageList" });
                }, (error) => {
                    console.log(error);
                })
            }
        }

        function toSend(nickname) {
            store.commit("messageStore/SET_REPLY_NICKNAME", nickname);
            router.push({ name: "MessageSend" });
        }


        function showModal() {
            displayModal.value = true;
        }

        function hideModal() {
            console.log("hide");
            displayModal.value = false;
        }

        return {
            content,
            createdDate,
            senderNickname,
            receiverNickname,
            id,
            deleteReceiveMessage,
            deleteSendMessage,
            toSend,
            showModal,
            hideModal,
            displayModal,
        };
    }
}
</script>

<style scoped>
* {
    font-size: 20px;
}
.container {
    position: relative;
    height: 100vh;
    /* 화면 높이에 맞춰 요소를 중앙에 배치하려면 필요합니다. */
}

.background-box {
    border: 1px solid #ccc;
    /* 테두리를 표시하기 위한 스타일 */
    margin: auto;
    width: 900px;
    height: 600px;
    background-color: #EAEAEA;
    border-radius: 20px;
    /* 필요한 스타일을 추가하세요 */
}

.content {
    margin-top: 150px;
    text-align: center;
}

.background-inbox {
    /* text-align: center; */
    display: flex;
    margin-top: 50px;
    margin-left: 80px;
    width: 700px;
    height: 450px;
    background-color: #FFFFFF;
    border-radius: 20px;
    /* 필요한 스타일을 추가하세요 */
}

.date {
    justify-content: space-evenly;
}

.background-box {
    /* 기타 스타일 */
    border: 1px solid #ccc;
    padding: 20px;
}

.d-flex {
    display: flex;
}

.flex-column {
    flex-direction: column;
}

.date {
    margin-top: auto;
}

input {
    text-align: center;
}

textarea {
    text-align: center;
}

.context-box {
    background-color: aquamarine !important;
    /* text-align: center; */
}

.btn {
    background-color: #0f4471;
    font: 700 16px/18px "Lato", sans-serif;
    border: none;
}

#sender {
    font-weight: bolder;
}

.message {
    position : relative;
    left: 50px;
    top: 20px;
    width: 600px;   
}

.message-content {
    height : 300px;
}

.message-date {
    position: relative;
}
</style>