<template>
<div class="findPasswordForm">
    <h1 style="color:#ffffff"><b>비밀번호 찾기</b></h1>
    <div class="form-group">
        <label for="email"></label>
        <input type="text" id="email" style="border-radius: 5px;" v-model="email" placeholder="이메일">
    </div>
    <b-button block variant="primary" type="submit" @click="submit" style="background-color:#2c3e50">찾기</b-button>
</div>
</template>
<script>

</script>
<style>
.findPasswordForm{
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 600px;
    background-color: #2c3e50;
}
.form-group {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center; 
    padding: 15px;
    width:100%;
}
</style>