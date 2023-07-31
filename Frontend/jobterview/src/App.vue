<template>
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
