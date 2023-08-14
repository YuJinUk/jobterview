<template>
    <div>
        <div style="margin-top:60px ">
        <h1 style="color: #000000"><b>비밀번호 변경</b></h1>
      </div>
        <div class="totalForm">
          <div class="resetPasswordForm">
            <div class="resetPasswordForm-group">
              <label for="password" ></label>
              <input
                class="inputForm"
                type="password"
                id="password"
                style="border-radius: 5px; height: 50px"
                v-model="password"
                placeholder="  새 비밀번호"
                @input="passwordLengthCheck"
                size="40"
              />
              <span style="color:#EC255A; font-size:medium " v-if="!passwordLength">8~15자로 입력해주세요.</span>
            </div>
            <div class="resetPasswordForm-group">
              <label for="passwordCheck"></label>
              <input
                class="inputForm"
                type="password"
                id="passwordCheck"
                style="border-radius: 5px; height: 50px" 
                v-model="passwordCheck"
                placeholder="  비밀번호 확인"
                size="40"
              />
              <span style="color:#EC255A; font-size:medium " v-if="passwordCheck != password">비밀번호와 일치하지 않습니다.</span>
            </div>
            <br>
            <br/>
            <div style="  display: flex;
            align-items: center;
            flex-direction: column;
            justify-content: center;
            width: 100%; ">               
                  <button class="resetPassword" @click="submit">변경하기</button>
            </div>
          </div>
        </div>
      </div>
</template>
<script>
import { resetPassword } from "@/api/memberApi";
import { useRoute,useRouter } from 'vue-router';
import { ref } from 'vue';
export default {
    name: 'resetForm',
    setup() {
        const password=ref('');
        const passwordCheck=ref('');
        const passwordLength=ref(true);
        const route = useRoute(); 
        const router = useRouter();
        const passwordLengthCheck=() =>{
            if(password.value.length>15||password.value.length<8){
                passwordLength.value=false;
            }
            else{
                passwordLength.value=true;
            }
        }
        const submit= async()=>{
            if (password.value != passwordCheck.value) {
                alert("비밀번호를 확인해주세요.");
            }
            else if(!passwordLength.value){
                alert("비밀번호를 8~15자로 설정해주세요.");
            }
            else if (password.value.includes(" ")){
                alert("비밀번호에 공백이 들어갈 수 없습니다.")
            }
            else {
                const member = {    
                    nickname: "",
                    email: route.params.email,
                    password:"",
                    newPassword:password.value,
                    code: route.params.code,
                };
                await resetPassword(member, () => {         
                    alert("새로운 비밀번호로 로그인해주세요.");
                    router.push({ name: 'Home' });
                },
                    (error) => {
                        alert("최신 메일의 링크로 접속해주세요.")
                        console.log(error);
                    });
            } 
        }
        return{
            password,
            passwordCheck,
            passwordLength,
            passwordLengthCheck,
            submit,
        };
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
.resetPasswordForm {
    display: block;
    align-items: center;
    justify-content: center;
    width: 700px;
    height: 400px;
}

.resetPasswordForm-group {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    padding: 15px;
    width: 100%;
}

.resetPassword {
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
