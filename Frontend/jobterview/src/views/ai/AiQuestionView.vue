<template>
  <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>

    <div id="mainBox">
      <!-- 카테고리 목록 -->
      <div id="categoryBox">
        <ul class="categoryList">
          <li
            v-for="category in categoryList"
            :key="category"
            :class="{ selectedCategory: category.selected }"
            @click="changeCategory(category)"
          >
            {{ category.display }}
            <font-awesome-icon
              v-if="category.cnt > 0"
              class="checkIcon"
              icon="fa-solid fa-check"
            />
          </li>
        </ul>
      </div>

      <!-- 세로 선 -->
      <div id="line"></div>

      <!-- 질문 목록과 버튼 -->
      <div id="contentBox">
        <!-- 질문 목록 -->
        <div id="questionbox">
          <ul class="questionList">
            <li
              v-for="question in categorizedQuestionList"
              :key="question.questionId"
              :class="{ selectedQuestion: question.selected }"
              @click="selectQuestion(question)"
            >
              <p>{{ question.content }}</p>

              <font-awesome-icon
                class="minusIcon"
                v-if="question.selected"
                icon="fa-solid fa-minus"
              />
              <font-awesome-icon
                class="plusIcon"
                v-else
                icon="fa-solid fa-plus"
              />
            </li>
          </ul>
        </div>

        <div>
           정확한 음성 분석을 위해 성별을 선택해주세요. 
           <button class="genderButton" :class="{selected: gender}" @click="toggleGender()" >남성</button>
           <button class="genderButton" :class="{selected: !gender}" @click="toggleGender()" >여성</button>
        </div>


        <!-- 버튼 -->
        <div id="buttonBox">
          <p>총 4개 중&nbsp;</p>
          <p>{{ selectedQuestionsLength }}</p>
          <p>개의 질문을 선택하셨습니다.</p>

          <router-link :to="{ name: 'AiWebCam' }">
            <button :class="{ activeButton: isAllSelected }">다음</button>
          </router-link>
          <!-- <button :class="{ activeButton: isAllSelected }">다음</button> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, computed, ref } from "vue";
import { useStore } from "vuex";
import { getQuestionListAPI } from "@/api/questionApi";

export default {
  setup() {
    const store = useStore();

    //data
    const headText = ref("연습할 질문을 선택해주세요.");

    const categoryList = ref([
      { name: "VISION", display: "비전/목표 질문", selected: true, cnt: 0 },
      { name: "ADAPT", display: "적응력 질문", selected: false, cnt: 0 },
      { name: "VALUES", display: "가치관 질문", selected: false, cnt: 0 },
      { name: "PRESSURE", display: "압박 질문", selected: false, cnt: 0 },
      { name: "JOB", display: "직무 질문", selected: false, cnt: 0 },
    ]);
    let categorizedQuestionList = ref([]);
    const allQuestionList = ref([]);

    const selectedQuestions = ref([]);
    const isAllSelected = ref(false);
    const gender = ref(true);

    //computed/////////////////////////////////////////////////////////////////////////////////////
    //선택된 질문의 갯수 반환
    const selectedQuestionsLength = computed(
      () => selectedQuestions.value.length
    );

    //methods
    //전체 질문 목록중에 특정 카테고리로 분류
    const categorizeQuestion = (categoryName) => {
      categorizedQuestionList.value = allQuestionList.value.filter(
        (question) => question.category === categoryName
      );
    };

    //카테고리 선택시 해당 카테고리만 selected true;
    const changeCategory = (category) => {
      //선택된 카테고리 true
      category.selected = true;

      //선택된 카테고리의 질문만 분류
      categorizeQuestion(category.name);

      //선택되지 않은 카테고리 false
      const list = categoryList.value;
      for (let i = 0; i < list.length; i++) {
        if (list[i] !== category) {
          list[i].selected = false;
        }
      }
      categoryList.value = list;
    };

    //질문 선택시 선택된 질문 목록에 추가
    const selectQuestion = (question) => {
      //이미 선택한 질문인 경우
      if (selectedQuestions.value.includes(question)) {
        //해당 질문 삭제
        selectedQuestions.value = selectedQuestions.value.filter(
          (value) => value !== question
        );

        //카테고리 선택 카운트 감소
        for (let i = 0; i < categoryList.value.length; i++) {
          if (categoryList.value[i].name === question.category) {
            categoryList.value[i].cnt--;
          }
        }
        //질문 선택 취소
        question.selected = false;
        isAllSelected.value = false;
        return;
      }

      //4개 미만인 경우
      if (selectedQuestions.value.length < 4) {
        selectedQuestions.value.push(question);

        //카테고리 선택 카운트 증가
        for (let i = 0; i < categoryList.value.length; i++) {
          if (categoryList.value[i].name === question.category) {
            categoryList.value[i].cnt++;
          }
        }

        question.selected = true;
      }

      //4개인경우 store 갱신
      if (selectedQuestions.value.length === 4) {
        isAllSelected.value = true;

        store.dispatch(
          "roomStore/setSelectedQuestions",
          selectedQuestions.value
        );
      }
    };

    const toggleGender = () => {
      console.log('toggleGender');
      gender.value = !gender.value;
      console.log(gender.value);
      selectGender();
    }

    const selectGender = () => {
      console.log('selectGender');

      let selectedGender = '';

      if(gender.value) {
        selectedGender = 'male';
      } else {
        selectedGender = 'female';
      }

      console.log(selectedGender);
      // 성별 값 Vuex 스토어에 저장
      store.dispatch("roomStore/setSelectGen", selectedGender);
    };

    //onMounted
    onMounted(async () => {
      //전체 질문 목록 가져오는 api 실행
      await getQuestionListAPI(
        ({ data }) => {
          //가져온 질문 목록에 selected 추가
          allQuestionList.value = data.map((question) => {
            return { ...question, selected: false };
          });
          categorizeQuestion("VISION");
        },
        //에러 발생시
        (error) => console.log(error)
      );
    });

    /////////////////////////////////////////////////////////////////////////////////////////////////
    return {
      //data
      gender,
      headText,
      selectedQuestions,
      categoryList,
      isAllSelected,
      categorizedQuestionList,
      selectGender,
      toggleGender,

      //method
      changeCategory,
      selectQuestion,

      //computed
      selectedQuestionsLength,
    };
  },
};
</script>

<style scoped src="@/css/aiQuestionView.css" >

</style>