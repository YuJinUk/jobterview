<template>
    <div>
    <ul>
      <li v-for="(data, idx) in getResultDataList" :key="data.id">
        {{ idx+1 }}번째 결과 : <br>
        main_emotion : {{ data.result_emotion.main_emotion }} <br>
        angry : {{ data.result_emotion.angry }}
        disgust : {{ data.result_emotion.disgust }}
        fear : {{ data.result_emotion.fear }}
        happy : {{ data.result_emotion.happy }}
        sad : {{ data.result_emotion.sad }}
        surprise : {{ data.result_emotion.surprise }}
        neutral : {{ data.result_emotion.neutral }}
        STT : {{ data.result_emotion.STT_message }}
        SER : {{ data.result_emotion.SER }}
        <div class="container mt-5 text-center" v-if="idx >= 3">
          <GoogleChart />
        </div>
        <div class="container mt-5 text-center" v-if="idx >= 3">
          <GoogleCharttwo />
        </div>
        <div class="container mt-5 text-center" v-if="idx >= 3">
          <GoogleChartthree />
        </div>
        <div class="container mt-5 text-center" v-if="idx >= 3">
          <GoogleChartfour /> 
        </div>
      </li>
    </ul>
  </div>
  </template>
  
<script>
import { mapGetters } from 'vuex';
import GoogleChart from './components/GoogleChart.vue'
import GoogleCharttwo from './components/GoogleCharttwo.vue'
import GoogleChartthree from './components/GoogleChartthree.vue'
import GoogleChartfour from './components/GoogleChartfour.vue'

export default {
  name: 'AiResult',
  components: {
    GoogleChart,
    GoogleCharttwo,
    GoogleChartthree,
    GoogleChartfour,
  },
  computed: {
  ...mapGetters(['getResultDataList']),
},
}
</script>

<style>
</style>