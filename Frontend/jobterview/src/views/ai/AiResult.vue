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
      <div style="margin-left: auto;">
        <router-link :to="{ name: 'Home' }">
            <button style="background-color: #0F4471; color: white; border-radius: 5px;">홈으로</button>
        </router-link>
      </div>
      </div>
      <!-- 안쪽 컨테이너 -->
      <div class="innerContainer">

        <!-- 1번 -->
        <div class="dataBox" v-if="pagenum === 1 && getvideo1">
          <GoogleChart />
        </div>

        <!-- 2번 -->
        <div class="dataBox" v-if="pagenum === 2 && getvideo2">
          <GoogleCharttwo />
        </div>

        <!-- 3번 -->
        <div class="dataBox" v-if="pagenum === 3 && getvideo3">
          <GoogleChartthree />
        </div>

        <!-- 4번 -->
        <div class="dataBox" v-if="pagenum === 4 && getvideo4">
          <GoogleChartfour />
        </div>

      </div>
      <!-- 안쪽 컨테이너 -->
      
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import GoogleChart from '@/views/components/chart/GoogleChart.vue';
import GoogleCharttwo from '@/views/components/chart/GoogleCharttwo.vue';
import GoogleChartthree from '@/views/components/chart/GoogleChartthree.vue';
import GoogleChartfour from '@/views/components/chart/GoogleChartfour.vue';

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
    ...mapGetters('aiStore',['getvideo1', 'getvideo2', 'getvideo3', 'getvideo4']),
  },
};
</script>

<style scoped>
.border-box {
  padding: 0px;
}

.page-numbers {
width: 1080px;

  display: flex;
  margin: 0 auto;
  padding: 10px;
}

.innerContainer {
  width: 1080px;
  height: 100vh;

  background-color: #fff;
  border-top: 1px solid #0F4471;
  /* border-radius: 5px; */

  /* border: 2px solid #0F4471;  */

  margin: 10px auto;

  display: flex;
  justify-content: center;
  /* align-items: center; */
  flex-direction: row;
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