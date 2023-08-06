<template>
      <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>

    <div id="videoBox">
      <div id="userVideo"></div>
      <div id="crossLine">
        <div class="row">
          <div class="box box1"></div>
          <div class="box box2"></div>
        </div>
        <div class="row">
          <div class="box box3"></div>
          <div class="box box4"></div>
        </div>
      </div>
    </div>

    <div id="buttonBox">
      <button @click="toRoomList()">뒤로</button>
      <button @click="enterRoom()">다음</button>
    </div>
  </div>
</template>

<script>
import { useRoute } from 'vue-router';
import { ref } from 'vue';
import router from '@/router';
export default {
    name: "RoomPermission",
    setup() {
        const route = useRoute();
        const headText = ref("마이크와 카메라를 준비중입니다.");

        function toRoomList() {
            router.push({name: "RoomList"});
        }

        function enterRoom() {
            router.push({name: "RoomMeeting", roomId: route.params.roomId});
        }   
        return {
            headText,
            toRoomList,
            enterRoom,
        };
    },
}

</script>


<style scoped src="@/css/permissionView.css" />