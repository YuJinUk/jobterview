<template>
  <auth-password v-if="!authenticated" />
  <password-change />
</template>

<script>
import AuthPassword from '@/views/components/member/AuthPassword.vue';
import PasswordChange from '@/views/components/member/PasswordChange.vue';
import { useStore } from 'vuex';

export default {
    components: {
        AuthPassword,
        PasswordChange,

    },
    setup() {
        const store = useStore();

        let authenticated = store.getters["memberStore/getAuthenticated"];

        return {
            authenticated,
        }
    }
}
</script>

<style>

</style>