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
        <div class="dataBox" v-if="pagenum === 1 && getvideo1 == null">
          <div class="loading-container" style="margin-top:300px;">
            <div class="loading"></div>
            <div id="loading-text">loading</div>
          </div>
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
#link {color: #E45635;display:block;font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;text-align:center; text-decoration: none;}
#link:hover {color: #CCCCCC}

#link, #link:hover {-webkit-transition: color 0.5s ease-out;-moz-transition: color 0.5s ease-out;-ms-transition: color 0.5s ease-out;-o-transition: color 0.5s ease-out;transition: color 0.5s ease-out;}

/** BEGIN CSS **/
        body {background: #333333;}
        @keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-moz-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-webkit-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-o-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-moz-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-webkit-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-o-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }

        @-moz-keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }

        @-webkit-keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }

        @-o-keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }
        .loading-container,
        .loading {
            height: 100px;
            position: relative;
            width: 100px;
            border-radius: 100%;
        }


        .loading-container { margin: 40px auto }

        .loading {
            border: 2px solid transparent;
            border-color: transparent #123 transparent #123;
            -moz-animation: rotate-loading 1.5s linear 0s infinite normal;
            -moz-transform-origin: 50% 50%;
            -o-animation: rotate-loading 1.5s linear 0s infinite normal;
            -o-transform-origin: 50% 50%;
            -webkit-animation: rotate-loading 1.5s linear 0s infinite normal;
            -webkit-transform-origin: 50% 50%;
            animation: rotate-loading 1.5s linear 0s infinite normal;
            transform-origin: 50% 50%;
        }

        .loading-container:hover .loading {
            border-color: transparent #E45635 transparent #E45635;
        }
        .loading-container:hover .loading,
        .loading-container .loading {
            -webkit-transition: all 0.5s ease-in-out;
            -moz-transition: all 0.5s ease-in-out;
            -ms-transition: all 0.5s ease-in-out;
            -o-transition: all 0.5s ease-in-out;
            transition: all 0.5s ease-in-out;
        }

        #loading-text {
            -moz-animation: loading-text-opacity 2s linear 0s infinite normal;
            -o-animation: loading-text-opacity 2s linear 0s infinite normal;
            -webkit-animation: loading-text-opacity 2s linear 0s infinite normal;
            animation: loading-text-opacity 2s linear 0s infinite normal;
            color: #123;
            font-family: "Helvetica Neue, "Helvetica", ""arial";
            font-size: 10px;
            font-weight: bold;
            margin-top: 45px;
            opacity: 0;
            position: absolute;
            text-align: center;
            text-transform: uppercase;
            top: 0;
            width: 100px;
        }

</style>
