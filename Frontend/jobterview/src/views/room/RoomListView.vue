<template>
  <div>
    <!-- <RoomModal v-if="showModal" @close="showModal = false" /> -->
    <div v-if="showModal">
      <div class="loginform-group">
                    <label for="방이름"></label>
                    <input type="text" id="email" style="border-radius: 5px; height: 50px;" v-model="ceateRoomName" placeholder="방이름" size="70">
                </div>
                <div class="loginform-group">
                    <label for="최대인원수"></label>
                    <input type="number" id="password" style="border-radius: 5px; height: 50px;" v-model="createMaxMember" placeholder="최대인원수" size="70">
                </div>
                <div style="display: flex; justify-content: center; align-items: center;"> 
                    <button class="button col-8" @click="start()" style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">생성하기</button>
                </div>
                <div style="display: flex; justify-content: center; align-items: center;"> 
                    <button class="button col-8" @click="out()" style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">닫기</button>
                </div>
                
    </div>
  <div class="container" style="width: 1080px">
    <div class="row justify-content-end mt-3">
      <button class="btn btn-primary" @click="makeroom()" id="createButton" >
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
                
                <th class="col-6">방 제목</th>
                <th class="col-2">인원 수</th>
                <th class="col-2">최대 인원</th>
                <th class="col-1"> 입장 </th>
                <th class="col-1"></th>
              </tr>
            </thead>


            <tbody>
              <tr v-for="room in receiveRooms" :key="room.roomId">
                
                <td>{{ room.roomName }}</td><!--클리시 룸아이디 파라미터로 넘겨라 roomId -->
                <td>{{ room.nowMember }}</td>
                <td>{{ room.maxMember }}</td>
                <td>
                  <button class="btn btn-primary" @click="enterroom(room)" id="createButton">
                  입장
                  </button>
                </td>
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
  </div>
  
</template>

<script>
import router from '@/router';
import { computed } from 'vue';
import { useStore } from 'vuex';
//import RoomModal from "./RoomEnterMadal.vue";

export default {
    name: 'RoomList',
    //components: { RoomModal },
    data() {
    return {
      showModal: false,
      createMaxMember:"",
      ceateRoomName:"",

      };
    },
    methods: {
      makeroom() {
        this.showModal = true;
    },
    
    out(){
      this.showModal = false;
    },
    start(){
          this.$store.commit('roomStore/SET_READ_ROOMNAME', this.ceateRoomName);
          this.$store.commit('roomStore/SET_READ_MAX_Member', this.createMaxMember);
          console.log(this.ceateRoomName);
          router.push({ name: 'RoomPermission' });
        }

    
  },
    mounted() {
        this.fetchReceiveRooms();

    },
    setup() {
        const store = useStore();
        const currentReceivePage = computed(() => store.state.roomStore.currentReceivePage);
        const totalReceivePage = computed(() => store.state.roomStore.totalReceivePage);
        const receiveRooms = computed(() => store.state.roomStore.receiveRooms);

        let receivePageRange = computed(() => {
            return Array.from({ length: 3 }, (_, index) => currentReceivePage.value - 1 + index);
        })

        let receiveLastPageRange = computed(() => {
            if (totalReceivePage.value == 2) {
                return [1, 2];
            } else {
                return Array.from({ length: currentReceivePage.value - (currentReceivePage.value - 2) + 1 }, (_, index) => currentReceivePage.value - 2 + index);
            }
        })

        //화면에서 페이지 누르면 현재 페이지 변경
        function changeReceivePage(pageNumber) {
            store.commit('roomStore/SET_CURRENT_RECEIVE_PAGE', pageNumber);
            console.log(currentReceivePage.value);
            fetchReceiveRooms();
        }


        async function fetchReceiveRooms() {
            await store.dispatch('roomStore/getReceiveRooms', currentReceivePage.value - 1 );
        }

        

        function enterroom(data) {
          //룸이름 뷰엑스에 설정
          store.commit('roomStore/SET_READ_ROOMNAME', data.roomName);
          router.push({ name: 'RoomPermission' });
        }

        // function start(){
        //   store.commit('roomStore/SET_READ_ROOMNAME', this.ceateRoomName);
        //   store.commit('roomStore/SET_READ_MAX_Member', this.createMaxMember);
        //   console.log(this.createMaxMember);
        //   router.push({ name: 'RoomPermission' });
        // }

        


        return {
            currentReceivePage,
            totalReceivePage,
            receivePageRange,
            receiveLastPageRange,
            receiveRooms,
            changeReceivePage,
            fetchReceiveRooms,
            enterroom,
            //start,
        };
    }
}
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