<template>
  <div class="joinHeader">
    <div style="margin-top: 60px">
      <h1 style="color: #000000"><b>회원가입</b></h1>
    </div>

    <div class="totalForm">
      <div class="joinForm">
        <div class="joinForm-group">
          <div class="duplCheck">
            <input
              class="inputForm"
              type="email"
              id="email"
              style="border-radius: 5px; height: 50px"
              v-model="email"
              placeholder="이메일"
              size="40"
              @input="testValidEmail"
            />
            <button class="check" @click="emailCheck()">중복확인</button>
          </div>
          <span style="color: #ec255a; font-size: medium" v-if="!isValidEmail">
            이메일 형식에 맞지 않습니다.
          </span>
        </div>

        <div class="joinForm-group">
          <div class="duplCheck">
            <input
              class="inputForm"
              type="text"
              id="nickname"
              style="border-radius: 5px; height: 50px"
              v-model="nickname"
              placeholder="닉네임"
              size="40"
              @input="testValidNickname"
            />
            <button class="check" @click="nicknameCheck()">중복확인</button>
          </div>
        </div>

        <div class="joinForm-group">
          <input
            class="inputForm"
            type="password"
            id="password"
            style="border-radius: 5px; height: 50px"
            v-model="password"
            placeholder="비밀번호"
            @input="passwordLengthCheck"
            size="40"
          />
          <span style="color:#EC255A font-size:medium" v-if="!passwordLength">
            8~15자로 입력해주세요.
          </span>
        </div>

        <div class="joinForm-group">
          <input
            class="inputForm"
            type="password"
            id="passwordCheck"
            style="border-radius: 5px; height: 50px"
            v-model="passwordCheck"
            placeholder="비밀번호 확인"
            size="40"
          />
          <span
            style="color: #ec255a; font-size: medium"
            v-if="passwordCheck != password"
            >비밀번호와 일치하지 않습니다.</span
          >
        </div>

        <div
          style="
            display: flex;
            align-items: center;
            flex-direction: column;
            justify-content: center;
            width: 100%;
          "
          class="joinBox"
        >
          <button class="joinButton" @click="submit">가입완료</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  join,
  reJoin,
  checkEmail,
  checkNickname,
  sendEmail,
} from "@/api/joinApi";
export default {
  name: "joinForm",
  data() {
    return {
      email: "",
      password: "",
      passwordCheck: "",
      nickname: "",
      CheckPassword: true,
      duplicateEmail: 0,
      duplicateNickname: true,
      isValidEmail: true,
      passwordLength: true,
    };
  },
  methods: {
    //닉네임 변경시
    testValidNickname() {
      this.duplicateNickname = true;
    },

    // 이메일 검사
    testValidEmail() {
      //이메일 중복체크 필요하게
      this.duplicateEmail = 0;

        const com= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.com$/i;
        const cokr= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.co.kr$/i;
        const net= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.net$/i;
        const ackr= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*[0-9a-zA-Z]([-_.]?[0-9a-zA-Z]).ac.kr$/i;

          if(this.email.match(com)!==null||this.email.match(cokr)!=null||this.email.match(net)!=null||this.email.match(ackr)!=null){
          this.isValidEmail = true;}
          else{
              this.isValidEmail=false;
          }
      },
    // 비밀번호 길이 검사
    passwordLengthCheck() {
      if (this.password.length > 15 || this.password.length < 8) {
        this.passwordLength = false;
      } else {
        this.passwordLength = true;
      }
    },
    // 이메일 중복 체크 및 공백 확인
    async emailCheck() {
      await checkEmail(this.email, (response) => {
        if (this.email.includes(" ")) {
          alert("이메일에 공백을 사용할 수 없습니다.");
        } else if (!this.email) {
          alert("이메일을 입력해주세요.");
        } else if (this.isValidEmail == false) {
          alert("이메일 형식에 맞지 않습니다.");
        } else {
          if (response.data == 1) {
            alert("사용가능한 이메일입니다.");
            this.duplicateEmail = 1;
            return;
          } else if (response.data == 2) {
            this.duplicateEmail = 2;
          } else {
            alert("중복된 이메일입니다");
          }
          console.log(response);
        }
        (error) => {
          alert("오류가 발생했습니다.");
          console.log(error);
        };
      });
    },
    // 닉네임 중복 체크 및 공백 확인
    async nicknameCheck() {
      await checkNickname(this.nickname, (response) => {
        if (this.nickname.includes(" ")) {
          alert("닉네임에 공백을 사용할 수 없습니다.");
        } else if (!this.nickname) {
          alert("닉네임을 입력해주세요.");
        } else {
          if (response.data == 1) {
            alert("사용가능한 닉네임입니다.");
            this.duplicateNickname = false;
          } else {
            alert("사용할 수 없는 닉네임입니다.");
            console.log(response);
          }
        }
        (error) => {
          alert("오류가 발생했습니다.");
          console.log(error);
        };
      });
    },
    async submit() {
      if (
        !this.email ||
        !this.password ||
        !this.passwordCheck ||
        !this.nickname
      ) {
        alert("모든 항목을 입력해주세요.");
      } else if (this.passwordCheck != this.password) {
        alert("비밀번호를 확인해주세요.");
      } else if ((this.duplicateEmail === 0) & this.duplicateNickname) {
        alert("이메일과 닉네임 중복체크 부탁드립니다.");
      } else if (this.duplicateEmail === 0) {
        alert("이메일 중복체크 부탁드립니다.");
      } else if (this.duplicateNickname) {
        alert("닉네임 중복체크 부탁드립니다.");
      } else if (!this.passwordLength) {
        alert("비밀번호를 8~15자로 설정해주세요.");
      } else if (!this.isValidEmail) {
        alert("이메일 형식에 맞춰주세요.");
      } else if (this.email.includes(" ")) {
        alert("이메일에 공백이 들어갈 수 없습니다.");
      } else if (this.nickname.includes(" ")) {
        alert("닉네임에 공백이 들어갈 수 없습니다.");
      } else if (this.password.includes(" ")) {
        alert("비밀번호에 공백이 들어갈 수 없습니다.");
      } else {
        let member = {
          email: this.email,
          password: this.password,
          nickname: this.nickname,
        };
        if (this.duplicateEmail == 1) {
          await join(
            member,
            () => {
              sendEmail(
                member.email,
                () => {},
                (error) => {
                  console.log(error);
                }
              );
              alert("등록하신 메일로 인증메일이 전송 되었습니다.");
              this.$router.push({ name: "Home" });
            },
            (error) => {
              console.log(error);
            }
          );
        } else {
          await reJoin(
            member,
            () => {
              sendEmail(
                member.email,
                () => {},
                (error) => {
                  console.log(error);
                }
              );
              alert("등록하신 메일로 인증메일이 전송 되었습니다.");
              this.$router.push({ name: "Home" });
            },
            (error) => {
              console.log(error);
            }
          );
        }
      }
    },
  },
};
</script>
<style scoped>
.totalForm {
  display: flex;
  position: absolute;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 500px;
  background-color: #ffffff;
}
.joinForm {
  /* display: block; */
  /* align-items: center; */
  /* justify-content: center; */
  width: 700px;
  height: 400px;
}

.joinForm-group {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  padding: 15px;
  width: 100%;
}

.joinBox {
  margin-top: 3%;
}

.joinButton {
  background-color: #083358;
  pointer-events: all;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-size: 14px;
  font-weight: 500;
  width: 369.6px;
  height: 50px;
  transition: all 0.15s ease-in-out;
  margin-bottom: 15px;
}
h1 {
  text-align: center;
}

.check {
  background-color: #2c3e50;
  color: #ffffff;
  border-radius: 8px;
  border-color: #ffffff;

  position: relative;
  right: 18%;
}

.duplCheck {
  margin-left: 11%;
}
.inputForm {
  border: 1px solid #083358;
  box-shadow: 0 3px 3px rgba(0,0,0,0.2);
  padding-left: 15px;
}
</style>
