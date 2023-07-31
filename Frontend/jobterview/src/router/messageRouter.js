
const messageRouter = [
    {
        path: '/message',
        name: 'MessageView',
        component: () => import('@/views/MessageView.vue'),
        redirect: "/message/list",
        children: [
            {
                path: 'list',
                name: 'MessageList',
                component: () => import('@/views/components/message/MessageList'),
            },
            {
                path: 'read/:category',
                name: 'MessageRead',
                component: () => import('@/views/components/message/MessageRead'),
            },
            // {
            //     path: 'send',
            //     name: 'MessageSend',
            //     component: () => import('@/views/components/message/MessageSend'),
            // },
        ],
    },
];

export default messageRouter;