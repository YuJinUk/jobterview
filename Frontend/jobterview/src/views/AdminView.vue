<template>
  <div>
    <admin-nav />
    <router-view />
  </div>
</template>

<script>
import AdminNav from "@/views/components/admin/AdminNav.vue";

export default {
  name: "AdminView",
  components: {
    AdminNav,
  },
  setup() {},
};
</script>

<style scoped>
</style>