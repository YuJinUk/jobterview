<template>
  <div class="main">
    <div class="container1">
      <div class="charBox shadow" style="width: 510px; margin-left:170px; text-align:center;">
        해당 질문에 대한 표정 감정 분석데이터 입니다.
      </div>
      <div class="chartBox shadow">
        <GChart type="PieChart" class="chart" :options="options" :data="normalizedData" />
      </div>
      <div class="eyeResult shadow">
        <h5>면접에 얼마나 집중하셨나요?</h5>
        <div v-if="getvideo3.result_emotion.eye_movement > 20">
          <li>다른 곳을 많이 주시하시는 군요! 면접 시 면접관과 눈을 맞추는 것이 도움이 될 것입니다.</li>
        </div>
        <div v-else>
          <li>면접관에게 집중을 잘 하고 계시는군요. 앞으로도 이렇게만 해주세요!</li>
        </div>
      </div>
    </div>
    
    <div class="container2">
        <ul class="emotionResult shadow">
          <!-- v-for 때리기 -->
          <li>슬픔 {{ getvideo3.result_emotion.sad }}</li>
          <li>나쁨 {{ getvideo3.result_emotion.disgust }}</li>
          <li>침착 {{ getvideo3.result_emotion.neutral }}</li>
          <li>긴장 {{ getvideo3.result_emotion.fear }} </li>
          <li>화남 {{ getvideo3.result_emotion.angry }} </li>
          <li>행복 {{ getvideo3.result_emotion.happy }} </li>
          <li>놀람 {{ getvideo3.result_emotion.surprise }} </li>
          <li></li>
        </ul>
    </div>
    
    
    <div class="container3">
      <div class="memberScript shadow">
        <div class="scroll-box">
        <h6> Q1. {{ selectedQuestions[2].content }}</h6>
          <p>
            A. {{ getvideo3.result_emotion.STT_message }}
          </p>
        </div>
      </div>
      <div class="comment shadow" style="height: 70%; margin-top: 30px;">
        <h3>해당 질문에 대한 음성 감정 분석데이터 입니다.</h3>
        - {{ getvideo3.result_emotion.SER }}
      </div>
      <div class="button">
        <router-link :to="{ name: 'Home' }">
          <button>홈으로</button>
        </router-link>
      </div>
    </div>

  </div>
</template>
  
<script>
import { mapGetters } from 'vuex';
import { GChart } from "vue-google-charts";

export default {
  name: "GoogleChartthree",
  components: {
    GChart
  },
  computed: {
    ...mapGetters('aiStore',['getvideo3']),
    ...mapGetters('roomStore', ['getSelectedQuestions']), // 'roomStore' 모듈에서 'getSelectedQuestions' getter 가져오기
    selectedQuestions() {
      return this.getSelectedQuestions; // 계산된 속성을 통해 getter 사용
    },
    normalizedData() {
      const dataList = this.getvideo3;
      const labels = ['angry', 'disgust', 'fear', 'happy', 'sad', 'surprise', 'neutral'];
      const normalizedValues = labels.map(label => {
        const value = dataList.result_emotion[label];
        console.log('value',value)
        if (typeof value === 'string') {
          return parseFloat(value.replace('%', '')); // 문자열에서 '%' 제거 후 숫자로 변환
        } else {
          return 0; // 기본값으로 0 반환
        }
      });
      return [
        ['Daily Routine', 'Hours per Day'],
        ['angry', normalizedValues[0]],
        ['disgust', normalizedValues[1]],
        ['fear', normalizedValues[2]],
        ['happy', normalizedValues[3]],
        ['sad', normalizedValues[4]],
        ['surprise', normalizedValues[5]],
        ['neutral', normalizedValues[6]]
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
          textStyle: {
            color: "black", // Set the legend text color to white
          },
        },
      };
    }
  },
};
</script>

<style scoped>
li {
  list-style: none;
}

.shadow {
  border-radius: 5px;
  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.05), 0 6px 6px rgba(0, 0, 0, 0.1);
}

.main {
  display: flex;
  justify-content: space-evenly;
  align-items: space-evenly;

  width: 1080px;
  height: 500px;

  /* background-color: white; */
}

.container1 {
  width: 35%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
}

.container1 div {
  width: 90%;
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
  height: 20%;
}

.container2 {
  width: 15%;
  height: 100%;

  margin: 0 auto;

  display: flex;
  justify-content: center;
  align-items: center;
}

.container2 .emotionResult {
  padding: 10%;
  margin: 0;

  display: flex;
  flex-direction: column;
  align-items: center;
} 

.container2 .emotionResult li {
  font-size: 16px;
  margin: 5px;
}

.container3 {
  width: 50%;
  height: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
}

.container3 div{
  width: 99%;
}

.memberScript, .comment {
  height: 38%;
  width: 80%;
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

</style>