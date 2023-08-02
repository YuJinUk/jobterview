<template>
  <top-banner/>
  <router-link to="/member/join">회원가입</router-link>
  <NavBar />
  <router-view />
</template>

<script>
import NavBar from "./views/components/NavBar.vue";
import TopBanner from './views/components/TopBanner.vue'

export default {
  name: "App",

  components: {
    NavBar,
    TopBanner,
  }
}
</script>
