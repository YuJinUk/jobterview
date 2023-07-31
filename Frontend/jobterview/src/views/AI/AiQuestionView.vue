<template>
  <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>
    <div id="mainBox">
      <div id="categoryBox">
        <ul class="categoryList">
          <li v-for="category in categoryList" :key="category" @click="changeCategory">
            {{ category.display }}
          </li>
        </ul>
      </div>
      <div id="line"></div>
      <div id="contentBox">
        <div id="questionbox">
          <ul class="questionList">
            <li v-for="question in questionList" :key="question.questionId">
              <p>{{ question.content }}</p>
              <font-awesome-icon class="checkIcon" icon="fa-solid fa-check" />
            </li>
          </ul>
        </div>
        <div id="buttonBox">
          <p>총 4개 중</p>
          <p>{{ selectedQuestionsLength }}</p>
          <p>개의 질문을 선택하셨습니다.</p>
          <button>다음</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, computed, ref } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();

    //data
    const selectedQuestions = ref([]);
    const selectedCategory = ref({ name: "VISION", display: "비전 / 목표 질문" });
    const categoryList = ref([
        { name: "VISION", display: "비전 / 목표 질문" },
        { name: "ADAPT", display: "적응력 질문" },
        { name: "VALUES", display: "가치관 질문" },
        { name: "PRESSURE", display: "압박 질문" },
        { name: "JOB", display: "직무 질문" },
      ]);

      const selectedQuestionsLength = () => selectedQuestions.value.length;

    //onMounted
    onMounted(() => {
      console.log(selectedCategory.value);
      store.dispatch("roomStore/setCategoryAndQuestionList", selectedCategory.value.name);
    });

    return {
      //data
      selectedQuestions,
      selectedCategory,
      categoryList,

      //computed
      selectedQuestionsLength,
      questionList: computed(() => store.getters["roomStore/getQuestionList"]),
    };
  },
};
</script>

<style scoped>
li {
  list-style: none;
}

.checkIcon {
  margin-right: 15px; 
}

#container {
  width: 1080px;
  height: 750px;
  display: block;
  margin: 0 auto;

  display: block;
  align-items: center;
  justify-content: center;
}

#headTextBox {
  text-align: center;
  font-size: 32px;
  font-weight: bold;
  color: #0f4471;

  padding: 40px 0;
}

#mainBox {
  width: 1024px;
  height: 500px;
  background-color: #f0f0f0;

  margin: 0 auto;
  border-radius: 10px;

  display: flex;
}

#categoryBox {
  width: 200px;
}

.categoryList {
  margin: 0;
  padding: 60px 0 60px 60px;

  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.categoryList li {
  padding: 0px 0px;

  font-size: 18px;
  color: #0f4471;
}

#line {
  width: 5%;
  height: 80%;

  margin: auto 0;

  border-right: 1px solid #0f4471;
}

#questionbox {
  background-color: wheat;
  width: 725px;
  height: 350px;

  margin: 50px 0 0 0;
}

.questionList {
  width: 100%;
  height: 100%;

  overflow: auto;
}

.questionList li {
  width: 650px;
  height: 50px;

  color: #0f4471;

  border-radius: 10px;

  margin: 18px 0;
  padding-left: 15px;

  display: flex;
  align-items: center;
  justify-content: space-between;

  background-color: whitesmoke;
}

.questionList li p {
  margin: 0;
}

.questionList li i {
  width: 100px;
  height: 100px;
}

#buttonBox {
  display: flex;
}
</style>
