<template>
  <div id="container">
    <div id="navBox">
      <!-- 검색창 -->
      <input type="text">

      <!-- 메이트 페이지로 가는 Nav -->
      <button>MATE</button>
    </div>

    <!-- 맴버 목록 -->
    <div id="memberListBox">
      <div class="empty"></div>
      <ul id="memberList">
        <li v-for="member in members" :key="member.nickname" class="memberBox">
          <div class="member">
            <p class="nickname"> {{ member.nickname }}</p>
            <font-awesome-icon icon="fa-solid fa-envelope" class="icon messageIcon" @click="toSend(member.nickname)" />
            <font-awesome-icon icon="fa-solid fa-user-group" class="icon mateIcon" :class="{ 'isMate' :member.isMate }" @click="switchMate(member)"/>
          </div>
        </li>
      </ul>
      <div class="empty"></div>
    </div>

    <!-- 페이지네이션 -->
    <nav aria-label="Page navigation example" id="pageBox">
      <ul class="pagination">
        <li class="page-item" @click="topPreviousPage">
          <a class="page-link" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li
          class="page-item"
          v-for="page in visiblePageNumbers"
          :key="page"
          @click="changePage(page)"
        >
          <a class="page-link" v-if="page >= curStartingPage">
            {{ page }}
          </a>
        </li>
        <li class="page-item" @click="toNextPage">
          <a class="page-link" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { useStore } from "vuex";
import router from '@/router';
import { getMemberListAPI, getLoginMemberAPI } from "@/api/memberApi";
import { getMateListApi } from "@/api/mateApi";

export default {
  setup() {
    const store = useStore();

    let loginMember = ref({} ); //로그인 유저
    let members = ref([]); //유저 목록
    let mateNicknames = ref([]); //메이트 목록
    let totalPages = ref(1); //전체 페이지
    let curPage = ref(1); //현재 페이지
    let curStartingPage = ref(1); //첫번째 표시될 페이지
    let visiblePageNumbers = ref(); //표시될 페이지 수

    //페이지네이션///////////////////////////////////////////////////////////
    //다음 페이지로
    let toNextPage = () => {
      if (Math.floor(totalPages.value / 5) * 5 >= curStartingPage.value) {
        curStartingPage.value += 5;
        curPage.value = curStartingPage.value;
        setVisiblePageNumbers();
      }
    };

    //이전 페이지로
    let topPreviousPage = () => {
      if (curStartingPage.value > 5) {
        curStartingPage.value -= 5;
        curPage.value = curStartingPage.value;
        setVisiblePageNumbers();
      }
    };

    //표시될 페이지
    let setVisiblePageNumbers = () => {
      visiblePageNumbers.value = Math.min(
        totalPages.value,
        Math.ceil(curStartingPage.value + 4)
      );
    };

    //페이지 변경
    const changePage = (page = 1, size = 12, sort = "createdDate,desc") => {
      curPage.value = page;
      getMembers(page, size, sort);
    };

  //페이지네이션 끝/////////////////////////////////////////////////////////

    //유저 목록 조회
    const getMembers = async ( page = 1, size = 12, sort = "createdDate,desc" ) => {
      await getMemberListAPI(
        { page, size, sort },
        ({ data }) => {
          totalPages.value = data.totalPages;
          members.value = data.content; //일단 배열에 한번 넣고

          //유저 목록에서 본인 제거
          members.value = members.value.filter(member => member.nickname !== loginMember.value); //본인 빼고 저장

          //유저 목록에 메이트 확인 속성 추가
          members.value = members.value.map((member) => {
              for(const mateNicname of mateNicknames.value) {
                //메이트 목록에 해당 맴버가 존재하는 경우
                if(member.nickname === mateNicname) {
                  return { ...member, isMate: true };
                }
              }
            return { ...member, isMate: false };
          });
        },
        (error) => {
          console.log(error);
        }
      );
    };

    //메이트 닉네임 목록 조회
    const getMateNicknames = async ({nickname}) => {
      console.log(nickname)
      await getMateListApi(
        {nickname},
        ({ data }) => {
          mateNicknames.value = data.content;
          mateNicknames.value = mateNicknames.value.map(mate => mate.toMember.nickname);
        },
        (error) => {
          console.log(error);
        }
      );
    };

    //해당 맴버에게 쪽지 보내기
    const toSend = (nickname) => {
      store.commit("messageStore/SET_REPLY_NICKNAME", nickname);
      router.push({name: "MessageSend"});
    }

    //해당 맴버와 메이트 관게 설정
    const switchMate = (member) => {
      // 메이트 목록에 존재하는지 확인
      const idx = mateNicknames.value.indexOf(member.nickname);

      //이미 메이트인 경우
      if (idx !== -1) {
        member.isMate = false;
        mateNicknames.value.splice(idx, 1);

        //서버에 메이트 취소하는 api 보내는 메서드

      } else { //메이트가 아닌 경우
        member.isMate = true;
        mateNicknames.value.push(member.nickname);

        //서버에 메이트 만드는 api 보내는 메서드
      }
    }

    onMounted(async () => {
      loginMember.value = getLoginMemberAPI(); //로그인한 맴버 가져오기
      await getMateNicknames(loginMember.value); //메이트 가져오기
      await getMembers(); //맴버 가져오기
      setVisiblePageNumbers() //페이지 갱신
    });

    return {
      loginMember,
      members,
      toSend,
      totalPages,
      curPage,
      changePage,
      getMateNicknames,
      switchMate,
      curStartingPage,
      toNextPage,
      topPreviousPage,
      visiblePageNumbers,
      setVisiblePageNumbers,
    };
  },
};
</script>

<style scoped>
li {
  list-style: none;
}

#container {
  width: 1080px;
  margin: 0 auto;
  
  display: block;
  justify-content: center;
  align-items: center;
}

#navBox {
  display: flex;
  justify-content: center;
  align-items: center;
  
  margin: 20px 0;
}

.pagination {
  width: 500px;
}

#memberListBox {
  display: flex;
  justify-content: center;
  align-items: center;

  margin: 10px 0;
}

#memberList {
  width: 1080px;
  height: 500px;

  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-around;
  align-content: space-around;
}

.memberBox {
  display: flex;
  justify-content: center;
  align-items: center;
  
  height: 15%;
}

.member {
  display: flex;
  justify-content: center;
  align-items: center;
}

.nickname {
  width: 130px;
  margin: 0 10px;

  font-size: 18px;
}

.icon {
  font-size: 18px;
  margin: 0 10px;
}

.mateIcon {
  color: green;
}

.isMate {
  color: red;
}

#pageBox {
  display: flex;
  justify-content: center;
  align-items: center;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>