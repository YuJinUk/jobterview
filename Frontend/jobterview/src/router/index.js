import { createRouter, createWebHistory } from 'vue-router'
import messageRouter from './messageRouter';
import commonRouter from './commonRouter';
import store from '@/store/store.js'; 
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
  ];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    console.log(store.getters.isLogin);
    if (  
      !store.getters.isLogin &&
      to.path != '/auth/login' && to.path != '/' && to.path != '/member/Join' && !to.path.startsWith('/emailauth/')
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

