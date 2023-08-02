<template>
    <p>이메일 인증 {{ email }}</p>
</template>
<script>
import { onMounted } from 'vue';
import {    useRoute } from 'vue-router';
import { apiTest }  from '@/api/apiTest';
export default {
    name: 'EmailAuth',
    setup() {
        const route = useRoute();
        onMounted(() => {
            apiTest.put(`/member/emailauth?email=${route.params.email}`, (response) => {
                console.log(response);
            }, 
            (error) => {
                console.log(error);
            })
        })
        // async function emailAuth() {
        //     console.log(`/member/emailauth?email=${route.params.email}`);
        //     await apitest.put(`/member/emailauth?email=${route.params.email}`, (response) => {
        //         console.log(response);
        //     },
        //     (error) => {
        //         console.log(error);
        //     })
        // }
        return {
        };
    }

}
</script>