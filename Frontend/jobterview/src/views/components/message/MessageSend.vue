<template>
    <div class="background-box mt-5">
        <div class="background-inbox d-flex flex-column">
            <form>
                <div class="form-group">
                    <label for="to">받는 사람</label>
                    <input type="text" class="form-control" id="to" placeholder="닉네임 입력..." v-model="receiverNickname">
                </div>
                <div class="form-group">
                    <label for="content">내용</label>
                    <textarea class="form-control" id="content" rows="15" placeholder="내용 입력..." v-model="content"></textarea>
                </div>
            </form>
        </div>
        <div class="row d-flex justify-content-center mt-4">
            <button class="btn btn-primary col-1" @click="sendMessage()">전송</button>
        </div>
    </div>
</template>

<script>
//import router from '@/router';
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
    name: "MessageSend",
    mounted() {

    },
    setup() {
        const store = useStore();
        const receiverNickname = ref('');
        const content = ref('');

        function sendMessage() {
            if (confirm("메시지를 전송하시겠습니까?")) {
                const message = {
                    senderNickname: 'reporter',
                    receiverNickname: receiverNickname.value,
                    content: content.value,
                };
                store.dispatch('messageStore/postMessages', message);
                // router.push({ name: 'MessageList' });
            }

        }

        onMounted(() => {
            if (store.state.messageStore.replyNickname) {
                receiverNickname.value = store.state.messageStore.replyNickname;
                store.commit("messageStore/SET_REPLY_NICKNAME", null);
            }
        });

        return {
            receiverNickname,
            content,
            sendMessage,
        };
    }
}
</script>

<style scoped>
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
    text-align: center;
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
</style>