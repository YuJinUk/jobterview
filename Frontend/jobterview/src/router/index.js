import { createRouter, createWebHistory } from 'vue-router'

import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import aiRouter from './aiRouter';
import roomRouter from './roomRouter';
import memberRouter from './memberRouter';
import authRouter from './authRouter';
import joinRouter from './joinRouter';
import adminRouter from  './adminRouter';

const routes = [
    ...commonRouter,
    ...aiRouter,
    ...roomRouter,
    ...memberRouter,
    ...messageRouter,
    ...authRouter,
    ...joinRouter,
    ...adminRouter,
    {
        path: '/emailauth/:email',
        name: 'EmailAuth',
        component: EmailAuth,
    },
  ];

// 라우터 생성
export default createRouter({
    history: createWebHistory(),
    routes
});