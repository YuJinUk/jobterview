import RoomListView from '@/views/components/room/RoomListView'
import RoomPermission from '@/views/components/room/RoomPermission'
import RoomMeeting from '@/views/components/room/RoomMeeting'
const roomRouter = [
  {
    path: '/room/list',
    name: 'RoomList',
    component: RoomListView,
  },
  {
    path: '/room/permission',
    name: 'RoomPermission',
    component: RoomPermission,
  },
  {
    path: '/room/meeting',
    name: 'RoomMeeting',
    component: RoomMeeting,
  }
];

export default roomRouter;