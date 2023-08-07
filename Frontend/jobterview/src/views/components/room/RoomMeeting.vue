<template>
  <div class="container-wrapper mt-3">
    <div class="main-container">
      <div class="text-center">
        <video ref="video" autoplay width="480" height="360" style="border-radius: 10%;"></video>
        <p class="text-center">{{ nickname }}</p>
        <p class="h2 text-center">
          <i v-if="camera" class="bi bi-camera-video-fill mx-3" @click="cameraClick"></i>
          <i v-else class="bi bi-camera-video-off-fill mx-3" @click="cameraClick"></i>
          <i v-if="mic" @click="muteClick" class="bi bi-mic-fill mx-3"></i>
          <i v-else @click="muteClick" class="bi bi-mic-mute-fill mx-3"></i>
          <i class="bi bi-box-arrow-right mx-3"></i>
        </p>

        <!-- <button @click="debug">디버그 버튼</button> -->
      </div>
    </div>
    <div v-for="user in users" :key="user.id" class="text-center">
      <UserVideo :info="user"></UserVideo>
    </div>
  </div>
  <div class="container-chatting" style="overflow-x: hidden;">
    <div class="user-list"> 
      <p><span>참여자 ({{ users.length + 1}})</span></p>
      <div class="user-nickname">
        <p><i class="bi bi-person-fill"></i><span>{{ nickname }}</span><i class="bi bi-exclamation-triangle-fill text-danger report"></i></p>
        <p v-for="user in users" :key="user.id"><i class="bi bi-person-fill"></i><span>{{ user.nickname }}</span><i class="bi bi-exclamation-triangle-fill text-danger report"></i></p>
      </div>
    </div>
    <div class="chatting mt-3">
      <p><span>실시간 채팅</span></p>
      <div class="chat-list">
        <div v-for="chat in chats" :key="chat.id">
          <p class="chat-nickname">{{ chat.nickname }} 님의 채팅</p>
          <p class="chat-content">{{ chat.content }}</p>
        </div>
      </div>
      <div class="row mt-4 chat-input">
        <div class="col-7">
          <input type="text" class="form-control" placeholder="채팅 입력..." v-model="chatContent" @keydown.enter.prevent="sendChat()">
        </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-secondary" @click="sendChat()">입력</button>
        </div>
      </div>


    </div>
  </div>
</template>

<script>
import UserVideo from "./UserVideo.vue";
import { mapState } from "vuex";
export default {
  name: "RoomMeeting",
  components: {
    UserVideo,
  },
  computed: {
    ...mapState("loginStore", ["loginNickname"]),
    ...mapState("roomStore", ["readRoomName", "readMaxMember"]),
  },
  mounted() {
    this.enter_room();
  },
  created() {
    this.$socket.on("all_users", async (allUsers) => {
      // console.log(allUsers);
      for (let i = 0; i < allUsers.length; i++) {
        //소켓id , nickname
        let pc = this.createPeerConnection(allUsers[i].id, allUsers[i].nickname); //RTC객체 생성하고 배열에 담고 이벤트등록 및 트랙등록해서 리턴
        const offer = await pc.createOffer();
        pc.setLocalDescription(offer);
        // console.log("sendOffer 보내는 곳");
        // console.log(allUsers[i].id);
        this.$socket.emit("offer", {
          offer,
          offerSendID: this.$socket.id,
          offerReceiveID: allUsers[i].id,
          offerSendNickname: this.nickname,
        });
      }
    });

    this.$socket.on("getOffer", async (data) => {
      // console.log("receive offer");
      let pc = this.createPeerConnection(data.offerSendID, data.offerSendNickname);
      pc.setRemoteDescription(data.offer);
      const answer = await pc.createAnswer();
      // console.log(answer);
      pc.setLocalDescription(answer);
      this.$socket.emit("answer", {
        answer,
        answerSendID: this.$socket.id,
        answerReceiveID: data.offerSendID,
      });
      // console.log("sent answer");
    });
    this.$socket.on("getAnswer", (data) => {
      // console.log("receive the answer");
      let pc = this.pcs[data.answerSendID];
      pc.setRemoteDescription(data.answer);
    });

    this.$socket.on("getCandidate", (data) => {
      // console.log("recive candidate");
      let pc = this.pcs[data.candidateSendID];
      pc.addIceCandidate(data.candidate);
    });

    this.$socket.on("user_exit", (data) => {
      this.pcs[data.id].close();
      delete this.pcs[data.id];
      this.users = this.users.filter((user) => user.id !== data.id);
      // console.log(this.users);
    });
  },
  data: () => {
    return {
      myStream: {},
      nickname: "",
      mic: true,
      camera: true,
      chat: false,
      roomName: "",
      users: [], // 참여자 객체 저장하는 배열
      chats: [], // 채팅 객체 저장하는 배열
      chatContent: "", // 채팅 내용
      pcs: {},
      maxNum: 0,
    };
  },
  props: {
    msg: String,
  },
  methods: {
    debug() {
      for (let i in this.pcs) {
        console.log(this.pcs[i].getConfiguration());
      }
    },
    // this.createPeerConnection(allUsers[i].id,allUsers[i].nickname);
    createPeerConnection(socketID, nickname) {
      let pc = new RTCPeerConnection({
        iceServers: [
          {
            urls: "stun:i9a701.p.ssafy.io:3478",
          },
          {
            urls: "turn:i9a701.p.ssafy.io:3478",
            username: "ksc",
            credential: "036",
          },
        ],
      });
      //       const H264Codec = {
      //   mimeType: 'video/H264',
      //   clockRate: 90000,
      //   payloadType: 101, // 사용 가능한 Payload Type 중에 선택 (충돌이 없도록 지정)
      // };
      // pc.addTransceiver('video', {
      //   direction: 'sendrecv',
      //   streams: [],
      //   codecs: [H264Codec],
      // });
      this.pcs = { ...this.pcs, [socketID]: pc };

      pc.addEventListener("icecandidate", (data) => this.handleIceCandidate(data, socketID));
      pc.addEventListener("addstream", (data) => this.handleAddStream(data, socketID, nickname));
      this.myStream.getTracks().forEach((track) => {
        pc.addTrack(track, this.myStream);
      });

      return pc;
    },
    handleAddStream(data, socketID, nickname) {
      // console.log("got my peer");
      this.users.push({ id: socketID, nickname, stream: data.stream });
    },
    handleIceCandidate(data, socketID) {
      // console.log("send candidate");
      this.$socket.emit("candidate", {
        candidate: data.candidate,
        candidateSendID: this.$socket.id,
        candidateReceiveID: socketID,
      });
    },
    cameraClick() {
      this.myStream.getVideoTracks().forEach((track) => (track.enabled = !track.enabled));
      // console.log(this.myStream.getVideoTracks());x
      this.camera = !this.camera;
    },
    muteClick() {
      // console.log(this.myStream.getAudioTracks());
      this.myStream.getAudioTracks().forEach((track) => (track.enabled = !track.enabled));
      // console.log(this.myStream.getAudioTracks());
      this.mic = !this.mic;
    },

    //클릭하면 enter_room 실행된다.
    async enter_room() {
      await this.initCall(); //자신의 track이랑 video audio를 등록해놓고 시그널링 서버에 접속
      // this.$socket.emit("join_room", this.roomName);
    },
    async initCall() {
      this.chat = !this.chat;
      await this.getMedia();
      this.nickname = this.loginNickname;
      this.roomName = this.readRoomName;
      this.maxNum = this.readMaxMember;
      this.$socket.emit("join_room", {
        //all user 시작하는거임 //offer도 저기서 완성시키고 보냄
        // store의 로그인 닉네임, url parameter의 roomNumber 받아오기
        nickname: this.nickname,
        roomName: this.roomName,
        maxNum: this.maxNum,
      });
    },
    async getMedia() {
      try {
        this.myStream = await navigator.mediaDevices.getUserMedia({
          audio: true,
          video: true,
        });
        // console.log(this.$refs.video);
        this.$refs.video.srcObject = this.myStream;
      } catch (e) {
        console.log(e);
      }
    },
    sendChat() {
      let chat = {
        nickname: this.nickname,
        content: this.chatContent,
      }
      if(chat.content != "") {
        this.chats.push(chat);
        this.chatContent = "";
      }
    },
  },
};
</script>

<style scoped>
.container-wrapper {
  display: grid;
  grid-template-columns: repeat(3, 400px);
  grid-template-rows: repeat(3, 240px);
  grid-template-areas:
    ". . . "
    "myCam myCam . "
    "myCam myCam . ";
}

.main-container {
  grid-area: myCam;
}

.container-chatting {
  left: 80%;
  top: 15%;
  position: absolute;
}

.user-list {
  background-color: #EAEAEA;
  border-radius: 5px;
  width: 300px;
  height: 320px;
  right: 20px;
}

.user-list > div {
  background-color: #FFFFFF;
  border-radius: 5px;
  width: 250px;
  height: 245px;
  position: absolute;
  top: 50px;
  left: 25px;
}

.user-list > p {
  position: absolute;
  left: 25px;
  top: 15px;
}
.user-nickname p {
  position: relative;
  left : 10px;
  top: 10px;
}

.user-nickname span {
  position: relative;
  left : 10px;
}

.user-nickname .report {
  position: absolute;
  right: 40px;
}

.chatting {
  background-color: #EAEAEA;
  border-radius: 5px;
  width: 300px;
  height: 370px;
  top: 30px;
  right: 20px;
}

.chat-list {
  background-color: #FFFFFF;
  border-radius: 5px;
  width: 250px;
  height: 250px;
  position: relative;
  top: 10px;
  left: 25px;
  overflow-x: hidden;
  overflow-y: auto;
}

.chatting > p {
  position: relative;
  left: 25px;
  top: 15px;
}

.chat-input {
  position: relative;
  left: 25px;
}

.chat-nickname {
  font-size: 8px;
  height: 8px;
  position: relative;
  left: 8px;
}

.chat-content {
  position: relative;
  left: 8px;
}
</style>
