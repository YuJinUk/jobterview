import { createRouter, createWebHistory } from 'vue-router'

import commonRouter from './commonRouter';
import aiRouter from './aiRouter';
import roomRouter from './roomRouter';

const routes = [
    ...commonRouter,
    ...aiRouter,
    ...roomRouter
  ];

// 라우터 생성
export default createRouter({
    history: createWebHistory(),
    routes
});