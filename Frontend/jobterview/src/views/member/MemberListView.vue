<template>
  <div id="container">
    <div id="navBox">
      <div></div>
      <!-- 검색창 -->
      <div class="search">
        <input
          type="text"
          placeholder="유저 닉네임 검색"
          v-model="searchQuery"
          @keyup.enter="searchMember()"
        />
        <img
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
          @click="searchMember()"
        />
      </div>

      <!-- 메이트 페이지로 가는 Nav -->
      <router-link :to="{ name: 'MateList' }">
        <button class="mateButton">메이트 목록</button>
      </router-link>
    </div>

    <div v-if="members.length == 0" class="emptyMate">
      <p>등록된 유저가 없습니다!</p>
    </div>

    <!-- 맴버 목록 -->
    <div v-else class="listBox">
      <div class="empty"></div>
      <ul class="list">
        <li v-for="member in members" :key="member.nickname" class="memberBox">
          <div class="member">
            <p class="nickname">{{ member.nickname }}</p>
            <font-awesome-icon
              icon="fa-solid fa-envelope"
              class="icon messageIcon"
              @click="toSend(member.nickname)"
            />
            <font-awesome-icon
              icon="fa-solid fa-user-group"
              class="icon mateIcon"
              :class="{ isMate: member.isMate }"
              @click="switchMate(member)"
            />
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
import router from "@/router";
import { searchMemberApi } from "@/api/memberApi";
import { getMateListApi, makeMateApi, breakMateApi } from "@/api/mateApi";

export default {
  setup() {
    const store = useStore();

    let searchQuery = ref(""); //유저 검색어
    let savedKeyword = ref("");

    let loginMemberNickname = ref({}); //로그인 유저
    let members = ref([]); //유저 목록
    let mateNicknames = ref([]); //메이트 목록

    let totalPages = ref(1); //전체 페이지
    let curPage = ref(1); //현재 페이지
    let curStartingPage = ref(1); //첫번째 표시될 페이지
    let visiblePageNumbers = ref(); //표시될 페이지 수

    //페이지네이션///////////////////////////////////////////////////////////
    //다음 페이지로
    let toNextPage = () => {
      if (Math.floor(totalPages.value / 5) - 1 * 5 >= curStartingPage.value) {
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
      searchQuery.value = savedKeyword.value;
      searchMember(page, size, sort);
    };

    //메이트 닉네임 목록 조회///////////////////////////////////////////////////////
    const getMateNicknames = async (nickname) => {
      mateNicknames.value = [];

      await getMateListApi(
        { nickname },
        ({ data }) => {
          mateNicknames.value = data;
          mateNicknames.value = mateNicknames.value.map(
            (mate) => mate.toMember.nickname
          );
        },
        (error) => {
          console.log(error);
        }
      );
    };

    //해당 맴버에게 쪽지 보내기///////////////////////////////////////////////////////
    const toSend = (nickname) => {
      store.commit("messageStore/SET_REPLY_NICKNAME", nickname);
      router.push({ name: "MessageSend" });
    };

    //해당 맴버와 메이트 관계 설정///////////////////////////////////////////////////////
    async function switchMate(member) {
      const fromNickname = loginMemberNickname.value;
      const toNickname = member.nickname;

      //메이트 목록에 해당 맴버가 존재하는지 확인
      const idx = mateNicknames.value.indexOf(toNickname);

      //이미 메이트인 경우
      if (idx !== -1) {
        member.isMate = false;
        mateNicknames.value.splice(idx, 1);

        //서버에 메이트 취소하는 api 보내는 메서드
        await breakMateApi({ fromNickname, toNickname });
        return;
      }
      member.isMate = true;
      mateNicknames.value.push(toNickname);

      //서버에 메이트 만드는 api 보내는 메서드
      await makeMateApi({ fromNickname, toNickname });
    }

    //검색어로 맴버 조회///////////////////////////////////////////////////////
    async function searchMember(
      page = 1,
      size = 12,
      sort = "createdDate,desc"
    ) {
      savedKeyword.value = searchQuery.value.trim();
      searchQuery.value = "";

      const keyword = savedKeyword.value;

      await searchMemberApi(
        { page, size, sort, keyword },
        ({ data }) => {
          totalPages.value = data.totalPages;
          members.value = data.content; //일단 배열에 한번 넣고

          //유저 목록에서 본인 제거
          members.value = members.value.filter(
            (member) => member.nickname !== loginMemberNickname.value
          ); //본인 빼고 저장

          //유저 목록에 메이트 확인 속성 추가
          members.value = members.value.map((member) => {
            if (mateNicknames.value !== undefined) {
              for (const mateNicname of mateNicknames.value) {
                //메이트 목록에 해당 맴버가 존재하는 경우
                if (member.nickname === mateNicname) {
                  return { ...member, isMate: true };
                }
              }
            }
            return { ...member, isMate: false };
          });

          setVisiblePageNumbers();
        },
        (error) => {
          console.log(error);
        }
      );
    }

    ///////////////////////////////////////////////////////
    onMounted(async () => {
      loginMemberNickname.value =
        store.getters["loginStore/getLoginMemberNickname"]; //로그인한 맴버 가져오기
      await getMateNicknames(loginMemberNickname.value); //메이트 가져오기
      await searchMember(); //맴버 가져오기
      setVisiblePageNumbers(); //페이지 갱신
    });

    return {
      //맴버 검색
      searchQuery,
      searchMember,
      savedKeyword,

      //맴버
      loginMemberNickname,
      members,

      //메이트
      getMateNicknames,
      switchMate,

      //쪽지
      toSend,

      //페이징
      totalPages,
      curPage,
      changePage,
      curStartingPage,
      toNextPage,
      topPreviousPage,
      visiblePageNumbers,
      setVisiblePageNumbers,
    };
  },
};
</script>

<style scoped src="@/css/memberListView.css">
</style>