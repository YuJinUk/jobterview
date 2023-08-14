<template>
    <div>
      <div style="z-index: 100; background-color: white; width: 500px;">
          <GChart type="PieChart" class="chart" :options="options" :data="eyeData" />
      </div>
    </div>
</template>
    
<script>
import { mapGetters } from 'vuex';
import { GChart } from "vue-google-charts";

export default {
    name: "EyeChart",
    components: {
    GChart,
    },
    computed: {
    ...mapGetters('aiStore',['getvideo4']),
    eyeData() {
        const dataList = this.getvideo4;
        const normalizedValues = dataList.result_emotion.all_eye
        console.log(normalizedValues)
        return [
        ['where', 'Per'],
        ['left', normalizedValues[0]],
        ['center', normalizedValues[1]],
        ['right', normalizedValues[2]]
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
.chart-container {
    position: relative;
    z-index: 100;
    background-color: None;
}

.chart {
    position: relative;
    z-index: 100;
    background-color: None;
  }
  </style>