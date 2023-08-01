<template>
  <div id="container">
    <div id="content">
      {{ bannerText }}
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();

    return {
      bannerText: store.state.bannerText,
    };
  },
};
</script>

<style scoped>
#container {
  width: 100%;
  height: 40px;

  display: flex;
  justify-content: center;

  background-color: #0f4471;
}

#content {
  font-size: 16px;
  color: #fff;

  text-align: center;
  display: flex;
  align-items: center;
}
</style>