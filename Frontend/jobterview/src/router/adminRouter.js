const adminRouter = [
  {
    path: "/admin",
    name: "AdminView",
    component: () => import("@/views/AdminView.vue"),
    children: [
      {
        path: "member",
        name: "AdminMember",
        component: () => import("@/views/components/admin/AdminMember.vue"),
      },
      {
        path: "message",
        name: "AdminMessage",
        component: () => import("@/views/components/admin/AdminMessage.vue"),
      },
      {
        path: "question",
        name: "AdminQuestion",
        component: () => import("@/views/components/admin/AdminQuestion.vue"),
      },
      {
        path: "room",
        name: "AdminRoom",
        component: () => import("@/views/components/admin/AdminRoom.vue"),
      },
    ],
  },
];

export default adminRouter;
