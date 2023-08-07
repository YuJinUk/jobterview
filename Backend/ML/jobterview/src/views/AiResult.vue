<template>
  <div>
    <div class="border-box">
    <div class="page-numbers">
      <button
        class="page-button"
        v-for="page in 4"
        :key="page"
        @click="goToPage(page)"
      >{{ page }}</button>
    </div>
    <ul>
      <div
        class="container mt-5 text-center"
        style="height: 500px; border: 2px solid #ccc; margin: 0px;"
        v-if="pagenum === 1"
      >
        <GoogleChart />
      </div>
      <div
        class="container mt-5 text-center"
        style="height: 500px;"
        v-if="pagenum === 2"
      >
        <GoogleCharttwo />
      </div>
      <div
        class="container mt-5 text-center"
        style="height: 500px;"
        v-if="pagenum === 3"
      >
        <GoogleChartthree />
      </div>
      <div
        class="container mt-5 text-center"
        style="height: 500px;"
        v-if="pagenum === 4"
      >
        <GoogleChartfour />
      </div>
    </ul>
  </div>
    <!-- <div>
      <button @click="changePage(-1)">prev</button>
      <button @click="changePage(1)">next</button>
    </div> -->
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

<style>
.page-numbers {
  display: flex;
  margin-bottom: 10px;
  margin-top: 10px;
  margin-left: 10px;
  border-bottom: 2px solid #2980b9;
  padding: 10px;
}

.page-button {
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  margin-right: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
  width: 50px;
}

.page-button:hover {
  background-color: #2980b9;
}

.border-box {
  border-top: 2px solid #ccc; /* 테두리 스타일 설정 */
  padding: 10px; /* 내용과 테두리 사이의 여백 설정 */
  /* 다른 스타일 설정 */
  background-color: #0f4471;
}
</style>