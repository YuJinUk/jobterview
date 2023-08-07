<template>
    <nav class="navbar">
      <ul class="nav-list">
        <li class="nav-item">카메라를 보고 또박또박 말씀해주세요.</li>
      </ul>
    </nav>
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
  </template>
  
<script>
import axios from 'axios';
import { mapState, mapActions } from 'vuex';

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
    ...mapState(['resultDataList'])
},
methods: {
    ...mapActions(['storeResultData']),
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

        const formData = new FormData();
        formData.append('video', videoBlob, 'video.webm');
        formData.append('audio', audioBlob, 'audio/wav');

        this.resultData = null;

        console.log(this.videoData.chunks)
        try {
            // 비디오와 오디오 데이터를 Flask로 전송
            const response = await axios.post(this.localhost + 'api/upload/2', formData);

            // Flask에서 받은 응답 데이터를 resultData에 저장
            this.resultData = response.data;
            this.$store.dispatch('storeResultData', response.data);
            console.log(this.resultData);
            console.log('Upload successful');
        } catch (error) {
            console.error('Upload error:', error);
        }
        // axios.post('http://localhost:5001/api/upload', formData)
        // .then((res) => {
        //     this.resultData = res.data
        //     console.log(this.videoData.chunks)
        //     console.log('Upload successful')
        // })
        // .catch(error => {
        //     console.error('Upload error:', error);
        // });

        // 업로드 이후 버퍼 비우기
        this.videoData.chunks = [];
        this.audioData = [];
    }
    },
},
};
</script>
  
  
<style>
@font-face {
font-family: 'digit';
src:url('@/assets/Orbitron-Medium.ttf');
}

.main-bg{
min-width: 1500px;
height: 100%;
background: white;
}

.hidden-audio{
display: none;
}

.camera-style{
position: absolute;
height: 450px;
left: 173px;
right: 587px;
top: 230px;
width: 900px;
height: 450px;

background: #000000;
}

.timer{
position: absolute;
display: block;
/* background-color: aqua; */
width: 160px;
height: 50px;
top: 105% !important;
left: 25% !important;
font-family: "digit";
color: black;
font-size: 36px;
}

.start-button{
display: inline-block;
text-align: center;
line-height: 1; /* 텍스트를 수직으로 가운데로 정렬 */
white-space: nowrap; /* 텍스트가 한 줄로 유지되도록 설정 */

/* 버튼 배경색과 텍스트 색상 */
background-color: white;
color: black;

/* 버튼 패딩과 여백 설정 */
padding: 12px 18px;
margin: 5px;

/* 버튼 둥글게 만들기 */
border-radius: 5px; /* 값을 조절하여 둥근 정도를 변경할 수 있습니다. */

/* 커서를 마우스로 올렸을 때의 스타일 설정 */
cursor: pointer;
}

.start-button:hover{
background-color: black;
color: white;
}

.start-button-position{
position: absolute;
left: 80%;
top: 140%;
}

.next-button-position{
position: absolute;
left: 34%;
top: 140%;
}

.clock {
position: absolute;
top: 0;
left: 0;
right: 0;
bottom: 0;
margin: auto; /* 시계를 가운데 정렬 */
width: 130px;
height: 130px;
border: 10px solid #0f4471;
border-radius: 50%;
transform-origin: center center;
}

.hour-hand {
position: absolute;
left: 48.62%;
right: 43.04%;
top: 5.37%;
bottom: 91.54%; 
width: 2px;
height: 50px;
transform-origin: bottom center;
background-color: black;
}

.marker1 {
/* 마커 스타일 설정 */
position: absolute;
font-size: 25px;
color: black;
top: 45%;
left: 77%;
font-family: "digit";
}
.marker2 {
/* 마커 스타일 설정 */
position: absolute;
font-size: 25px;
color: black;
top: 75%;
left: 44%;
font-family: "digit";
}
.marker3 {
/* 마커 스타일 설정 */
position: absolute;
font-size: 25px;
color: black;
top: 45%;
left: 10%;
font-family: "digit";
}
.marker4 {
/* 마커 스타일 설정 */
position: absolute;
font-size: 25px;
color: black;
top: 12%;
left: 46%;
font-family: "digit";
}

.clock-wrapper {
position: relative;
width: 290px;
height: 290px;
margin-left: 71%;
margin-top: 7%;
}


.navbar {
background-color: #0f4471;
color: #fff;
display: flex;
align-items: center;
justify-content: center;
padding: 3px;
}

.nav-list {
list-style: none;
display: flex;
align-items: center;
}

.nav-item {
padding: 5px;
font-family: "digit";
}

.nav-item:hover {
background-color: #0f4471;
}
</style>