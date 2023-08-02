<template>
    <p>이메일 인증 {{ email }}</p>
    <button @click="emailAuth()">인증</button>
</template>
<script>
import { useRoute } from 'vue-router';
import apitest  from '@/api/apitest';
export default {
    name: 'EmailAuth',
    setup() {
        const route = useRoute();
        // onMounted(() => {
        //     apitest.put(`/member/emailauth/${email}`, (response) => {
        //         console.log(response);
        //     }, 
        //     (error) => {
        //         console.log(error);
        //     })
        // })
        function emailAuth() {
            apitest.put(`/member/emailauth/${route.params.email}`, (response) => {
                console.log(response);
            }, 
            (error) => {
                console.log(error);
            })
        }
        return {
            emailAuth,
        };
    }

}
</script>