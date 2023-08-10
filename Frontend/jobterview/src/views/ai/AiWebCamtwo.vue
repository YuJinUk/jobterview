<template>
    <div>
    <nav class="navbar">
      <ul class="nav-list">
        <li class="nav-item">카메라를 보고 또박또박 말씀해주세요.</li>
      </ul>
    </nav>

    <div id="headTextBox">
        Q2. {{ selectedQuestions[1].content }}
    </div>

    <div class="main-bg">
      <video ref="video" class="camera-style" autoplay></video>
      <audio ref="audio" controls class="hidden-audio"></audio>
      <div class="clock-wrapper">
        <div class="clock">
            <div class="hour-hand" :style="{ transform: `rotate(${rotateDegree}deg)` }"></div>
        </div>
        <div class="marker1">15</div>
        <div class="marker2">30</div>
        <div class="marker3">45</div>
        <div class="marker4">0</div>
        <div class="timer">
            {{ formatTimer(timer) }}
        </div>
        <div v-if="changebutton">
            <div class="start-button-position">
                <div v-if="checkactive">
                    <button class="start-button" @click="startCapture">시작!</button>
                </div>
                <div v-else>
                    <button class="start-button" disabled>진행중</button>
                </div>
            </div>
        </div>
        <div v-else>
            <router-link :to="{ name: 'AiWebCamthree' }">
                <div class="next-button-position">
                    <button class="start-button">다음 문항</button>
                </div>
            </router-link>
        </div>
      </div>
    </div>
</div>
</template>
  
<script>
import axios from 'axios';
import { mapState, mapActions, mapGetters } from 'vuex';

export default {
name: 'AiWebCam',
data() {
    return {
    resultData: null,
    stream: null,
    videoTrack: null,
    audioTrack: null,
    videoData: { chunks: [] },
    audioData: [],
    captureTimeout : null, // 시간 계산
    timer: this.second,
    checktimer: false,
    showTimer: false,
    captureDuration: this.second, // 타이머 시간
    captureInterval: null,
    rotateDegree: 0,
    changebutton: true,
    checkactive: true,
    };
},
computed: {
    ...mapState('loginStore', ['loginNickname']),
    ...mapState('aiStore', ['resultDataList']),
    ...mapState('roomStore', ['selectGen']),
    ...mapGetters('roomStore', ['getSelectedQuestions']), // 'roomStore' 모듈에서 'getSelectedQuestions' getter 가져오기
    
    selectedQuestions() {
      return this.getSelectedQuestions; // 계산된 속성을 통해 getter 사용
    }
},
methods: {
    ...mapActions('aiStore',['storeResultData', 'storeVideo2']),
    formatTimer(seconds) {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;
    },
    update_Timer(intervalStep) {
    this.timer -= intervalStep; // 타이머의 값을 intervalStep만큼 감소
    if (this.timer === 0) {
        clearInterval(this.captureInterval)
        this.timer = this.captureDuration;
        this.changebutton = false;
    }
    this.rotateDegree += 6; // 초침 1초당 6도 회전
    if (this.rotateDegree >= 360) {
        this.rotateDegree = 0; // 한 바퀴를 돌고 다시 0도로 초기화
    }
    },
    stopTimer: function(update_Timer){
    clearInterval(update_Timer);
    this.timer = this.captureDuration
    },
    startAudio() {
    console.log("startaudio")
    this.checkactive = false;
    navigator.mediaDevices.getUserMedia({ audio: true })
    .then(stream => {
        this.stream = stream;
        this.audioTrack = stream.getAudioTracks()[0];

        this.audioData = [];

        this.audioData.recorder = new MediaRecorder(stream);

        this.audioData.recorder.ondataavailable = event => {
            this.audioData.push(event.data);
        };

        this.audioData.recorder.start();

        this.$refs.audio.srcObject = stream;
        })
        .catch(error => {
        console.error('Error accessing camera and microphone:', error);
        });

    },
    startCapture() {
    console.log("startcapture")
    this.startAudio()
    clearInterval(this.captureInterval);
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(stream => {
        this.stream = stream;
        this.videoTrack = stream.getVideoTracks()[0];

        this.videoData.chunks = [];

        this.videoData.recorder = new MediaRecorder(stream, { mimeType: 'video/webm' });

        this.videoData.recorder.ondataavailable = event => {
            this.videoData.chunks.push(event.data);
        };

        this.videoData.recorder.start();

        this.$refs.video.srcObject = stream;

        this.showTimer = true;
        this.timer = this.captureDuration;
        const intervalStep = 1;

        this.rotateDegree = 0;
        this.captureInterval = setInterval(() => {
            this.update_Timer(intervalStep);
        }, 1000);
        this.captureTimeout = setTimeout(() => {
            this.showTimer = false;
            this.stopCapture();
        }, this.captureDuration * 1000);
        })
        .catch(error => {
        console.error('Error accessing camera and microphone:', error);
        });
        
    },
    stopCapture() {
    console.log('end')
    if (this.videoData.recorder && this.audioData.recorder) {
        this.videoData.recorder.stop();
        this.audioData.recorder.stop();
        this.videoTrack.stop();
        this.audioTrack.stop();
        this.stream.getTracks().forEach(track => track.stop());
        
        this.$nextTick(() => {
        if (this.$refs.video && this.$refs.video.paused) {
            this.$refs.video.srcObject = null;
        }
        if (this.$refs.audio && this.$refs.audio.paused) {
            this.$refs.audio.srcObject = null;
        }
        });
        
        this.stream = null;
        this.videoTrack = null;
        this.audioTrack = null;
    }
    if (this.captureTimeout){
        clearTimeout(this.captureTimeout); // 타이머 중지
        this.captureTimeout = null; // 타이머 시간 초기화
        clearInterval(this.captureInterval); // interval을 멈춤
    }
    this.captureTimeout = setTimeout(() => {
        this.uploadCapture();
        }, 100)
    },
    async uploadCapture() {
    console.log('upload')
    if (this.videoData.chunks.length > 0 && this.audioData.length > 0) {
        const videoBlob = new Blob(this.videoData.chunks, { type: 'video/webm' });
        const audioBlob = new Blob(this.audioData, { type: "audio/ogg codecs=opus" });
        const textBlob = new Blob([this.loginNickname, this.selectGen], { type: "text/plain" });

        const formData = new FormData();
        formData.append('video', videoBlob, 'video.webm');
        formData.append('audio', audioBlob, 'audio/wav');
        formData.append('nickname', textBlob, 'nickname.txt');
        this.resultData = null;

        console.log(this.videoData.chunks)
        try {
            // 비디오와 오디오 데이터를 Flask로 전송
            const response = await axios.post(this.localhost + 'api/upload/2', formData);

            // Flask에서 받은 응답 데이터를 resultData에 저장
            this.resultData = response.data;
            // this.$store.dispatch('aiStore/storeResultData', response.data);
            this.$store.dispatch('aiStore/storeVideo2', response.data);
            console.log(this.resultData);
            console.log('Upload successful');
        } catch (error) {
            console.error('Upload error:', error);
        }

        // 업로드 이후 버퍼 비우기
        this.videoData.chunks = [];
        this.audioData = [];
    }
    },
},
};
</script>
  
  
<style scoped src="@/css/aiWebCam.css">
</style>