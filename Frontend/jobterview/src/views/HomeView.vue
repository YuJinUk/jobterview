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
        <router-link to="chat">
          <button>메이트 찾기</button>
        </router-link>
      </div>
    </div>
  </main>
</template>

<script>
import {computed, onMounted} from 'vue';
import {useStore} from 'vuex';

export default {
  setup() {
    const store = useStore();
    //onMounted
    onMounted(() => {
      store.dispatch('homeStore/setTotalMemberCnt');
    })

    return {
      //computed
      totalMemberCnt: computed(() => store.getters['homeStore/getTotalMemberCnt']),
    }
  }
};
</script>

<style scoped>
main {
  width: 100%;
  height: 600px;
  background-color: #0f4471;

  color: white;

  display: flex;
  justify-content: center;
}

p {
  margin: 0;
}

button {
  background-color: #083358;
  color: #fff;
  border: none;
  border-radius: 5px;

  font-size: 14px;
  font-weight: 500;

  width: 120px;
  height: 40px;

  margin: 10px;

  letter-spacing: 0;
  -webkit-transition: all 0.28s ease-in-out;
  transition: all 0.28s ease-in-out;
}

button:hover,
button:focus,
button:active {
  letter-spacing: 2px;
}

button:after,
button:before {
  border: 1px solid rgba(255, 255, 255, 0);
  bottom: 0;
  content: " ";
  display: block;
  margin: 0 auto;
  position: relative;
  -webkit-transition: all 0.28s ease-in-out;
  transition: all 0.28s ease-in-out;
  width: 0;
}

button:hover:after {
  border-color: #fff;
  -webkit-transition: width 350ms ease-in-out;
  transition: width 350ms ease-in-out;
  width: 90%;
  /* margin: 0px 3px; */
}

button:hover:before {
  bottom: auto;
  top: 0;
}

div {
  display: flex;
  justify-content: center;
}

#title {
  display: block;
  align-items: center;
  justify-content: center;
}

#nameTitle {
  font-size: 32px;

  padding: 120px 0 5px 0;
}

#mainTitle {
  font-size: 64px;
  font-weight: bold;

  padding: 0 0 10px 0;
}

#subTitle {
  font-size: 24px;
}

#subTitle p {
  padding: 5px 0;
}

#useState {
  color: red;
  font-size: 16px;

  padding: 20px 0;

  display: flex;
  justify-content: center;
}

#useState #numOfUser {
  font-size: 24px;
  position: relative;
  bottom: 8px;

  padding: 0 3px;
}
</style>