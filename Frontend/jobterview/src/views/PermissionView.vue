<template>
  <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>

    <div id="videoBox">
      <div id="userVideo"></div>
      <div id="crossLine">
        <div class="row">
          <div class="box box1"></div>
          <div class="box box2"></div>
        </div>
        <div class="row">
          <div class="box box3"></div>
          <div class="box box4"></div>
        </div>
      </div>
    </div>

    <div id="buttonBox">
      <button>뒤로</button>
      <button>다음</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      headText: "마이크와 카메라를 준비중입니다.",
    };
  },
};
</script>

<style scoped src="@/css/permissionView.css"/>