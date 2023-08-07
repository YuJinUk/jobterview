<template>
  <div class="container">
    <div>
      <h2>4번차트</h2>
      <GChart type="PieChart" :options="options" :data="normalizedDatafour" />
    </div>
    <div style="width: 200px;">
      <div v-for="data in getResultDataList[3]" :key="data.id">
        main_emotion : {{ data.main_emotion[0] }} <br>
        main_emotion_percent : {{ data.main_emotion[1] }} <br>
        eye_tracking : {{ data.eye_movement }} <br>
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
    normalizedDatafour() {
      const dataList = this.getResultDataList;
      if (!dataList.length) {
        return this.data; // 데이터가 없을 경우 기존 데이터 반환
      }
      const emotionData = dataList[3].result_emotion;
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
        height: 300
      };
    }
  },
};
</script>

<style>
.container{
  display: flex;
  flex-direction: row;
}

</style>