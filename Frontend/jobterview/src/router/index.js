import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import joinRouter from './joinRouter';

const routes = [
    ...commonRouter,
    ...messageRouter,
    ...joinRouter,
];

export default createRouter({
    history: createWebHistory(),
    routes,
});