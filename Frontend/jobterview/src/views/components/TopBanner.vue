<template>
  <div id="container">
    <div id="content">
        {{ bannerText }}
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            bannerText: '일단 베너 공지사항',
        }
    }
};
</script>

<style scoped>
#container {
    width: 100%;
    height: 40px; 

    display: flex;
    justify-content: center;

    background-color: #0F4471;
}

#content {
    font-size: 16px;
    color: #fff;

    text-align: center;
    display: flex;
    align-items: center;
}
</style>