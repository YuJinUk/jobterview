import MemberListView from '@/views/member/MemberListView'
import FindPassword from '@/views/components/auth/FindPassword'
import UpdatePassword from '@/views/components/auth/UpdatePassword'
const memberRouter = [
  {
    path: '/member/list',
    name: 'MemberList',
    component: MemberListView,
  },
  {
    path:'/member/findPassword',
    name:'FindPassword',
    component: FindPassword,
  },
  {
    path:'/member/updatePassword',
    name:'UpdatePassword',
    component:UpdatePassword,
  }
];

export default memberRouter;