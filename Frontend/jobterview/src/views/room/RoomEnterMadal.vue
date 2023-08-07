<template>
    <div class="modal-container">
      <div class="modal-content">
        <!-- 모달 내용이 들어갑니다 -->
        <h2>모달 제목</h2>
        <p>모달 내용이 여기에 들어갑니다...</p>
        <button @click="closeModal">닫기</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      closeModal() {
        this.$emit("close");
      },
    },
  };
  </script>
  
  <style scoped>
    .modal-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경 딤드 효과 */
  display: none; /* 초기에 숨기기 */
  z-index: 9999; /* 다른 요소 위에 표시되도록 */
}

/* 모달 콘텐츠 스타일 */
.modal-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
}

/* 모달이 보여질 때 보이도록 설정 */
.modal-container.show {
  display: block;
}
  </style>