<template>
    <div v-if="!chat">
        <h1>Room</h1>
    </div>
    <div v-else>

    </div>
</template>

<script>
import { ref } from 'vue-router';

export default {
    name: "RoomMeeting",
    components: {
        
    },
    created() {

    },
    setup() {
        const chat = ref(false);


        return {
            chat,
        };
    },
}
</script>

<style scoped>
</style>