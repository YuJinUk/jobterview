<template>
    <div id="total">
    <div id="main">
        <br>
        <div id="smile">
        <svg xmlns="http://www.w3.org/2000/svg" width="500" height="300" fill="currentColor" class="bi bi-emoji-smile" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
          </svg></div>
        <div id="text">
        <p>인증이 완료되었습니다.</p>
        </div>
        <div id="text">
            <p>환영합니다!</p>
        </div>
        <div id="home-button">
        <router-link :to="{name:'Home'}">
            <button>홈으로</button>
          </router-link>
        </div>
    </div>
</div>
</template>
<script>
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
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

<style scoped>
#main {
    width: 100%;
    height: 100%;
    background-color: #0f4471;
    color: white;
    justify-content: center;
  }
#text{
    display:flex;
    font-size: 40px;
    font-weight: bold;
    padding: 0 0 10px 0;
    align-items: center;
    justify-content: center;
  }
#home-button{
    display:flex;
    align-items:center;
    justify-content: center;
}
#smile{
    display:flex;
    justify-content:center;
    align-items: center;
    margin-bottom: 10px;
}
#total {
    display: block;
    align-items: center;
    justify-content: center;
  }  
button {
    background-color: #083358;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 14px;
    font-weight: 500;
    width: 120px;
    height: 40px;
    margin: 10px;
    letter-spacing: 0;
    -webkit-transition: all 0.28s ease-in-out;
    transition: all 0.28s ease-in-out;
  }
  
button:hover,
button:focus,
button:active {
    letter-spacing: 2px;
  }
  
button:after,
button:before {
    border: 1px solid rgba(255, 255, 255, 0);
    bottom: 0;
    content: " ";
    display: block;
    margin: 0 auto;
    position: relative;
    -webkit-transition: all 0.28s ease-in-out;
    transition: all 0.28s ease-in-out;
    width: 0;
  }
  
button:hover:after {
    border-color: #fff;
    -webkit-transition: width 350ms ease-in-out;
    transition: width 350ms ease-in-out;
    width: 90%;
  }
  
button:hover:before {
    bottom: auto;
    top: 0;
  }
</style>