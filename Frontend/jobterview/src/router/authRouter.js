
const loginRouter = [
    {
        path: '/auth',
        name: 'AuthView',
        component: () => import('@/views/AuthView.vue'),
        //redirect: "/message/list",
        children: [
            {
                path: 'login',
                name: 'LoginMember',
                component: () => import('@/views/components/auth/LoginMember'),
            },
            // {
            //     path: 'read/:category',
            //     name: 'MessageRead',
            //     component: () => import('@/views/components/message/MessageRead'),
            // },
            // {
            //     path: 'send',
            //     name: 'MessageSend',
            //     component: () => import('@/views/components/message/MessageSend'),
            // },
        ],
    },
    {
        path: '/emailauth/:email',
        name: 'EmailAuth',
        component: () => import('@/views/EmailAuth'),
    },
];

export default loginRouter;