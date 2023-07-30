<template>
  <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>
    <div id="mainBox">
      <div id="categoryBox">
        <ul class="categoryList">
          <li
            v-for="category in categories"
            :key="category.id"
            @click="selectCategory(category)"
          >
            {{ category.name }}
          </li>
        </ul>
      </div>
      <div id="line"></div>
      <div id="contentBox">
        <div id="questionbox">
          <ul class="questionList">
            <li v-for="question in questions" :key="question.id">
              <p>{{ question.content }}</p>
              <font-awesome-icon icon="fa-solid fa-check" />
            </li>
          </ul>
        </div>
        <div id="buttonBox">
          <p>총 4개 중</p>
          <p>{{ selectedQuestion.length }}</p>
          <p>개의 질문을 선택하셨습니다.</p>
          <button>다음</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { mapGetters }from 'vuex';

export default {
  data() {
    return {
      headText: "연습할 질문을 선택해주세요.",
      selectedCategory: { id: 1, name: "비전/목표 질문" },
      categories: [
        { id: 1, name: "비전/목표 질문" },
        { id: 2, name: "적응력 질문" },
        { id: 3, name: "가치관 질문" },
        { id: 4, name: "압박 질문" },
        { id: 5, name: "직무 질문" },
      ],
      selectedQuestion: [],
      questions: [
        { id: 1, content: "입사 후 포부가 무엇입니까?" },
        { id: 2, content: "입사 5년 후, 10년 후 자신의 모습은 어떨 것이라고 생각합니까?" },
        { id: 3, content: "본인의 직업관은 무엇입니까?" },
        { id: 4, content: "당신에게 일이 왜 중요합니까?" },
        { id: 5, content: "직장은 어떤 면을 보고 선택합니까?" },
        { id: 6, content: "해당 직무를 선택한 이유가 무엇입니까?" },
        { id: 7, content: "현재 해당 산업군의 시장 동향에 대해 어떻게 생각합니까?"},
        { id: 8, content: "앞으로의 발전 계획이 어떻게 됩니까?" },
      ],
      allQuestionSelected: false,
    };
  },
  methods: {
    selectCategory(category) {
      this.selectedCategory = category;
    },
  },
  // computed: {
  //   ...mapGetters(['getCategory']),
  //   categories() {
  //       return this.getCategory;
  //   }
  // }
};
</script>

<style scoped>
li {
  list-style: none;
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

.questionList li p{
  margin: 0;
}

.questionList li i{
  width: 100px;
  height: 100px;
}

#buttonBox {
  display: flex;
}
</style>
