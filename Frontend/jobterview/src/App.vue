<template>
  <NavBar/>
  <router-view />
  <router-link to="/message">메시지</router-link>
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
