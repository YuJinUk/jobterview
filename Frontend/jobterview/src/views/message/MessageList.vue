<template>
    <div class="container">
        <div class="row justify-content-center mt-3">
            <div class="col-3">
                <input type="text" id="searchMessage" class="form-control">
            </div>
            <div class="col-1">
                <button type="submit" class="btn btn-primary">검색</button>
            </div>
            <div class="col-2">
                <button type="submit" class="btn btn-primary">쪽지 작성</button>
            </div>
        </div>
        <div class="row justify-content-end mt-3">

        </div>
        <div class="row justify-content-center mt-5">
            <ul class="nav nav-tabs" id="messageTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="receive-tab" data-bs-toggle="tab" data-bs-target="#receive"
                        type="button" role="tab" aria-controls="receive" aria-selected="true">받은 쪽지함</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="send-tab" data-bs-toggle="tab" data-bs-target="#send" type="button"
                        role="tab" aria-controls="send" aria-selected="false">보낸 쪽지함</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active mt-2" id="receive" role="tabpanel" aria-labelledby="receive-tab">
                    <table class="table table-striped content-justify-center text-center">
                        <thead>
                            <tr>
                                <th class="col-6 ">내용</th>
                                <th class="col-2">수신 일자</th>
                                <th class="col-2">보낸 사람</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="data in receivedData" :key="data.id">
                                <td>{{ data.content }}</td>
                                <td>{{ data.receiverNickname }}</td>
                                <td>{{ data.senderNickname }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === 1 }">
                                <a class="page-link" href="#" @click="changeReceivePage(1)" aria-label="first">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === 1 }">
                                <a class="page-link" href="#" @click="changeReceivePage(currentReceivePage - 1)"
                                    aria-label="Previous">
                                    <span aria-hidden="true">&lt;</span>
                                </a>
                            </li>
                            <template v-if="currentReceivePage === 1">
                                <li v-for="firstPageNumber in Math.min(totalPages, 3)" :key="firstPageNumber"
                                    :class="{ 'active': currentReceivePage === firstPageNumber }">
                                    <a class="page-link" href="#" @click="changeReceivePage(firstPageNumber)">{{
                                        firstPageNumber }}</a>
                                </li>
                            </template>
                            <template v-else-if="currentReceivePage === totalPages">
                                <li v-for="LastPageNumber in lastPageRange" :key="LastPageNumber"
                                    :class="{ 'active': currentReceivePage === LastPageNumber }">
                                    <a class="page-link" href="#" @click="changeReceivePage(LastPageNumber)">{{
                                        LastPageNumber }}</a>
                                </li>
                            </template>
                            <template v-else>
                                <li v-for="pageNumber in pageRange" :key="pageNumber"
                                    :class="{ 'active': currentReceivePage === pageNumber }">
                                    <a class="page-link" href="#" @click="changeReceivePage(pageNumber)">{{ pageNumber
                                    }}</a>
                                </li>
                            </template>
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === totalPages }">
                                <a class="page-link" href="#" @click="changeReceivePage(currentReceivePage + 1)"
                                    aria-label="Next">
                                    <span aria-hidden="true">&gt;</span>
                                </a>
                            </li>
                            <li class="page-item" :class="{ 'disabled': currentReceivePage === totalPages }">
                                <a class="page-link" href="#" @click="changeReceivePage(totalPages)" aria-label="Last">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="tab-pane fade mt-2" id="send" role="tabpanel" aria-labelledby="send-tab">
                    <table class="table table-striped content-justify-center text-center">
                        <thead>
                            <tr>
                                <th class="col-6">내용</th>
                                <th class="col-2">송신 일자</th>
                                <th class="col-2">받는 사람</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="index in Array.from({ length: count }, (_, index) => index)" :key="index">
                                <td>{{ index }}</td>
                                <td>{{ index }}</td>
                                <td>{{ index }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { ref, computed } from 'vue';


export default {
    name: 'MessageList',
    components: {

    },
    mounted() {
        this.fetchReceiveMessage();
    },
    setup() {
        const count = 13;
        let currentReceivePage = ref(1);
        let totalPages = ref(1);
        let receivedData = ref([]);

        let pageRange = computed(() => {
            return Array.from({ length: 3 }, (_, index) => currentReceivePage.value - 1 + index);
        })

        let lastPageRange = computed(() => {
            if (totalPages.value == 2) {
                return [1, 2];
            } else {
                return Array.from({ length: currentReceivePage.value - (currentReceivePage.value - 2) + 1 }, (_, index) => currentReceivePage.value - 2 + index);
            }
        })

        async function fetchReceiveMessage() {
            await axios.create({
                baseURL: "http://localhost:8080",
                headers: {
                    "Content-Type": "application/json;charset=utf-8",
                },
            }).get(`/message/me?nickname=reporter&page=${currentReceivePage.value-1}`)
            .then((response) => {
                totalPages.value = response.data.totalPages;
                console.log(response.data);
                console.log(response.data.content);
                receivedData.value = response.data.content;
            })
        }

        function changeReceivePage(pageNumber) {
            currentReceivePage.value = pageNumber;
            console.log(currentReceivePage.value);
            fetchReceiveMessage();
        }

        return {
            count,
            currentReceivePage,
            totalPages,
            pageRange,
            lastPageRange,
            receivedData,
            fetchReceiveMessage,
            changeReceivePage,
        };
    }
}
</script>

<style>
.pagination {
    justify-content: center;
}
</style>