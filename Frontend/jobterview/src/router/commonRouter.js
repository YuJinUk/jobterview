import HomeView from '@/views/HomeView'
import PermissionView from '@/views/PermissionView'

const commonRouter = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/permission',
    name: 'Permission',
    component: PermissionView,
  },
];

export default commonRouter;