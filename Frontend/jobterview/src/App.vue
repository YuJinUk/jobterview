<template>
  <top-banner/>
  <NavBar/>
  <router-view />
</template>

<script>
import NavBar from './views/components/NavBar.vue'
import TopBanner from './views/components/TopBanner.vue'


export default {
  name: 'App',

  components: {
    NavBar,
    TopBanner,
  }
}
</script>
