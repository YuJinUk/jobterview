import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import EmailAuth from '@/views/EmailAuth';

const routes = [
    ...commonRouter,
    ...messageRouter,
    {
        path: '/emailauth/:email',
        name: 'EmailAuth',
        component: EmailAuth,
    },
];

export default createRouter({
    history: createWebHistory(),
    routes,
});