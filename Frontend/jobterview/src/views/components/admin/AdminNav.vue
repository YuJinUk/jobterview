<template>
  <nav>
    <router-link :to="{ name: 'AdminMember' }">
      <button class="mateButton">맴버 목록</button>
    </router-link>
    <router-link :to="{ name: 'AdminMessage' }">
      <button class="mateButton">쪽지 목록</button>
    </router-link>
    <router-link :to="{ name: 'AdminQuestion' }">
      <button class="mateButton">질문 목록</button>
    </router-link>
    <router-link :to="{ name: 'AdminRoom' }">
      <button class="mateButton">채팅방 목록</button>
    </router-link>
  </nav>
</template>

<script>
export default {};
</script>

<style>
</style>