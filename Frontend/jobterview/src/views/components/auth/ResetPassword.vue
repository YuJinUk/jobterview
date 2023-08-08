<template>
    <div>
        <div class="totalForm">
            <div id="joinForm">
                <h1 style="color:#ffffff; text-align:center;"><b>비밀번호 변경</b></h1>
                <div class="form-group">
                    <label for="password"></label>
                    <input type="password" id="password" style="border-radius: 5px;" v-model="password" placeholder="새 비밀번호" @input="passwordLengthCheck" >
                    <span style="color:#ffffff; font-size:small" v-if="!passwordLength">8~15자로 입력해주세요.</span>
                </div>
                <div class="form-group">
                    <label for="passwordCheck"></label>
                    <input type="password" id="passwordCheck" style="border-radius: 5px;" v-model="passwordCheck"
                        placeholder="비밀번호 확인">
                    <span style="color:#ffffff; font-size:small" v-if="passwordCheck != password">비밀번호와 일치하지 않습니다.</span>
                </div>
                <div style="display: flex; justify-content: center; align-items: center;">
                    <button class="button col-3" @click="submit"
                        style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">변경하기</button>
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
                };
                await resetPassword(member, () => {         
                    alert("새로운 비밀번호로 로그인해주세요.");
                    router.push({ name: 'Home' });
                },
                    (error) => {
                        alert("현재 비밀번호가 일치하지 않습니다.")
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
<style>
.totalForm {
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 600px;
    background-color: #0F4471;
}
#joinForm {
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

.check {
    background-color: #2c3e50;
    color: #ffffff;
    border-radius: 8px;
    border-color: #ffffff;
}

.input-group {
    margin-left: 72%;
}</style>
