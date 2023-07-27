<template>
  <div id="app">
    <router-view name="header"/>
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',

  components: {
  },

  data: () => ({
    //
  }),
}
</script>
