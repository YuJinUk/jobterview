import MemberListView from '@/views/member/MemberListView'
import FindPassword from '@/views/components/auth/FindPassword'
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
  }
];

export default memberRouter;