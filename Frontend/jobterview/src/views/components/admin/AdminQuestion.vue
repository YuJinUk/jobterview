<template>
  <h1>면접 질문 관리</h1>

  <nav>
    <button v-for="category in categories" :key="category" @click="selectCategory(category)">
      {{category}}</button>
  </nav>

  <div v-if="questions.length === 0">
    등록된 질문이 없습니다.
  </div>

  <table v-else class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">id</th>
        <th scope="col">category</th>
        <th scope="col">content</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(question, index) in questions" :key="index">
        <th scope="row">{{ index + 1 }}</th>
        <td>{{ question.questionId }}</td>
        <td>{{ question.category }}</td>
        <td>{{ question.content }}</td>
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
import { getQuestionListWithPagingAPI, getQuestionListByCategoryWithPagingAPI } from "@/api/questionApi";

export default {
  setup() {
    const categories = ref([
      'ALL','VISION', 'ADAPT', 'VALUES', 'PRESSURE', 'JOB'
    ])
    let selectedCategory = ref('ALL');

    let questions = ref([]); //유저 목록

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
    const changePage = (page = 1, size = 10, sort = "createdDate,desc") => {
      curPage.value = page;
      getQuestionsByCategory(page, size, sort);
    };
    ////////////////////////////////////////////////////////////////////////////////
    //카테고리 선택 및 조회
    const selectCategory = (category) => {
      selectedCategory.value = category;
      getQuestionsByCategory();
    }

    //카테고리 기반으로 페이징 처리된 목록 조회
    async function getQuestionsByCategory( page = 1, size = 10, sort = "createdDate,desc" ) {
      const category = selectedCategory.value;

      //카테고리가 전체인 경우
      if(category === 'ALL') {
        await getQuestionListWithPagingAPI(
        { page, size, sort },
        ({ data }) => {
          totalPages.value = data.totalPages;
          questions.value = data.content;
          setVisiblePageNumbers();
        },
        (error) => {
          console.log(error);
        }
      );
        return;
      }

      //선택된 카테고리가 있는 경우
      await getQuestionListByCategoryWithPagingAPI(
        { category, page, size, sort },
        ({ data }) => {
          totalPages.value = data.totalPages;
          questions.value = data.content;
          setVisiblePageNumbers();
        },
        (error) => {
          console.log(error);
        }
      );
    }

    onMounted(async () => {
      await getQuestionsByCategory();
      setVisiblePageNumbers(); //페이지 갱신
    });

    return {
      questions,
      selectedCategory,
      selectCategory,
      categories,
      getQuestionsByCategory,

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