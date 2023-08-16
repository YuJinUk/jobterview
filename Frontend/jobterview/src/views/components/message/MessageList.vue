<template>
  <div class="container" style="width: 1080px">
    <div class="row justify-content-end mt-3">
      <button class="btn btn-primary col-1" @click="toSendMessage()" id="write">
        작성
      </button>
    </div>
    <div class="row justify-content-center1">
      <ul class="nav nav-tabs" id="messageTab" role="tablist">
        <li class="nav-item" role="presentation">
          <button
            class="nav-link active"
            id="receive-tab"
            data-bs-toggle="tab"
            data-bs-target="#receive"
            type="button"
            role="tab"
            aria-controls="receive"
            aria-selected="true"
            @click="fetchReceiveMessage(1)"
          >
            받은 쪽지함
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
            class="nav-link"
            id="send-tab"
            data-bs-toggle="tab"
            data-bs-target="#send"
            type="button"
            role="tab"
            aria-controls="send"
            aria-selected="false"
            @click="fetchSendMessage(1)"
          >
            보낸 쪽지함
          </button>
        </li>
      </ul>
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
                <th class="col-6">내용</th>
                <th class="col-2">수신 일자</th>
                <th class="col-2">보낸 사람</th>
                <th class="col-1"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="data in receiveMessage" :key="data.id">
                <td @click="toReadMessage(data, 'receive')">
                  {{ messageSummary(data.content) }}
                </td>
                <td>{{ data.createdDate }}</td>
                <td>{{ data.senderNickname }}</td>
                <td>
                  <button
                    class="btn btn-danger deleteButton"
                    @click="deleteReceiveMessage(data)"
                  >
                    삭제
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
          <PagingComponent
            @changePage="fetchReceiveMessage"
            :totalPage="totalReceivePage"
            :currentPage="currentReceivePage"
          />
        </div>
        <div
          class="tab-pane fade mt-2"
          id="send"
          role="tabpanel"
          aria-labelledby="send-tab"
        >
          <table class="table table-striped content-justify-center text-center">
            <thead>
              <tr>
                <th class="col-6">내용</th>
                <th class="col-2">송신 일자</th>
                <th class="col-2">받는 사람</th>
                <th class="col-1"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="data in sendMessage" :key="data.id">
                <td @click="toReadMessage(data, 'send')">
                  {{ messageSummary(data.content) }}
                </td>
                <td>{{ data.createdDate }}</td>
                <td>{{ data.receiverNickname }}</td>
                <td>
                  <button
                    class="btn btn-danger deleteButton"
                    @click="deleteSendMessage(data)"
                  >
                    삭제
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
          <PagingComponent
            @changePage="fetchSendMessage"
            :totalPage="totalSendPage"
            :currentPage="currentSendPage"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { computed } from "vue";
import { useStore } from "vuex";
import { deleteReceivedMessage, deleteSentMessage } from "@/api/messageApi";
import PagingComponent from "@/views/components/PagingComponent.vue";
export default {
  name: "MessageList",
  components: {
    PagingComponent,
  },
  mounted() {
    this.fetchReceiveMessage(1);
    this.fetchSendMessage(1);
  },
  setup() {
    const store = useStore();
    let currentReceivePage = computed(
      () => store.state.messageStore.currentReceivePage
    );
    let totalReceivePage = computed(
      () => store.state.messageStore.totalReceivePage
    );
    let receiveMessage = computed(
      () => store.state.messageStore.receiveMessage
    );

    let currentSendPage = computed(
      () => store.state.messageStore.currentSendPage
    );
    let totalSendPage = computed(() => store.state.messageStore.totalSendPage);
    let sendMessage = computed(() => store.state.messageStore.sendMessage);

    const nickname = computed(() => store.state.loginStore.loginNickname);

    async function fetchReceiveMessage(pageNumber) {
      await store.dispatch("messageStore/getReceiveMessages", {
        nickname: nickname.value,
        page: pageNumber - 1,
      });
    }

    async function fetchSendMessage(pageNumber) {
      await store.dispatch("messageStore/getSendMessages", {
        nickname: nickname.value,
        page: pageNumber - 1,
      });
    }

    function toReadMessage(data, isReceive) {
      store.commit("messageStore/SET_READ_CONTENT", data.content);
      store.commit("messageStore/SET_READ_CREATED_DATE", data.createdDate);
      store.commit(
        "messageStore/SET_READ_SENDER_NICKNAME",
        data.senderNickname
      );
      store.commit(
        "messageStore/SET_READ_RECEIVER_NICKNAME",
        data.receiverNickname
      );
      store.commit("messageStore/SET_READ_ID", data.id);
      router.push({ name: "MessageRead", params: { category: isReceive } });
    }

    function toSendMessage() {
      router.push({ name: "MessageSend" });
    }

    async function deleteReceiveMessage(data) {
      if (confirm("삭제하시겠습니까?")) {
        deleteReceivedMessage(
          data.id,
          () => {
            fetchReceiveMessage();
          },
          (error) => {
            console.log(error);
          }
        );
      }
    }

    async function deleteSendMessage(data) {
      if (confirm("삭제하시겠습니까?")) {
        deleteSentMessage(
          data.id,
          () => {
            fetchSendMessage();
          },
          (error) => {
            console.log(error);
          }
        );
      }
    }

    function messageSummary(content) {
      if (content.length > 30) {
        return content.substr(0, 30) + "...";
      } else {
        return content;
      }
    }

    return {
      currentReceivePage,
      totalReceivePage,
      receiveMessage,
      fetchReceiveMessage,
      currentSendPage,
      totalSendPage,
      sendMessage,
      fetchSendMessage,
      toReadMessage,
      toSendMessage,
      deleteReceiveMessage,
      deleteSendMessage,
      messageSummary,
    };
  },
};
</script>

<style scoped>
.pagination {
  justify-content: center;
}

.deleteButton {
  border-radius: 10px;
  font-size: 12px;
}

#write {
  background-color: #0f4471;
  font: 700 16px/18px "Lato", sans-serif;
  border: none;
}
</style>