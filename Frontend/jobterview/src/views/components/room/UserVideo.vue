<template>
    <video ref="remote" autoplay playsinline width="300" height="180" style="border-radius: 10%;"></video>
    <p class="text-center">{{ info.nickname }}</p>
</template>
<script>
export default {
    name: "UserVideo",
    components: {
    }, created() {
        console.log(this.info.stream);
    }, mounted() {
        console.log(this.info);
        console.log(this.$refs);
        this.$refs.remote.srcObject = this.info.stream;
    },
    props: {
        info: Object,
    },
};
</script>

<style>
/* #app {
     font-family: Avenir, Helvetica, Arial, sans-serif; 
     -sebkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
text-align: center;
color: #2c3e50;
margin-top: 60px; 
} */
</style>