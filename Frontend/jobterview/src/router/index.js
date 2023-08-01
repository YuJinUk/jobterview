import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import authRouter from './authRouter';
import joinRouter from './joinRouter';

const routes = [
    ...commonRouter,
    ...messageRouter,
    ...authRouter,
    ...joinRouter,
];

export default createRouter({
    history: createWebHistory(),
    routes,
});