import MemberListView from '@/views/member/MemberListView'
import ChangePasswordView from '@/views/member/ChangePasswordView'

const memberRouter = [
  {
    path: '/member/list',
    name: 'MemberList',
    component: MemberListView,
  },
  {
    path: '/member/password',
    name: 'ChangePassword',
    component: ChangePasswordView,
  }
];

export default memberRouter;