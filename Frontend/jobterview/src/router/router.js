import { createWebHistory, createRouter } from 'vue-router';
import NavBar from "./views/components/NavBar.vue";
import MessageList from "./views/message/MessageList.vue";
// import MainPage from "./views/MainPage.vue";

const routes = [
    {
        path: '/',
        name: 'NavBar',
        components: {
            default: MessageList,
            header: NavBar,
        }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;