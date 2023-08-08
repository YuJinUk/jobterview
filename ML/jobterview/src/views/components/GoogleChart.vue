<template>
  <!-- <div class="container">
    <div>
      <h2>1번차트</h2>
      <GChart type="PieChart" :options="options" :data="normalizedData" />
    </div>
    <div class="info-item" style="width: 200px; color: beige;">
      <div v-for="data in getResultDataList[0]" :key="data.id">
        main_emotion : {{ data.main_emotion[0] }} <br>
        main_emotion_percent : {{ data.main_emotion[1] }} <br>
        eye_tracking : {{ data.eye_movement }} <br>
      </div>
    </div>
  </div> -->
  <div class="main">
    <div class="container1">
      <div class="chartBox">
        <GChart type="PieChart" class="chart" :options="options" :data="normalizedData" />
      </div>
      <div class="eyeResult">
        <li>다른 곳을 얼마나 봤나요? {{ getResultDataList[0].result_emotion.eye_movement }}</li>
      </div>
    </div>
    
    <div class="container2">
        <ul class="emotionResult">
          <!-- v-for 때리기 -->
          <li>기쁨 {{ getResultDataList[0].result_emotion.happy }}</li>
          <li>슬픔 {{ getResultDataList[0].result_emotion.sad }}</li>
          <li>나쁨 {{ getResultDataList[0].result_emotion.disgust }}</li>
          <li>중립 {{ getResultDataList[0].result_emotion.neutral }}</li>
          <li>긴장 {{ getResultDataList[0].result_emotion.fear }} </li>
          <li></li>
        </ul>
    </div>
    
    
    <div class="container3">
      <div class="memberScript">

      </div>
      <div class="comment">

      </div>
      <div class="button">
        <button> 결과 종료 </button>
      </div>
    </div>

  </div>
</template>
  
<script>
import { mapGetters } from 'vuex';
import { GChart } from "vue-google-charts";

export default {
  name: "App",
  components: {
    GChart
  },
  computed: {
    ...mapGetters(['getResultDataList']),
    normalizedData() {
      const dataList = this.getResultDataList;
      if (!dataList.length) {
        return this.data; // 데이터가 없을 경우 기존 데이터 반환
      }
      const emotionData = dataList[0].result_emotion;
      if (!emotionData) {
        return this.data; // 감정 데이터가 없을 경우 기존 데이터 반환
      }
      const labels = ['angry', 'disgust', 'fear', 'happy', 'sad', 'surprise', 'neutral'];
      const normalizedValues = labels.map(label => {
        const value = emotionData[label];
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
            color: "white", // Set the legend text color to white
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

.main {
  display: flex;
  justify-content: space-evenly;
  align-items: space-evenly;

  width: 1080px;
  height: 500px;

  background-color: white;
}

.container1 {
  width: 35%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;

  background-color: aqua;
}

.container1 div {
  width: 90%;

  border-radius: 5px;
  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.05), 0 6px 6px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.1s linear;
}

.container1 .chartBox {
  height: 70%;

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: blue;
}

.container1 .chartBox .chart{
  width: 100%;
  background-color: None;
}

.container1 .eyeResult {
  height: 20%;

  background-color: red;
}

.container2 {
  width: 15%;
  height: 100%;

  margin: 0 auto;

  display: flex;
  justify-content: center;
  align-items: center;

  background-color: black;
}

.container2 .emotionResult {
  padding: 10%;
  margin: 0;

  display: flex;
  flex-direction: column;
  align-items: center;

  border-radius: 5px;
  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.05), 0 6px 6px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.1s linear;

  background-color: red;
} 

.container2 .emotionResult li {
  font-size: 16px;
  margin: 5px;
}

.container3 {
  width: 50%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;

  background-color: green;
}

.container3 div{
  width: 90%;
}

.memberScript, .comment {
  height: 38%;
  
  border-radius: 5px;
  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.05), 0 6px 6px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.1s linear;
}

.container3 .memberScript {
  background-color: pink;
}

.container3 .comment {
  background-color: violet;
}

.container3 .button {
  height: 10%;

  display: flex;
  align-items: center;
  
  background-color: red;
}


</style>