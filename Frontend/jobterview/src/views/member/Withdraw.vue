<template>
    <div>
        <div class="totalForm">
            <div id="joinForm">
                <h1 style="color:#ffffff; text-align:center;"><b>회원 탈퇴</b></h1>
                <div class="form-group">
                    <label for="password"></label>
                    <input type="password" id="password" style="border-radius: 5px;" v-model="password" placeholder="비밀번호">
                </div>
                <div style="display: flex; justify-content: center; align-items: center;">
                    <button class="button col-3" @click="submit"
                        style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">탈퇴하기</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { useStore } from 'vuex';
import { ref,computed } from 'vue';
import { withdrawMember } from "@/api/memberApi";
import { useRouter } from 'vue-router';
export default {
    name: 'deleteForm',
    setup() {
        const store = useStore();
        const router = useRouter();
        const loginMemberNickname = computed(()=>store.state.loginStore.loginNickname);
        const password=ref("");
        const submit= async()=>{
                const member = {
                    nickname: loginMemberNickname.value,
                    password: password.value,
                };
                
                await withdrawMember(member, () => {         
                    alert("탈퇴가 완료되었습니다.");
                    store.commit("loginStore/User_Logout");
                    router.push({ name: 'Home' });
                },
                    (error) => {
                        alert("현재 비밀번호가 일치하지 않습니다.")
                        console.log(error);
                    });
        }
        return{
            loginMemberNickname,
            password,
            withdrawMember,
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
