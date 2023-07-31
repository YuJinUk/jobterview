<template>
    <div>
        <div class="totalForm">
            <div id="joinForm">
                <h1 style="color:#ffffff"><b>회원가입</b></h1>
                <div class="form-group">
                    <label for="email"></label>
                    <input type="text" id="email" style="border-radius: 5px;" v-model="email" placeholder="이메일">
                </div>
                <div class="form-group">
                    <label for="password"></label>
                    <input type="password" id="password" style="border-radius: 5px;" v-model="password" placeholder="비밀번호">
                </div>
                <div class="form-group">
                    <label for="passwordCheck"></label>
                    <input type="password" id="passwordCheck" style="border-radius: 5px;" v-model="passwordCheck" placeholder="비밀번호 확인">
                    <span class="badge badge-danger mt-1" v-if="passwordCheck">비밀번호와 일치하지 않습니다.</span>
                </div>
                <div class="form-group">
                    <label for="nickname"></label>
                    <input type="text" id="nickname" style="border-radius: 5px;" v-model="nickname" placeholder="닉네임">
                </div>
                <button class="button col-8" @click="submit" style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">가입완료</button>
            </div>

        </div>
    </div>
</template>
<script>
import { join } from "@/api/joinApi";
export default {
    name:'joinForm',
    data(){
        return {
            email:"",
            password:"",
            passwordCheck:"",
            nickname:""
        };
    },
    methods: {
        async submit(){
            if(!this.email || !this.password|| !this.passwordCheck|| !this.nickname){
                alert("모든 항목을 입력해주세요.");
            }
            else if(this.passwordCheck!=this.password){
                alert("비밀번호를 확인해주세요.");
            }

            
            else{
              
            let member = {
                email: this.email,
                password: this.password,
                nickname: this.nickname,
            };
            }
            async function doJoin(){
            await join(member, (response) => {
                console.log(response);
            },
            (error) => {
                console.log(error);
            });
            }
   


        }

        }

        
  
}}
</script>
<style>
.totalForm{
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 600px;
    background-color: #2c3e50;

}
#joinForm {
    display: block;
    align-items: center;
    justify-content: center;
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