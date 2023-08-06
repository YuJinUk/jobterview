import RoomListView from '@/views/room/RoomListView'
import RoomPermission from '@/views/room/RoomPermission'
import RoomMeeting from '@/views/room/RoomMeeting'
const roomRouter = [
  {
    path: '/room/list',
    name: 'RoomList',
    component: RoomListView,
  },
  {
    path: '/room/permission/:roomId',
    name: 'RoomPermission',
    component: RoomPermission,
  },
  {
    path: '/room/meeting/:roomId',
    name: 'RoomMeeting',
    component: RoomMeeting,
  }
];

export default roomRouter;