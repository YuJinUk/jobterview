<template>
  <h1>채팅방 관리</h1>

  <div v-if="rooms.length === 0">
    등록된 채팅방이 없습니다.
  </div>
  
  <table v-else class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Id</th>
        <th scope="col">name</th>
        <th scope="col">Password</th>
        <th scope="col">NowMember</th>
        <th scope="col">MaxMember</th>
        <th scope="col">createdDate</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(room, index) in rooms" :key="index">
        <th scope="row">{{ index + 1 }}</th>
        <td>{{ room.roomId }}</td>
        <td>{{ room.roomName }}</td>
        <td>{{ room.roomPassword }}</td>
        <td>{{ room.nowMember }}</td>
        <td>{{ room.maxMember }}</td>
        <td>{{ room.createdDate }}</td>
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
import { getRoomListWithPaging } from "@/api/roomApi";

export default {
  setup() {
    let rooms = ref([]);

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
      getRoomList(page, size, sort);
    };
    ////////////////////////////////////////////////////////////////////////////////

    async function getRoomList(page = 1, size = 12, sort = "createdDate,desc") {
      await getRoomListWithPaging(
        { page, size, sort },
        ({ data }) => {
          totalPages.value = data.totalPages;
          rooms.value = data.content;

          setVisiblePageNumbers();
        },
        (error) => {
          console.log(error);
        }
      );
    }

    onMounted(async () => {
      await getRoomList(); //메시지 가져오기
      setVisiblePageNumbers(); //페이지 갱신
    });

    return {
      rooms,
      getRoomList,


      totalPages,
      curPage,
      curStartingPage,
      visiblePageNumbers,
      toNextPage,
      topPreviousPage,
      changePage,
      setVisiblePageNumbers,

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