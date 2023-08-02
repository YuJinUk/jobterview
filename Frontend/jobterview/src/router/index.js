import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import authRouter from './authRouter';
import joinRouter from './joinRouter';
import adminRouter from  './adminRouter';

const routes = [
    ...commonRouter,
    ...messageRouter,
    ...authRouter,
    ...joinRouter,
    ...adminRouter,
];

export default createRouter({
    history: createWebHistory(),
    routes,
});