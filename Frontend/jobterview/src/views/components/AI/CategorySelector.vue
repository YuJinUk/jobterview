<template>
  <div id="category">
        <ul>
            <li v-for="category in categories" :key="category.id" @click="onCategoryChange(category)">
                {{ category.name }}
            </li>
        </ul>
      </div>
</template>

<script>
export default {
    data() {
    return {
      selectedCategory: { id: 1, name: '전체 질문'},
      categories: [
        { id: 1, name: '전체 질문' },
        { id: 2, name: '비전/목표 질문' },
        { id: 3, name: '적응력 질문' },
        { id: 4, name: '가치관 질문' },
        { id: 5, name: '압박 질문' },
        { id: 6, name: '직무 질문' },
      ],
    };
  },
  methods: {
    onCategoryChange(selected) {
      selectedCategory = selected;
      this.$emit('category-change', this.selectedCategory);
    },
  },
}
</script>

<style>

</style>