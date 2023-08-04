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
import {apiUrlPrefix} from "../config/config.js";

// 모든 라우트 경로 앞에 apiUrlPrefix를 추가합니다.
function RouteChange(routes) {
  return routes.map(route => {
    return {
      ...route,
      path: `${apiUrlPrefix}${route.path}`
    };
  });
}

const routes = [
    ...RouteChange(commonRouter),
    ...RouteChange(aiRouter),
    ...RouteChange(roomRouter),
    ...RouteChange(memberRouter),
    ...RouteChange(messageRouter),
    ...RouteChange(authRouter),
    ...RouteChange(joinRouter),
    ...RouteChange(adminRouter),
  ];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    console.log(store.getters.isLogin);
    if (  
      !store.getters.isLogin &&
      to.path != `${apiUrlPrefix}/auth/login` && to.path != `${apiUrlPrefix}/` && to.path != `${apiUrlPrefix}/member/Join` && !to.path.startsWith(`${apiUrlPrefix}/emailauth/`)
    ) {
      next(`${apiUrlPrefix}/auth/login`);
      console.log(store.getters.isLogin);
    } else {
        console.log(to.path);   
      next(); // 다음 라우트로 진행
      //console.log(store.state.isLogin);
    }
  });

  export default router;

