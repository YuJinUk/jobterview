<template>
  <div class="main">
    <div class="container1">
      <div style="width: 600px; margin-left:0px; text-align:center;">
      </div>
      <div class="chartBox">
        <div style="display: flex; flex-direction:column; justify-content: center; text-align:center;">
          <h4>어떤 표정을 지으셨나요?</h4>
          <GChart type="PieChart" class="chart-down" :options="options" :data="normalizedData" />
        </div>
        <div style="display: flex; flex-direction:column; justify-content: center; text-align:center;">
          <h4>어느 곳을 주시하셨나요?</h4>
          <EyeChartfour />
        </div>
      </div>
      <div class="zoomable-div" @click="eyeMouseOver">
        <div class="shadow" style="background-color: #0F4471;">
          <p style="text-align: center; color:white;">감정의 흐름이 궁금하신가요?</p>
        </div>
      </div>
    </div>
    <div>
      <div class="modal-overlay modal-shadow" v-if="!eye">
        <div>
          <p>면접자님의 감정의 흐름이에요!</p>
        </div>
        <br>
        <div><LineChartfour /></div>
        <div style="background-color:#0F4471; color: white; border-radius:5px; width:60px; text-align:center" @click="eye = true">닫기</div>
      </div>
    </div>
    <div>
      <div class="modal-overlay modal-shadow" v-if="!mo">
        <div class="shadow">면접에서 다양한 감정은 표정에 드러난다고 해요. 면접관님들께서 보고 느낄 수 있는 감정이니 연습해봐요!</div>
        <br>
        <div>시간의 흐름에 따른 감정변화에요.</div>
        <div><LineChartfour /></div>
        <div style="background-color:#0F4471; color: white; border-radius:5px; width:60px; text-align:center" @click="mo = true">닫기</div>
      </div>
    </div>
    
    <div class="container3">
      <h4>면접자님의 말씀에 대해서 알아볼까요?</h4>
      <div class="memberScript">
        <h5>면접자님의 답변이에요.</h5>
        <div class="scroll-box">
        <h6> Q1. {{ selectedQuestions[0].content }}</h6>
          <p>
            A. {{ getvideo4.result_emotion.STT_message }}
          </p>
        </div>
        <div class="zoomable-div" @click="TextOver">
          <div class="shadow" style="background-color:#0F4471; margin-top:10px;">
            <p style="text-align: center; color:white;">면접자님이 가장 많이 말씀하신 단어를 알아볼까요?</p>
          </div>
        </div>
      </div>
      <div style="display: flex; justify-content:center; border-top:1px solid black; margin-top:20px; margin-bottom: 0; padding-top:20px; padding-bottom:0;">
        <h4>어떤 목소리로 답변했나요?</h4>
      </div>
      <div class="chart-down" style="height: 70%; margin-top: 30px; margin-left:150px; display:flex; flex-direction:row">
        <div class="container2">
          <h5>
            표현에 능숙하지 않은 사람도 표정은 숨겨도 목소리에 깃든 감정은 숨길 수 없다는 말이 있잖아요.
            <br><br>
            성공적인 면접을 위해 
            <br><br>
            면접자님의 목소리에 깃든 숨겨진 감정을 알아봤어요.
          </h5>
        </div>
        <br>
        <SERChartfour />
      </div>
    </div>
    <div>
      <div class="modal-overlay shadow" v-if="!tt" style="display:flex; flex-direction:column; justify-content: center; text-align:center;">
        <div style="text-align: center;">{{ getvideo4.result_emotion.WordCheck.maxnum }}번씩 말씀하신 단어들이에요.</div>
        <br>
          <ul v-for="word in getvideo4.result_emotion.WordCheck.maxcnt" :key="word" style="padding: 0;">
            <li>{{ word }}</li>
          </ul>
        <div style="background-color:#0F4471; color: white; border-radius:5px; width:60px;" @click="tt = true">닫기</div>
      </div>
    </div>
  </div>
</template>
  
<script>
import { mapGetters } from 'vuex';
import { GChart } from "vue-google-charts";
import SERChartfour from "./SERChartfour.vue";
import LineChartfour from "./LineChartfour.vue";
import EyeChartfour from "./EyeChartfour.vue"

export default {
  name: "GoogleChartfour",
  components: {
    GChart,
    LineChartfour,
    SERChartfour,
    EyeChartfour,
  },
  data(){
    return {
      mo: true,
      eye: true,
      showModal: true,
      tt: true,
    }
  },
  methods : {
    closeModal() {
        this.showModal = false;
    },
    doMouseOver() {
      this.mo = false
    },
    doMouseLeave(event) {
      if (event.target.classList.contains("modal")) {
        this.mo = true;
      }
    },
    eyeMouseOver() {
      this.eye = false
    },
    eyeMouseLeave() {
      this.eye = true
    },
    TextOver() {
      this.tt = false
    },
    TextLeave() {
      this.tt = true
    },
  },
  computed: {
    ...mapGetters('aiStore',['getvideo4']),
    ...mapGetters('roomStore', ['getSelectedQuestions']), // 'roomStore' 모듈에서 'getSelectedQuestions' getter 가져오기
    selectedQuestions() {
      return this.getSelectedQuestions; // 계산된 속성을 통해 getter 사용
    },
    normalizedData() {
      const dataList = this.getvideo4;
      const labels = ['angry', 'disgust', 'fear', 'happy', 'sad', 'surprise', 'neutral'];
      const normalizedValues = labels.map(label => {
        const value = dataList.result_emotion[label];
        if (typeof value === 'string') {
          return parseFloat(value.replace('%', '')); // 문자열에서 '%' 제거 후 숫자로 변환
        } else {
          return 0; // 기본값으로 0 반환
        }
      });
      return [
        ['Emotion', 'Percent'],
        ['흥분', normalizedValues[0]],
        ['불쾌', normalizedValues[1]],
        ['긴장', normalizedValues[2]],
        ['행복', normalizedValues[3]],
        ['슬픔', normalizedValues[4]],
        ['놀람', normalizedValues[5]],
        ['차분', normalizedValues[6]]
      ];
    },
    options() {
      return {
        width: 400,
        height: 300,
        backgroundColor: "transparent",
        titleTextStyle: {
          color: "white", // Set the title text color to white
        },
        legend: {
          position: "right",
          alignment: "center",
          textStyle: {
            color: "black", // Set the legend text color to white
            fontSize: 15,
          },
        },
        chartArea: { // 차트 영역 조절
          left: 100, // 왼쪽 여백
          top: 30, // 위쪽 여백
          width: '75%', // 너비 조절
          height: '70%', // 높이 조절
            },
          margin: 0, // 주위 여백 조절
      };
    }
  },
};
</script>

<style scoped>
.chart-down {
    position: relative;
    z-index: 0;
    background-color: None;
}
li {
  list-style: none;
}
.zoomable-new {
  transition: transform 0.3s ease;
}
.zoomable-new:hover {
  transform: scale(1.1);
}
.zoomable-div {
  transition: transform 0.3s ease; /* 변화를 부드럽게 만들기 위한 트랜지션 설정 */
}

.zoomable-div:hover {
  transform: scale(1.05); /* 마우스 오버 시 요소 확대 */
}
.shadow {
  border-radius: 5px;
  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.05), 0 6px 6px rgba(0, 0, 0, 0.1);
}
.modal-shadow {
  height: 100%;
  border-radius: 5px;
  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.05), 0 6px 6px rgba(0, 0, 0, 0.1);
}

.main {
  display: flex;
  justify-content: space-evenly;
  align-items: space-evenly;
  flex-direction: column;
  width: 1080px;
  height: 100vh;

  /* background-color: white; */
}

.container1 {
  width: 100%;
  height: 100%;
  margin-top: 410px;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  z-index: 10;
  border-bottom: 1px solid gray;
}

.container1 div {
  width: 100%;
}

.container1 .chartBox {
  height: 70%;

  display: flex;
  justify-content: center;
  align-items: center;
}

.container1 .chartBox .chart{
  width: 100%;
  background-color: None;
}

.container1 .eyeResult {
  height: 100%;
  width: 100%;
}
.container1 .eye-chart-result {
  display: relative;
}

.container2 {
  width: 15%;
  height: 100%;

  margin: 0 auto;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

.container2 .emotionResult {
  padding: 0;
  margin: 0;

  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
} 

.container2 .emotion-chart-result {
  display: relative;
  z-index: 99;
}

.container2 .emotionResult li {
  font-size: 16px;
  margin: 5px;
}

.container3 {
  width: 100%;
  height: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  z-index: 0;
  margin-top:25px;
}

.container3 div{
  width: 99%;
  z-index: 0;
}

.memberScript, .comment {
  height: 38%;
}

.container3 .button {
  height: 10%;

  display: flex;
  align-items: center;
}

button {
  position: absolute;
  box-shadow: none;
  border-radius: 8px;
  width: 80px;
  height: 30px;
  margin-left: 75%;
}
.scroll-box {
  max-height: 100%; /* 최대 높이 설정 */
  max-width: 140%;
  overflow-y: scroll; /* 스크롤을 항상 표시 */
  padding: 10px; /* 내용과 상하단 여백 조정 */
}

.modal-overlay {
  position: fixed;
  top: 100px;
  left: 500px;
  width: 600px;
  height: 450px;
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
  flex-direction: column;
}

</style>