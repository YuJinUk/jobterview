<!-- <template>
    <div>
        <div class="totalForm">
            <div id="joinForm">
                <h1 style="color:#ffffff; text-align:center;"><b>비밀번호 확인</b></h1>
                <div class="form-group">
                    <label for="password"></label>
                    <input type="password" id="password" style="border-radius: 5px;" v-model="password" placeholder="비밀번호" @input="passwordLengthCheck" >
                </div>
                <div style="display: flex; justify-content: center; align-items: center;">
                    <button class="button col-3" @click="submit"
                        style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">다음</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { useStore } from 'vuex';
import { ref,computed } from 'vue';
export default {
    name: 'updateForm',
    data(){
        return {
            password:"",
        }
    }
    methods : {

    }
    setup() {
        const store = useStore();
        const password=ref('');
        const loginMemberNickname = computed(()=>store.state.loginStore.loginNickname);
        const submit= async()=>{
            //if(password.value !=loginMember.password){
                alert("비밀번호를 확인해주세요.")
            }
            else{    
                    this.$router.push({ name: 'UpdatePassword' });
              
        }}
        return{
            password,
            submit,
        };
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
}</style> -->
