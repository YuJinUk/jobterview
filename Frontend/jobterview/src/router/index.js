import { createRouter, createWebHistory } from 'vue-router'

import commonRouter from './commonRouter';
import aiRouter from './aiRouter';
import roomRouter from './roomRouter';
import memberRouter from './memberRouter';

const routes = [
    ...commonRouter,
    ...aiRouter,
    ...roomRouter,
    ...memberRouter
  ];

// 라우터 생성
export default createRouter({
    history: createWebHistory(),
    routes
});