const memberStore = {
    namespaced: true,
    state: {
        //비밀번호 변경을 위한 비밀번호 인증
        authenticated: false,
    },
    getters: {
        getAuthenticated(state) {
            return state.authenticated;
        }
    },
    mutations: {
        SET_TOTAL_MEMBER_CNT(state, count) {
            state.totalMemberCnt = count;
        }
    },
    actions: {
        async setTotalMemberCnt({commit}) {
            await getTotalMemberCntAPI( 
                ({data}) => {
                    commit('SET_TOTAL_MEMBER_CNT', data);},
                (error) => console.log(error)
            )
        },
    }
}

export default memberStore