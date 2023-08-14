<template>
    <div>
        <div style="margin-top:60px ">
        <h1 style="color: #000000"><b>비밀번호 찾기</b></h1>
      </div>
        <div class="totalForm">
          <div class="findPasswordForm">
            <div class="findPasswordForm-group">
              <label for="email" ></label>
              <input
                class="inputForm"
                type="email"
                id="email"
                style="border-radius: 5px; height: 50px"
                v-model="email"
                placeholder="  email"
                size="40"
              />
            </div>
            <br>
            <br/>
            <div style="  display: flex;
            align-items: center;
            flex-direction: column;
            justify-content: center;
            width: 100%; ">               
                  <button class="findPassword" @click="submit">메일 받기</button>
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
<style scoped>
.totalForm {
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 600px;
    background-color: #ffffff;
}
.findPasswordForm {
    display: block;
    align-items: center;
    justify-content: center;
    width: 700px;
    height: 400px;
}

.findPasswordForm-group {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    padding: 15px;
    width: 100%;
}

.findPassword {
    background-color: #083358;
    pointer-events: all;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 14px;
    font-weight: 500;
    width:369.6px;
    height: 50px;
    transition: all 0.15s ease-in-out;
    margin-bottom: 15px;
  }
h1 {
  text-align: center;
}
.inputForm {
  box-shadow: 0px 6px 4px 0px rgb(109, 114, 118);
}
</style>
