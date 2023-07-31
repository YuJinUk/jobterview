
const messageRouter = [
    {
        path: '/message',
        name: 'MessageView',
        component: () => import('@/views/MessageView.vue'),
        children: [
            {
                path: 'list',
                component: () => import('@/views/components/message/MessageList'),
            },
            {
                path: 'read',
                component: () => import('@/views/components/message/MessageList'),
            },
            {
                path: 'send',
                component: () => import('@/views/components/message/MessageList'),
            },
        ],
    },
];

export default messageRouter;