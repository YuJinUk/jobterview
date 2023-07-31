<template>
    <!-- 수신 메시지 조회 -->
    <div v-if="$route.params.category == 'receive'">
        <p>수신 메시지 조회</p>
        <p>{{ this.content }}</p>
        <p>{{ this.createdDate }}</p>
        <p>{{ this.senderNickname }}</p>
    </div>
    <!-- 송신 메시지 조회 -->
    <div v-else>
        <p>송신 메시지 조회</p>
        <p>{{ this.content }}</p>
        <p>{{ this.createdDate }}</p>
        <p>{{ this.receiverNickname }}</p>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
    name: "MessageRead",
    components: {

    },
    mounted() {

    },
    setup() {
        const store = useStore();
        const content = computed(() => store.state.messageStore.readContent);
        const createdDate = computed(() => store.state.messageStore.readCreatedDate);
        const senderNickname = computed(() => store.state.messageStore.readSenderNickname);
        const receiverNickname = computed(() => store.state.messageStore.readReceiverNickname);
        return {
            content,
            createdDate,
            senderNickname,
            receiverNickname,
        };
    }
}
</script>

<style scoped></style>