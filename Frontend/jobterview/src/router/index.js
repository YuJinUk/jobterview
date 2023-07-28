import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';

const routes = [
    ...commonRouter,
    ...messageRouter,
];

export default createRouter({
    history: createWebHistory(),
    routes,
});