<template>
  <div id="container">
    <div id="navBox">
      <div></div>
      <!-- 검색창 -->
      <div class="search">
        <input
          type="text"
          placeholder="메이트 닉네임 검색"
          v-model="searchQuery"
          @keyup.enter="searchMate()"
        />
        <img
          src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
          @click="searchMate()"
        />
      </div>

      <!-- 메이트 페이지로 가는 Nav -->
      <router-link :to="{ name: 'MemberList' }">
        <button class="mateButton">유저 목록</button>
      </router-link>
    </div>

    <div v-if="mates.length == 0" class="emptyMate">
      <p>등록된 메이트가 없습니다!</p>
    </div>
    <div v-else>
      <!-- 메이트 목록 -->
      <div class="listBox">
        <div class="empty"></div>

        <ul class="list">
          <li
            v-for="mate in mates"
            :key="mate.toMemberNickname"
            class="mateBox"
          >
            <div class="mate">
              <p class="nickname">{{ mate.toMemberNickname }}</p>
              <font-awesome-icon
                icon="fa-solid fa-envelope"
                class="icon messageIcon"
                @click="toSend(mate.toMemberNickname)"
              />
              <font-awesome-icon
                icon="fa-solid fa-user-group"
                class="icon mateIcon"
                :class="{ isMate: mate.isMate }"
                @click="switchMate(mate)"
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
  </div>
</template>
  
  <script>
import { onMounted, ref } from "vue";
import { useStore } from "vuex";
import router from "@/router";
import {
  makeMateApi,
  breakMateApi,
  getMateListWithPagingApi,
  searchMateListWithPagingApi,
} from "@/api/mateApi";

export default {
  setup() {
    const store = useStore();

    let searchQuery = ref(""); //메이트 검색어
    let savedKeyword = ref("");

    let loginMemberNickname = ref({}); //로그인 유저
    let mates = ref([]); //메이트 목록

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
    const changePage = (page = 1) => {
      curPage.value = page;
      searchQuery.value = savedKeyword.value;
      searchMate(page);
    };
    ////////////////////////////////////////////////////////////////////////////////

    //해당 맴버에게 쪽지 보내기//////////////////////////////////////
    const toSend = (nickname) => {
      store.commit("messageStore/SET_REPLY_NICKNAME", nickname);
      router.push({ name: "MessageSend" });
    };

    //해당 맴버와 메이트 관계 설정///////////////////////////////////
    async function switchMate(mate) {
      const fromNickname = loginMemberNickname.value;
      const toNickname = mate.toMemberNickname;

      //이미 메이트인 경우
      if (mate.isMate === true) {
        mate.isMate = false;

        //서버에 메이트 취소하는 api 보내는 메서드
        await breakMateApi({ fromNickname, toNickname });
        return;
      }
      //메이트가 아닌 경우
      mate.isMate = true;

      //서버에 메이트 만드는 api 보내는 메서드
      await makeMateApi({ fromNickname, toNickname });
    }

    //전체 메이트 조회///////////////////////////////////////////////////////
    async function getMates(
      nickname,
      page = 1,
      size = 12,
      sort = "createdDate,desc"
    ) {
      await getMateListWithPagingApi(
        { page, size, sort, nickname },
        ({ data }) => {
          totalPages.value = data.totalPages;
          mates.value = data.content; //일단 배열에 한번 넣고

          //목록에 메이트 확인 속성 추가
          mates.value = mates.value.map((mate) => {
            return { ...mate, isMate: true };
          });
          setVisiblePageNumbers();
        },
        (error) => {
          console.log(error);
        }
      );
    }

    //메이트 검색어로 조회/////////////////////////////////////////////////////
    async function searchMate(page = 1) {
      savedKeyword.value = searchQuery.value.trim();
      searchQuery.value = "";

      //검색어가 존재하지 않은 경우
      if (savedKeyword.value === "") {
        await getMates(loginMemberNickname.value, page);
        setVisiblePageNumbers();

        //검색어가 존재하는 경우
      } else {
        await searchMateListWithPagingApi(
          {
            page,
            size: 12,
            sort: "createdDate,desc",
            fromNickname: loginMemberNickname.value,
            keyword: savedKeyword.value,
          },
          ({ data }) => {
            totalPages.value = data.totalPages;
            mates.value = data.content; //일단 배열에 한번 넣고

            //목록에 메이트 확인 속성 추가
            mates.value = mates.value.map((mate) => {
              return { ...mate, isMate: true };
            });
            setVisiblePageNumbers();
          },
          (error) => {
            console.log(error);
          }
        );
        setVisiblePageNumbers();
      }
    }

    ///////////////////////////////////////////////////////
    onMounted(async () => {
      loginMemberNickname.value =
        store.getters["loginStore/getLoginMemberNickname"]; //로그인한 맴버 가져오기
      getMates(loginMemberNickname.value);
      setVisiblePageNumbers(); //페이지 갱신
    });

    return {
      //검색
      searchQuery,
      savedKeyword,
      searchMate,

      //맴버
      loginMemberNickname,
      mates,
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