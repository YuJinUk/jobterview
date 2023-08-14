<template>
  <div>
      <GChart
        type="LineChart"
        class="chart"
        :options="options"
        :data="collectionData"
      />
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { GChart } from "vue-google-charts";

export default {
  name: "LineChartthree",
  components: {
    GChart
  },
  computed: {
    ...mapGetters('aiStore', ['getvideo3']),
    collectionData() {
      const dataList = this.getvideo3;
      const labels = ['angry', 'disgust', 'fear', 'happy', 'sad', 'surprise', 'neutral'];
      const chartData = [['Time', ...labels]];

      for (let i = 0; i < dataList.result_emotion.new_data_about_time.time.length; i++) {
        const time = dataList.result_emotion.new_data_about_time.time[i];
        const values = labels.map(label => dataList.result_emotion.new_data_about_time[label][i]);
        chartData.push([time, ...values]);
      }

      return chartData;
    },
    options() {
      return {
        width: 500,
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
        chartArea: { // 차트 영역 조절
          left: 30, // 왼쪽 여백
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
.chart {
    position: relative;
    z-index: 100;
    background-color: white;
}

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