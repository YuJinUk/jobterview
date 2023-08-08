<template>
  <div>
    <div class="border-box">

      <!-- 번호 -->
      <div class="page-numbers">
        <button
          class="page-button"
          v-for="page in 4"
          :key="page"
          @click="goToPage(page)"
        >{{ page }}</button>
      </div>
      <!-- 번호 -->

      <!-- 안쪽 컨테이너 -->
      <div class="innerContainer">

        <!-- 1번 -->
        <div class="dataBox" v-if="pagenum === 1">
          <GoogleChart />
        </div>

        <!-- 2번 -->
        <div class="dataBox" v-if="pagenum === 2">
          <GoogleCharttwo />
        </div>

        <!-- 3번 -->
        <div class="dataBox" v-if="pagenum === 3">
          <GoogleChartthree />
        </div>

        <!-- 4번 -->
        <div class="dataBox" v-if="pagenum === 4">
          <GoogleChartfour />
        </div>

      </div>
      <!-- 안쪽 컨테이너 -->
      
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import GoogleChart from './components/GoogleChart.vue';
import GoogleCharttwo from './components/GoogleCharttwo.vue';
import GoogleChartthree from './components/GoogleChartthree.vue';
import GoogleChartfour from './components/GoogleChartfour.vue';

export default {
  name: 'AiResult',
  components: {
    GoogleChart,
    GoogleCharttwo,
    GoogleChartthree,
    GoogleChartfour,
  },
  data() {
    return {
      pagenum: 1,
    };
  },
  methods: {
    changePage(offset) {
      const newPage = this.pagenum + offset;
      if (newPage >= 1 && newPage <= 4) {
        this.pagenum = newPage;
      }
    },
    goToPage(page) {
      this.pagenum = page;
    },
  },
  computed: {
    ...mapGetters(['getResultDataList']),
  },
};
</script>

<style scoped>
.border-box {
  padding: 10px;
}

.page-numbers {
width: 1080px;

  display: flex;
  margin: 0 auto;
  padding: 10px;
}

.innerContainer {
  width: 1080px;
  height: 550px;

  background-color: rgb(232, 232, 232);

  border: 2px solid #0F4471; 

  margin: 10px auto;

  display: flex;
  justify-content: center;
  align-items: center;
}

.page-button {
  background-color: #0F4471;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  margin-right: 10px;
  cursor: pointer;
  width: 50px;
}

.page-button:hover {
  background-color: #316fa5;
  transform: scale( 0.95 );
}

</style>