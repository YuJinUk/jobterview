<template>
    <div>
        <div class="total-form">
            <div class="email-form">
                <h1 style="color:#ffffff; text-align:center;"><b>비밀번호 찾기</b></h1>
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="email"
                            style="border-radius: 5px; margin-right: 10px;" v-model="email" placeholder="이메일" />
                    </div>
                </div>
                <div style="display: flex; justify-content: center; align-items: center;">
                    <button class="button col-3" @click="submit"
                        style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">메일 받기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {checkEmail,sendPasswordEmail} from "@/api/joinApi";
export default {
    name: 'FindPassword',
    data() {
        return {
            email: "",
        };
    },
    methods: {
      async submit() {
                   await checkEmail(this.email,(response)=>{
                    if(response.data==0){
                        sendPasswordEmail(this.email, () => {
                            alert("등록하신 메일로 인증메일이 전송 되었습니다.");           
                         }, (error) => {
                             console.log(error);
                         });
                    }
                    (error)=>{
                       alert("존재하지 않는 이메일입니다.");
                       console.log(error);
      }});}

       }
}
</script>
<style>
.total-form {
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 600px;
    background-color: #0F4471;

}
.email-form {
    display: block;
    align-items: center;
    justify-content: center;
    width: 700px;
    height: 400px;
    border-style: solid;
    border: 5px solid #eeeeee;
}
.form-group {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    padding: 15px;
    width: 100%;
}
.input-group {
    margin-left: 72%;
}
</style>
