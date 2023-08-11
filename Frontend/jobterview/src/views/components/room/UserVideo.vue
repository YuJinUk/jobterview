<template>
  <video
    v-if="shouldUseLargeVideo"
    ref="remote"
    autoplay
    playsinline
    width="480"
    height="360"
    style="border-radius: 10%"
  ></video>
  <video
    v-if="!shouldUseLargeVideo"
    ref="remote"
    autoplay
    playsinline
    width="245"
    height="180"
    style="border-radius: 15%"
    @click="clickEvent"
  ></video>
  <p class="text-center">{{ info.nickname }}</p>
</template>
<script>
export default {
  name: "UserVideo",
  computed: {
    shouldUseLargeVideo() {
      return this.index === 0;
    },
  },
  methods: {
    clickEvent() {
      this.$emit("changeMainVideo", this.index);
    },
  },
  components: {},
  created() {
    // console.log(this.info.stream);
  },
  mounted() {
    console.log(this.index + "번컴포넌트에서 mount이벤트 발생");
    this.$refs.remote.srcObject = this.info.stream;
    // console.log(this.$refs.remote);
    // console.log(this.$refs);
  },
  props: {
    info: Object,
    index: Number,
  },
};
</script>

<style scoped>
/* #app {
     font-family: Avenir, Helvetica, Arial, sans-serif; 
     -sebkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
text-align: center;
color: #2c3e50;
margin-top: 60px; 
} */
</style>
