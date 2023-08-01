<template>
  <router-link to="/member/join">회원가입</router-link>
  <NavBar/>
  <router-view />
</template>

<script>
import NavBar from './views/components/NavBar.vue'



export default {
  name: 'App',

  components: {
    NavBar,
  }
}
</script>
