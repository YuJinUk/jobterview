import { createRouter, createWebHistory } from 'vue-router';
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import authRouter from './authRouter'; 
import joinRouter from './joinRouter';
import store from '@/store/store.js'; 

const routes = [
    ...commonRouter,
    ...messageRouter,
    ...authRouter,
    ...joinRouter,
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    console.log(store.getters.isLogin);
    if (
      !store.getters.isLogin &&
      to.path != '/auth/login' && to.path != '/' && to.path != '/member/Join'
    ) {
      next('/auth/login');
      console.log(store.getters.isLogin);
    } else {
        console.log(to.path);   
      next(); // 다음 라우트로 진행
      //console.log(store.state.isLogin);
    }
  });

  export default router;

