<template>
  <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>

    <div id="videoBox">
      <div id="userVideo">
        <video ref="video" autoplay width="800" height="500" style="border-radius: 10%;"></video>
      </div>
      <div id="crossLine" v-if="video">
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
import { ref } from 'vue';
import router from '@/router';
// import { useStore } from 'vuex';
export default {
  name: "RoomPermission",
  mounted() {
    this.getMedia();
  },
  setup() {

    // const store = useStore();
    const headText = ref("마이크와 카메라를 준비중입니다.");
    const camera = ref(true);
    const mic = ref(true);
    const myStream = ref({});
    function toRoomList() {
      router.push({ name: "RoomList" });
    }

    function enterRoom() {
      // store.commit("roomStore/SET_ROOM_NAME", "Test Room");
      router.push({ name: "RoomMeeting" });
    }
    async function getMedia() {
      try {
        this.myStream = await navigator.mediaDevices.getUserMedia({
          audio: true,
          video: true,
        });
        this.$refs.video.srcObject = this.myStream;
      } catch (e) {
        console.log(e);
      }
    }

    function cameraClick() {
      this.myStream.getVideoTracks().forEach((track) => (track.enabled = !track.enabled));
      this.camera = !this.camera;
    }

    function muteClick() {
      // console.log(this.myStream.getAudioTracks());
      this.myStream.getAudioTracks().forEach((track) => (track.enabled = !track.enabled));
      // console.log(this.myStream.getAudioTracks());
      this.mic = !this.mic;
    }
    
    return {
      headText,
      toRoomList,
      enterRoom,
      getMedia, 
      cameraClick,
      muteClick,
      camera,
      mic,
      myStream,
    };
  },
}

</script>


<style scoped src="@/css/permissionView.css" />