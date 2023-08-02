<template>
  <div class="container" style="width: 1080px">
    <div class="row justify-content-end mt-3">
      <button class="btn btn-primaryㄴ" @click="toSendMessage()" id="createButton">
        방 만들기
      </button>
    </div>
    <div class="row justify-content-center1">
      <div class="tab-content" id="myTabContent">
        <div
          class="tab-pane fade show active mt-2"
          id="receive"
          role="tabpanel"
          aria-labelledby="receive-tab"
        >
          <table class="table table-striped content-justify-center text-center">
            <thead>
              <tr>
                <th class="col-1"></th>
                <th class="col-8">방 제목</th>
                <th class="col-2">인원 수</th>
                <th class="col-1"></th>
              </tr>
            </thead>


            <tbody>
              <tr v-for="room in roomList" :key="room.roomId">
                <td></td>
                <td>{{ room.roomName }}</td>
                <td>{{ room.nowMember }}</td>
                <td></td>
              </tr>
            </tbody>


          </table>


          <!-- 페이지네이션 -->
          <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
              <li
                class="page-item"
                :class="{ disabled: currentReceivePage === 1 }"
              >
                <a
                  class="page-link"
                  href="#"
                  @click="changeReceivePage(1)"
                  aria-label="first"
                >
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li
                class="page-item"
                :class="{ disabled: currentReceivePage === 1 }"
              >
                <a
                  class="page-link"
                  href="#"
                  @click="changeReceivePage(currentReceivePage - 1)"
                  aria-label="Previous"
                >
                  <span aria-hidden="true">&lt;</span>
                </a>
              </li>
              <template v-if="currentReceivePage === 1">
                <li
                  v-for="firstPageNumber in Math.min(totalReceivePage, 3)"
                  :key="firstPageNumber"
                  :class="{ active: currentReceivePage === firstPageNumber }"
                >
                  <a
                    class="page-link"
                    href="#"
                    @click="changeReceivePage(firstPageNumber)"
                    >{{ firstPageNumber }}</a
                  >
                </li>
              </template>
              <template v-else-if="currentReceivePage === totalReceivePage">
                <li
                  v-for="LastPageNumber in receiveLastPageRange"
                  :key="LastPageNumber"
                  :class="{ active: currentReceivePage === LastPageNumber }"
                >
                  <a
                    class="page-link"
                    href="#"
                    @click="changeReceivePage(LastPageNumber)"
                    >{{ LastPageNumber }}</a
                  >
                </li>
              </template>
              <template v-else>
                <li
                  v-for="pageNumber in receivePageRange"
                  :key="pageNumber"
                  :class="{ active: currentReceivePage === pageNumber }"
                >
                  <a
                    class="page-link"
                    href="#"
                    @click="changeReceivePage(pageNumber)"
                    >{{ pageNumber }}</a
                  >
                </li>
              </template>
              <li
                class="page-item"
                :class="{ disabled: currentReceivePage === totalReceivePage }"
              >
                <a
                  class="page-link"
                  href="#"
                  @click="changeReceivePage(currentReceivePage + 1)"
                  aria-label="Next"
                >
                  <span aria-hidden="true">&gt;</span>
                </a>
              </li>
              <li
                class="page-item"
                :class="{ disabled: currentReceivePage === totalReceivePage }"
              >
                <a
                  class="page-link"
                  href="#"
                  @click="changeReceivePage(totalReceivePage)"
                  aria-label="Last"
                >
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { useStore } from "vuex";

export default {
  setup() {
    // const store = useStore();

    return {};
  },
};
</script>

<style scoped>
.pagination {
    justify-content: center;
}

.deleteButton {
    border-radius: 10px;
}

#createButton {
    width: 100px;

    background-color: #0f4471;
    color: white;
    font: 500 16px/18px "Lato", sans-serif;
}

</style>