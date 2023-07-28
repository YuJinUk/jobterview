import { createRouter, createWebHistory } from 'vue-router'

import commonRouter from './commonRouter';
import aiRouter from './aiRouter';

const routes = [
    ...commonRouter,
    ...aiRouter,
  ];

// 라우터 생성
export default createRouter({
    history: createWebHistory(),
    routes
});