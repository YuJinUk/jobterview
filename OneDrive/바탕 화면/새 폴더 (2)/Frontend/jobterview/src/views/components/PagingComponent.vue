<template>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
                <a class="page-link" href="#" @click="changePage(1)" aria-label="first">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
                <a class="page-link" href="#" @click="changePage(currentPage - 1)" aria-label="Previous">
                    <span aria-hidden="true">&lt;</span>
                </a>
            </li>
            <template v-if="currentPage === 1">
                <li v-for="firstPageNumber in Math.min(totalPage, 5)" :key="firstPageNumber"
                    :class="{ 'active': currentPage === firstPageNumber }">
                    <a class="page-link" href="#" @click="changePage(firstPageNumber)">{{
                        firstPageNumber }}</a>
                </li>
            </template>
            <template v-else-if="currentPage === totalPage">
                <li v-for="LastPageNumber in lastPageRange" :key="LastPageNumber"
                    :class="{ 'active': currentPage === LastPageNumber }">
                    <a class="page-link" href="#" @click="changePage(LastPageNumber)">{{
                        LastPageNumber }}</a>
                </li>
            </template>
            <template v-else>
                <li v-for="pageNumber in pageRange" :key="pageNumber" :class="{ 'active': currentPage === pageNumber }">
                    <a class="page-link" href="#" @click="changePage(pageNumber)">{{ pageNumber
                    }}</a>
                </li>
            </template>
            <li class="page-item" :class="{ 'disabled': currentPage === totalPage }">
                <a class="page-link" href="#" @click="changePage(currentPage + 1)" aria-label="Next">
                    <span aria-hidden="true">&gt;</span>
                </a>
            </li>
            <li class="page-item" :class="{ 'disabled': currentPage === totalPage }">
                <a class="page-link" href="#" @click="changePage(totalPage)" aria-label="Last">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</template>

<script>
import { computed } from 'vue';

export default {
    name: "PagingComponent",
    props: {
        // 상위 컴포넌트로부터 전달받을 숫자 형태의 props
        totalPage: {
            type: Number,
            default: () => 0 
        },
        currentPage: {
            type: Number,
            default: () => 1 
        }
    },
    setup(props, context) {  

      let pageRange = computed(() => {
            let array = [];
            if (props.currentPage == 2) {
                array = Array.from({ length: 5 }, (_, index) => 1 + index);
            } else if (props.currentPage == props.totalPage - 1) {
                array = Array.from({ length: 5 }, (_, index) => props.currentPage - 3 + index);
            } else {
                array = Array.from({ length: 5 }, (_, index) => props.currentPage - 2 + index);
            }
            return array.filter((value) => {
                return (value >= 1 && value <= props.totalPage);
            });
        });

        let lastPageRange = computed(() => {
            if (props.totalPage < 5) {
                const array = [];
                for (let i = 1; i <= props.totalPage; i++) {
                    array.push(i);
                }
                return array;
            } else {
                return Array.from({ length: props.currentPage - (props.currentPage - 4) + 1 }, (_, index) => props.currentPage - 4 + index);
            }
        });

        function changePage(pageNumber) {
            context.emit('changePage', pageNumber);
        }

        return {
            pageRange,
            lastPageRange,
            changePage,
        };
    }
}
</script>