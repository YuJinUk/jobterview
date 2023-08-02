import { getTotalMemberCntAPI } from '@/api/homeApi'

const homeStore = {
    namespaced: true,
    state: {
        totalMemberCnt: 0,
    },
    getters: {
        getTotalMemberCnt(state) {
            return state.totalMemberCnt;
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

export default homeStore