import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AiView from '../views/AiView.vue';
import ChatRoomView from '../views/ChatRoomView.vue';

const routes = [
    {
        path: '/',
        component: HomeView,
    },
    {
        path: '/ai',
        component: AiView,
    },
    {
        path: '/chat',
        component: ChatRoomView,
    }
  ];
  
  export default createRouter({
    history: createWebHistory(),
    routes,
  });