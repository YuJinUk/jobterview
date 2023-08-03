const adminRouter = [
    {
        path: '/admin',
        name: 'AdminView',
        component: () => import('@/views/AdminView.vue'),
    }
];

export default adminRouter;