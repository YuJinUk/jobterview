<template>
  <nav class="navbar navbar-expand-sm navbar-light bg-light">
    <a class="navbar-brand" href="#" @click="toMain()">JOBTERVIEW</a>

    <!-- 로그인 했을 때 -->

    <div v-if=getUser class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <p class="  login-nickname">{{ loginNickname }}</p>
        </li>
        <div class="dropdown">
          <i class="bi bi-caret-down-fill" @click="toggleDropdown"></i>
          <div v-if=getSocail class="dropdown-menu dropdown-menu-left" :class="{ 'show': isDropdownOpen }"
            aria-labelledby="dropdownIcon">
            <a class="dropdown-item" @click="logout">로그아웃</a>
          </div>
          <div v-else class="dropdown-menu dropdown-menu-left" :class="{ 'show': isDropdownOpen }"
            aria-labelledby="dropdownIcon">
            <a class="dropdown-item" @click="updatePassword">비밀번호 변경</a>
            <a class="dropdown-item" @click="withdraw">회원 탈퇴</a>
            <a class="dropdown-item" @click="logout">로그아웃</a>
          </div>
        </div>
        <li v-if="getIsAdmin" class="nav-item"><i class="bi bi-hammer" style="font-size: 22px; margin-right: 20px;"
            @click="toAdmin()"></i></li>
        <li class="nav-item"><i class="bi bi-envelope-fill" style="font-size: 22px; margin-right: 20px;"
            @click="toMessage()"></i></li>
        <li class="nav-item"><i class="bi bi-people-fill" style="font-size: 22px; margin-right: 20px;"
            @click="toMemberList()"></i></li>
      </ul>
    </div>
    <!-- 로그인 안 했을 때 -->

    <div v-else class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <div class="container-login navbar-light">
          <button type="button" class="btn btn-link" id="login" @click="toLogin()">
            로그인
          </button>
        </div>
        <div class="container-register navbar-light">
          <button type="button" class="btn btn-link" id="register" @click="toJoin()">
            회원가입
          </button>
        </div>
      </ul>
    </div>
  </nav>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  name: "NavBar",
  components: {},
  data() {
    return {
      isDropdownOpen: false,
      bookmarkVisible: false,
    };
  },
  methods: {
    toggleDropdown() {
      this.isDropdownOpen = !this.isDropdownOpen;
      this.bookmarkVisible = false;
    },
    closeDropdown(event) {
      const target = event.target;
      if (!target.closest(".dropdown")) {
        this.isDropdownOpen = false;
      }
    },
    toggleBookmark() {
      this.bookmarkVisible = !this.bookmarkVisible;
      this.isDropdownOpen = false;
    },
    toMessage() {
      this.$router.push({ name: "MessageList" });
    },
    updatePassword() {
      this.$router.push({ name: "UpdatePassword" });
    },
    toMemberList() {
      this.$router.push({ name: "MemberList" });
    },
    toAdmin() {
      this.$router.push({ name: "AdminMember" });
    },
    toMain() {
      this.$router.push({ name: "Home" });
    },
    toLogin() {
      this.$router.push({ name: "LoginMember" });
    },
    toJoin() {
      this.$router.push({ name: "Join" });
    },
    withdraw() {
      this.$router.push({ name: "WithdrawMember" });
    },
    logout() {
      this.$store.dispatch("loginStore/UserLogout");
    },
  },
  async mounted() {
    //어드민인지 확인
    window.addEventListener("click", this.closeDropdown);
  },
  beforeUnmount() {
    window.removeEventListener("click", this.closeDropdown);
  },
  computed: {
    ...mapGetters(["loginStore/getLogin"]),
    ...mapGetters(["loginStore/getSocial"]),
    ...mapGetters(["loginStore/getIsAdmin"]),
    ...mapState("loginStore", ["isLogin"]),
    ...mapState("loginStore", ["isSocial"]),
    ...mapState("loginStore", ["isAdmin"]),
    ...mapState("loginStore", ["loginNickname"]),

    getUser() {
      if (this.isLogin) {
        return true;
      } else {
        return false;
      }
    },
    getSocail() {
      if (this.isSocial) {
        return true;
      } else {
        return false;
      }
    },
    getIsAdmin() {
      if (this.isAdmin) {
        return true;
      } else {
        return false;
      }
    },
  },
}
</script>

<style scoped>
.closeButton {
  background-color: #fff;
  color: black;
  border: 1px solid #083358;
  border-radius: 5px;

  font-size: 14px;
  font-weight: 500;

  width: 50px;
  height: 25px;

  margin: 13px 5% 5px 74%;

  transition: all 0.15s ease-in-out;
}

.bookmark {
  position: relative;
  left: 74%;
  width: 300px;
}

.bookmark li {
  width: 90%;
  margin: 0 auto;
  border-bottom: 1px solid #083358;
}

.room {
  display: flex;
  margin: 5px 0;
}

.room p {
  margin: 0 0 3px 0;
}

.navbar {
  border-style: outset;
}

.login-nickname {
  display: flex;
  justify-content: center;
  margin-top: 4px;
  margin-bottom: 0px;
}

.navbar .dropdown {
  margin-top: 4px;
  margin-bottom: 0px;
  margin-right: 30px;
}

.login-nickname {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

.nav-item {
  margin-right: 20px;
  text-align: center;
}

.navbar .navbar-brand {
  color: #0f4471;
  font: 700 24px/24px "Lato", sans-serif;
  margin-left: 25px;
}

.navbar .container-login {
  padding: 6px 0px 6px 0px;
  margin-left: auto;
  margin-right: 25px;
}

.navbar .container-register {
  background: #0f4471;
  padding: 6px 12px 6px 12px;
  margin-left: auto;
  margin-right: 25px;
}

.navbar #login {
  color: #0f4471;
  font: 700 16px/18px "Mulish", sans-serif;
  text-decoration: none !important;
}

.navbar #register {
  color: var(--light-white, #ffffff);
  font: 700 16px/18px "Mulish", sans-serif;
  text-decoration: none !important;
}
</style>
