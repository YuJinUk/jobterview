<template>
    <div>
      <h2>4번차트</h2>
      <GChart type="PieChart" :options="options" :data="normalizedDatafour" />
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
          width: 1100,
          height: 400
          // 기타 원하는 옵션 설정 추가
        };
      }
    },
  };
  </script>