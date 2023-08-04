import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AiView from '../views/AiView.vue';
import ChatRoomView from '../views/ChatRoomView.vue';
import AiWebCam from '../views/AiWebCam.vue';
import AiWebCamtwo from '../views/AiWebCamtwo.vue';
import AiWebCamthree from '../views/AiWebCamthree.vue';
import AiWebCamfour from '../views/AiWebCamfour.vue';
import AiResult from '../views/AiResult.vue';

const routes = [
    {
        path: '/',
        name: "HomeView",
        component: HomeView,
    },
    {
        path: '/ai',
        name: "AiView",
        component: AiView,
    },
    {
        path: '/chat',
        name: "ChatRoomView",
        component: ChatRoomView,
    },
    {
        path: '/aiwebcam',
        name: "AiWebCam",
        component: AiWebCam,
    },
    {
        path: '/aiwebcamtwo',
        name: "AiWebCamtwo",
        component: AiWebCamtwo,
    },
    {
        path: '/aiwebcamthree',
        name: "AiWebCamthree",
        component: AiWebCamthree,
    },
    {
        path: '/aiwebcamfour',
        name: "AiWebCamfour",
        component: AiWebCamfour,
    },
    {
        path: '/airesult',
        name: "AiResult",
        component: AiResult,
    },
  ];
  
  export default createRouter({
    history: createWebHistory(),
    routes,
  });