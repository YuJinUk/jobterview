<template>
    <div>
        <div class="totalForm">
            <div id="joinForm">
                <h1 style="color:#ffffff; text-align:center;"><b>회원가입</b></h1>
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" :disabled="!duplicateEmail" id="email" style="border-radius: 5px; margin-right: 10px;" v-model="email" placeholder="이메일"/>
                        <div><button class="check"  @click="emailCheck()">중복확인</button>
                        </div></div>
                </div>
                <div class="form-group">
                <div class="input-group">
                    <input type="text" :disabled="!duplicateNickname" id="nickname" style="border-radius: 5px; margin-right: 10px;" v-model="nickname" placeholder="닉네임">
                    <div><button class="check"  @click="nicknameCheck()">중복확인</button></div></div>
                </div>
                <div class="form-group">
                    <label for="password"></label>
                    <input type="password" id="password" style="border-radius: 5px;" v-model="password" placeholder="비밀번호">
                </div>
                <div class="form-group">
                    <label for="passwordCheck"></label>
                    <input type="password" id="passwordCheck" style="border-radius: 5px;" v-model="passwordCheck" placeholder="비밀번호 확인">
                    <span style="color:#ffffff; font-size:small" v-if="passwordCheck!=password">비밀번호와 일치하지 않습니다.</span>
                </div>
                <div style="display: flex; justify-content: center; align-items: center;"> 
                    <button class="button col-3" @click="submit" style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">가입완료</button>
                </div>
            </div>
        </div>

    </div>
</template>
<script>
import { join } from "@/api/joinApi";
import { checkEmail, checkNickname } from "@/api/joinApi";

export default {
    name:'joinForm',
    data(){
        return {
            email:"",
            password:"",
            passwordCheck:"",
            nickname:"",
            CheckPassword:true,
            duplicateEmail:true,
            duplicateNickname:true,  
        };
    },
    methods: {
        async emailCheck(){
            await checkEmail(this.email,(response)=>{
                if(response.data==1)
               {
                alert("사용가능한 이메일입니다.");
                    this.duplicateEmail=false;
                }
            console.log(response);
           },            (error) => {
            alert("중복된 이메일입니다.")
                console.log(error);
            });

        },
        async nicknameCheck(){
            await checkNickname(this.nickname,(response)=>{
                if(response.data==1)
               {
                alert("사용가능한 닉네임입니다.");
                    this.duplicateNickname=false;
                }
            console.log(response);
           },            (error) => {
            alert("중복된 닉네임입니다.")
                console.log(error);
            });

        },

        async submit(){
            if(!this.email || !this.password|| !this.passwordCheck|| !this.nickname){
                alert("모든 항목을 입력해주세요.");
            }
            else if(this.passwordCheck!=this.password){
                alert("비밀번호를 확인해주세요.");
            }
            else if(this.duplicateEmail & this.duplicateNickname){
                alert("이메일과 닉네임 중복체크 부탁드립니다.");
            }
            else if(this.duplicateEmail){
                alert("이메일 중복체크 부탁드립니다.");
            }
            else if (this.duplicateNickname){
                alert("닉네임 중복체크 부탁드립니다.");
            }
            
            else{
            let member = {
                email: this.email,
                password: this.password,
                nickname: this.nickname,};
                await join(member, (response) => {
                console.log(response);
                alert("등록하신 메일로 인증메일이 전송 되었습니다.")
                this.$router.push({name:'Home'});
            },
            (error) => {
                console.log(error);
            });

            
 

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

.check{
    background-color:#2c3e50;
    color:#ffffff; 
    border-radius: 8px; 
    border-color:#ffffff;
}

.input-group{
    margin-left:72%;
}


</style>