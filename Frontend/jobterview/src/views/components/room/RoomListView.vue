<template>
  <div>
    <div class="modal-container" v-if="showModal">
      <div class="modal-overlay"></div>
      <div class="modal-content">
        <h1 style="text-align: center; font-weight: bold; font-size: 24px; margin-bottom: 20px;">방을 생성해 보아요</h1>
        <br>
        <div class="loginform-group">
          <label for="방이름"></label>
          <input type="text" id="email" style="border-radius: 5px; height: 50px;" v-model="createRoomName"
            placeholder="방이름" size="70">
        </div>
        <br>
        <div class="loginform-group">
          <label for="최대인원수"></label>
          <input type="number" id="maxNum" style="border-radius: 5px; height: 50px;" v-model="createMaxMember"
            placeholder="최대인원수" size="70">
        </div>
        <br>
        <div style="display: flex; align-items: center; ">
          <span style="display: flex; align-items: center;">
            <label for="checkbox" style="white-space: nowrap; margin-right: 5px;">비밀번호 설정</label>
            <input type="checkbox" id="checkbox" v-model="checked">
          </span>

        </div>

        <div class="loginform-group" v-if="checked">
          <label for="비밀번호"></label>
          <input type="password" id="password" style="border-radius: 5px; height: 50px;" v-model="createRoomPassword"
            placeholder="비밀번호" size="70">
        </div>
        <br>

        <div style="display: flex; justify-content: center; align-items: center;">
          <button class="button col-8" @click="check(this.createRoomName)"
            style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">시작하기</button>
        </div>

        <div style="display: flex; justify-content: center; align-items: center;">
          <button class="button col-8" @click="out()"
            style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">닫기</button>
        </div>
      </div>
    </div>
    <br>
    <div class="container" style="width: 1080px">
      <div class="row justify-content-center1">
        <div>
          <div class="tab-pane fade show active mt-2" id="receive" role="tabpanel" aria-labelledby="receive-tab">
            <table>
              <thead>
                <tr>
                  <th class="col-3"></th>
                  <th class="col-7" style="vertical-align: middle;">
                    <div class="search">
                      <input type="text" placeholder="방이름 검색" v-model="keyword" @keyup.enter="searchRoom()" />
                      <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
                        @click="searchRoom()" />
                    </div>
                  </th>

                  <th class="col-1"></th>
                  <th class="col-1" style="vertical-align: middle;">
                    <button class="btn btn-primary input-btn-equal-height" @click="makeroom()" id="createButton">
                      방 만들기
                    </button>
                  </th>
                </tr>
              </thead>
            </table>
          </div>
        </div>
      </div>
    </div>
    <br>
    <br>

    <!-- 검색창 -->


    <div class="container" style="width: 1080px">

      <div class="row justify-content-center1">
        <div class="tab-content" id="myTabContent">
          <div class="tab-pane fade show active mt-2" id="receive" role="tabpanel" aria-labelledby="receive-tab">
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
                  <td v-if="room.roomPassword == ' '">
                    <i class="bi bi-unlock"></i>
                  </td>
                  <td v-else>
                    <i class="bi bi-lock-fill"></i>
                  </td>
                </tr>
              </tbody>


            </table>


            <!-- 페이지네이션 -->
            <nav aria-label="Page navigation">
              <ul class="pagination justify-content-center">
                <li class="page-item" :class="{ disabled: currentReceivePage === 1 }">
                  <a class="page-link" href="#" @click="changeReceivePage(1)" aria-label="first">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
                <li class="page-item" :class="{ disabled: currentReceivePage === 1 }">
                  <a class="page-link" href="#" @click="changeReceivePage(currentReceivePage - 1)" aria-label="Previous">
                    <span aria-hidden="true">&lt;</span>
                  </a>
                </li>
                <template v-if="currentReceivePage === 1">
                  <li v-for="firstPageNumber in Math.min(totalReceivePage, 3)" :key="firstPageNumber"
                    :class="{ active: currentReceivePage === firstPageNumber }">
                    <a class="page-link" href="#" @click="changeReceivePage(firstPageNumber)">{{ firstPageNumber }}</a>
                  </li>
                </template>
                <template v-else-if="currentReceivePage === totalReceivePage">
                  <li v-for="LastPageNumber in receiveLastPageRange" :key="LastPageNumber"
                    :class="{ active: currentReceivePage === LastPageNumber }">
                    <a class="page-link" href="#" @click="changeReceivePage(LastPageNumber)">{{ LastPageNumber }}</a>
                  </li>
                </template>
                <template v-else>
                  <li v-for="pageNumber in receivePageRange" :key="pageNumber"
                    :class="{ active: currentReceivePage === pageNumber }">
                    <a class="page-link" href="#" @click="changeReceivePage(pageNumber)">{{ pageNumber }}</a>
                  </li>
                </template>
                <li class="page-item" :class="{ disabled: currentReceivePage === totalReceivePage }">
                  <a class="page-link" href="#" @click="changeReceivePage(currentReceivePage + 1)" aria-label="Next">
                    <span aria-hidden="true">&gt;</span>
                  </a>
                </li>
                <li class="page-item" :class="{ disabled: currentReceivePage === totalReceivePage }">
                  <a class="page-link" href="#" @click="changeReceivePage(totalReceivePage)" aria-label="Last">
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
import { computed, ref } from 'vue';
import { useStore } from 'vuex';
import { receiveRoomName } from '@/api/roomApi';
//import RoomModal from "./RoomEnterMadal.vue";

export default {
  name: 'RoomList',
  //components: { RoomModal },
  data() {
    return {
      showModal: false,
      createMaxMember: "",
      createRoomName: "",
      createRoomPassword: "",
      checked: false,
      //keyword:"",


    };
  },
  watch: {
    checked(newVal) {
      if (newVal) {
        this.checkd = true;
        //console.log(this.checkd);
      } else {
        this.checkd = false;
        this.createRoomPassword = "";
        //console.log(this.checkd);
      }
    },
  },
  methods: {
    makeroom() {
      this.showModal = true;
    },

    out() {
      this.showModal = false;
    },


    async check() {
      await receiveRoomName(this.createRoomName,
        (response) => {
          if (response.data == 1) {
            alert("이미 존재하는 이름입니다.")
          } else {
            if (this.createMaxMember > 6) {
              alert("최대 인원은 6인 입니다")
            } else if (this.createMaxMember == "") {
              alert("최대 인원수를 입력해 주세요")

            } else if (this.createRoomName == "") {
              alert("빈방은 안되지^^")

            } else {
              if (this.createRoomPassword == " " || this.createRoomPassword == "") {
                this.$store.commit('roomStore/SET_READ_ROOMNAME', this.createRoomName);
                this.$store.commit('roomStore/SET_READ_MAX_Member', this.createMaxMember);
                this.$store.commit('roomStore/SET_READ_ROOM_PASSWORD', " ");
                router.push({ name: 'RoomPermission' });
              } else {
                this.$store.commit('roomStore/SET_READ_ROOMNAME', this.createRoomName);
                this.$store.commit('roomStore/SET_READ_MAX_Member', this.createMaxMember);
                this.createRoomPassword = this.createRoomPassword.trim();
                this.$store.commit('roomStore/SET_READ_ROOM_PASSWORD', this.createRoomPassword);
                router.push({ name: 'RoomPermission' });
              }
            }
          }
        },
        (error) => {
          console.log(error);
        })
    },



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
      await store.dispatch('roomStore/getReceiveRooms', currentReceivePage.value - 1);
    }

    // function getInputValue(promptText) {
    //   const input = prompt(promptText);
    //   return input;
    // }



    function enterroom(data) {
      //룸이름 뷰엑스에 설정
      if (data.maxMember == data.nowMember) {
        alert("입장 인원이 가득 찼습니다")
      } else {
        if (data.roomPassword == " ") {
          store.commit('roomStore/SET_READ_ROOMNAME', data.roomName);
          store.commit('roomStore/SET_READ_ROOM_PASSWORD', data.roomPassword);
          router.push({ name: 'RoomPermission' });
        } else {
          if (prompt('비밀번호를 입력하세요:') == data.roomPassword) {
            //비밀번호 설정
            store.commit('roomStore/SET_READ_ROOM_PASSWORD', data.roomPassword);
            store.commit('roomStore/SET_READ_ROOMNAME', data.roomName);
            router.push({ name: 'RoomPermission' });

          } else {
            alert("비밀번호가 다릅니다.");
          }
        }
      }
    }

    const keyword = ref('');

    async function searchRoom() {
      //console.log(keyword.value);
      await store.dispatch('roomStore/SearchRoomList', { keyword: keyword.value, page: currentReceivePage.value - 1 });
    }



    return {
      currentReceivePage,
      totalReceivePage,
      receivePageRange,
      receiveLastPageRange,
      receiveRooms,
      changeReceivePage,
      fetchReceiveRooms,
      enterroom,
      searchRoom,
      keyword,
      //check,
      //start,
    };
  }
}
</script>

<style scoped>
.search {
  position: relative;
  width: 100%;
  /* min-width: 170px; */
  /* max-width: 230px; */
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

.pagination {
  justify-content: center;
}

#createButton {
  width: 100px;

  background-color: #0f4471;
  color: white;
  font: 500 16px/18px "Lato", sans-serif;
}

.modal-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

.modal-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  /* 어두운 배경 색상 */
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  z-index: 101;
  /* 모달 내용이 레이어 위에 올라오도록 */
  width: 70%;
  /* 원하는 너비 값으로 조정 */
  max-width: 500px;
  /* 원하는 최대 너비 값으로 조정 */
  margin: 0 auto;
  /* 가운데 정렬 */
}
</style>
