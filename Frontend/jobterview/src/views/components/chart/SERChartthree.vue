<template>
    <div style="z-index: 0; background-color: white;">
        <div class="chart-container">
            <GChart type="PieChart" class="chart" :options="options" :data="eyeData" />
        </div>
    </div>
</template>
    
<script>
import { mapGetters } from 'vuex';
import { GChart } from "vue-google-charts";

export default {
    name: "SERChart",
    components: {
    GChart,
    },
    computed: {
    ...mapGetters('aiStore',['getvideo3']),
    eyeData() {
        const dataList = this.getvideo3;
        const normalizedValues = dataList.result_emotion.SER
        console.log(normalizedValues)
        return [
        ['where', 'Per'],
        ['angry', normalizedValues['angry']],
        ['disgust', normalizedValues['disgust']],
        ['fear', normalizedValues['fear']],
        ['neutral', normalizedValues['neutral']],
        ['sad', normalizedValues['sad']],
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
        left: 50, // 왼쪽 여백
        top: 50, // 위쪽 여백
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
    z-index: 0;
    background-color: None;
}

.chart {
    position: relative;
    z-index: 0;
    background-color: None;
}
</style>