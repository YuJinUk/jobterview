<template>
  <div id="container">
    <div id="navBox">
      <!-- 검색창 -->
      <input type="text">

      <!-- 메이트 Nav -->
      <button>MATE</button>
    </div>

    <!-- 맴버 목록 -->
    <div id="memberListBox">
      <div class="empty"></div>
      <ul id="memberList">
        <li v-for="member in members" :key="member.nickname" class="memberBox">
          <div class="member">
            <p class="nickname"> {{ member.nickname }}</p>
            <font-awesome-icon icon="fa-solid fa-envelope" class="icon messageIcon" route-to/>
            <font-awesome-icon icon="fa-solid fa-user-group" class="icon mateIcon" />
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
import { getMemberListAPI } from "@/api/memberApi";

export default {
  setup() {
    let members = ref([]);
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
  //페이지네이션///////////////////////////////////////////////////////////

    const getMembers = async ( page = 1, size = 12, sort = "createdDate,desc" ) => {
      await getMemberListAPI(
        { page, size, sort },
        ({ data }) => {
          totalPages.value = data.totalPages;
          members.value = data.content;
        },
        (error) => {
          console.log(error);
        }
      );
    };

    onMounted(async () => {
      await getMembers(); //맴버 가져오기
      setVisiblePageNumbers() //페이지 갱신
    });

    return {
      members,
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