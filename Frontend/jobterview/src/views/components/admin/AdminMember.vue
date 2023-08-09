<template>
  <h1>사용자 관리</h1>

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

  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">email</th>
        <th scope="col">nickname</th>
        <th scope="col">role</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(member, index) in members" :key="index">
        <th scope="row">{{ index + 1 }}</th>
        <td>{{ member.email }}</td>
        <td>{{ member.nickname }}</td>
        <td>{{ member.role }}</td>
      </tr>
    </tbody>
  </table>

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
</template>

<script>
import { onMounted, ref } from "vue";
import { searchMemberApi } from "@/api/memberApi";

export default {
  setup() {
    let members = ref([]); //유저 목록

    let totalPages = ref(1); //전체 페이지
    let curPage = ref(1); //현재 페이지
    let curStartingPage = ref(1); //첫번째 표시될 페이지
    let visiblePageNumbers = ref(); //표시될 페이지 수

    let searchQuery = ref(""); //유저 검색어
    let savedKeyword = ref("");

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
      searchQuery.value = savedKeyword.value;
      searchMember(page, size, sort);
    };
    ////////////////////////////////////////////////////////////////////////////////

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
          members.value = data.content;

          setVisiblePageNumbers();
        },
        (error) => {
          console.log(error);
        }
      );
    }

    onMounted(async () => {
      await searchMember(); //맴버 가져오기
      setVisiblePageNumbers(); //페이지 갱신
    });

    return {
      members,
      totalPages,
      curPage,
      curStartingPage,
      visiblePageNumbers,

      toNextPage,
      topPreviousPage,
      changePage,
      setVisiblePageNumbers,

      searchMember,
      searchQuery, 
      savedKeyword,
    };
  },
};
</script>

<style scoped>
.search {
  position: relative;
  width: 100%;
  min-width: 170px;
  max-width: 230px;
}

input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}

img {
  position: absolute;
  width: 17px;
  top: 11px;
  right: 12px;
  margin: 0;
}
</style>