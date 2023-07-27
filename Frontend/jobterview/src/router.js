import { createWebHistory, createRouter } from 'vue-router';
import NavBar from "./views/components/NavBar.vue";
// import MainPage from "./views/MainPage.vue";

const routes = [
    {
        path: '/',
        name: 'NavBar',
        component: NavBar
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;