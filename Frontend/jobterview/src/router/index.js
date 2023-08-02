import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import EmailAuth from '@/views/EmailAuth';
import authRouter from './authRouter';
import joinRouter from './joinRouter';

const routes = [
    ...commonRouter,
    ...messageRouter,
    {
        path: '/emailauth/:email',
        name: 'EmailAuth',
        component: EmailAuth,
    },
    ...authRouter,
    ...joinRouter,
];

export default createRouter({
    history: createWebHistory(),
    routes,
});