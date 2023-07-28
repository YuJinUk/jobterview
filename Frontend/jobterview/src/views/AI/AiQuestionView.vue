<template>
  <div id="container">
    <div id="headTextBox">
      {{ headText }}
    </div>
    <div id="questionBox">
      <div id="category">
        <ul>
            <li v-for="category in categories" :key="category.id">
                <a>{{ category.name }}</a>
            </li>
        </ul>
      </div>
      <div id="line">

      </div>
      <div id="listBox">
        <div id="list">

        </div>
        <div id="buttonBox">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      headText: "연습할 질문을 선택해주세요.",
    };
  },
  computed: {
    ...mapGetters(['getCategory']),
    categories() {
        return this.getCategory;
    }
  }
};
</script>

<style scoped>
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

#questionBox {
  width: 1024px;
  height: 500px;
  background-color: #979797;
  opacity: 0.15;

  margin: 0 auto;
  border-radius: 10px;
}

#questionBox {
    display: flex;
}

#category {
    display: block;
    align-items: center;
}

</style>