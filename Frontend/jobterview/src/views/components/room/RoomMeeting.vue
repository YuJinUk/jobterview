<template>
    <div class="container-wrapper">
        <div class="main-container">
            <div class="text-center">
                <video ref="video" autoplay width="480" height="360"></video>
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
        ...mapState("roomStore", ["enterRoomName", "maxMember"]),
    },
    mounted() {
        this.enter_room();
    },
    created() {
        this.$socket.on("all_users", async (allUsers) => {
            // console.log(allUsers);
            for (let i = 0; i < allUsers.length; i++) {
                //소켓id , nickname
                let pc = this.createPeerConnection(
                    allUsers[i].id,
                    allUsers[i].nickname
                ); //RTC객체 생성하고 배열에 담고 이벤트등록 및 트랙등록해서 리턴
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
            let pc = this.createPeerConnection(
                data.offerSendID,
                data.offerSendNickname
            );
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
            users: [],
            pcs: {},
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

            pc.addEventListener("icecandidate", (data) =>
                this.handleIceCandidate(data, socketID)
            );
            pc.addEventListener("addstream", (data) =>
                this.handleAddStream(data, socketID, nickname)
            );
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
            this.myStream
                .getVideoTracks()
                .forEach((track) => (track.enabled = !track.enabled));
            // console.log(this.myStream.getVideoTracks());x
            this.camera = !this.camera;
        },
        muteClick() {
            // console.log(this.myStream.getAudioTracks());
            this.myStream
                .getAudioTracks()
                .forEach((track) => (track.enabled = !track.enabled));
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
            this.roomName = this.enterRoomName;
            this.$socket.emit("join_room", {
                //all user 시작하는거임 //offer도 저기서 완성시키고 보냄

                // store의 로그인 닉네임, url parameter의 roomNumber 받아오기
                nickname: this.nickname,
                roomName: this.roomName,
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
    },
}
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
</style>