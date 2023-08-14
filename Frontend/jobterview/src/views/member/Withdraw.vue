<template>
    <div>
      <div style="margin-top:60px ">
      <h1 style="color: #000000"><b>회원 탈퇴</b></h1>
    </div>
      <div class="totalForm">
        <div class="withdrawForm">
          <div class="withdrawForm-group">
            <label for="password" ></label>
            <input
              class="inputForm"
              type="password"
              id="password"
              style="border-radius: 5px; height: 50px"
              v-model="password"
              placeholder="  비밀번호"
              size="40"
            />
            <span style="color:#ffffff; font-size:small" v-if="!passwordLength">8~15자로 입력해주세요.</span>
          </div>
          <br>
          <br/>
          <div style="  display: flex;
          align-items: center;
          flex-direction: column;
          justify-content: center;
          width: 100%; ">               
                <button class="withdraw" @click="submit">탈퇴하기</button>
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
                
                if(confirm("탈퇴하시겠습니까?")){
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
                  else{
                    return;
                  }
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
    background-color: #ffffff;
}
.withdrawForm {
    display: block;
    align-items: center;
    justify-content: center;
    width: 700px;
    height: 400px;
}

.withdrawForm-group {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    padding: 15px;
    width: 100%;
}

.withdraw {
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
