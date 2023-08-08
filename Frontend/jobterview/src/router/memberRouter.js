import MemberListView from '@/views/member/MemberListView'
import FindPassword from '@/views/components/auth/FindPassword'
import UpdatePassword from '@/views/components/auth/UpdatePassword'
import ResetPassword from '@/views/components/auth/ResetPassword'
import Withdraw from '@/views/member/Withdraw'
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
  },
  {
    path:'/member/resetPassword/:email',
    name:'ResetPassword',
    component:ResetPassword,
  },
  {
    path:'/member/withdraw',
    name:'WithdrawMember',
    component:Withdraw,
  }
];

export default memberRouter;