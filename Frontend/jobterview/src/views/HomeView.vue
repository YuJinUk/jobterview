<template>
  <main>
    <div id="title">
      <div id="nameTitle">
        <p>JOBTERVIEW</p>
      </div>

      <div id="mainTitle">
        <p>잡터뷰에서 취직 기회 잡자</p>
      </div>

      <div id="subTitle">
        <p>면접 메이트와 함께 면접 준비</p>
        <p>AI 기반의 면접 자가 진단</p>
      </div>

      <div id="useState">
        <p>현재</p>
        <p id="numOfUser">{{ totalMemberCnt }}</p>
        <p>명의 메이트가 이용중입니다!</p>
      </div>

      <div id="buttonBox">
        <router-link to="/ai/question">
          <button>AI 면접 준비</button>
        </router-link>
        <router-link to="/room/list">
          <button>메이트 찾기</button>
        </router-link>
      </div>
    </div>
  </main>
</template>

<script>
import { computed, onMounted} from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    //onMounted
    onMounted(() => {
      store.dispatch("homeStore/setTotalMemberCnt");

  
    });


    return {
      //computed
      totalMemberCnt: computed(
        () => store.getters["homeStore/getTotalMemberCnt"]
      ),
    };
  },

  created() {
    const nickname = this.$route.query.nickname;
    if (nickname) {
      
      this.$store.commit('loginStore/SET_Login', nickname);
        }
    }, 

};
</script>

<style scoped src="@/css/homeView.css" />