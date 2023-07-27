<template>
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
        <a class="navbar-brand" href="#">JOBTERVIEW</a>
        <!-- 로그인 안 했을 때 -->
        <!-- <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <div class="container-login navbar-light">
                    <button type="button" class="btn btn-link" id="login">로그인</button>
                </div>
                <div class="container-register navbar-light">
                    <button type="button" class="btn btn-link" id="register">회원가입</button>
                </div>
            </ul>
        </div> -->
        <!-- 로그인 했을 때 -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <p class="  login-nickname">이상우 님</p>
                </li>
                    <div class="dropdown">
                    <i class="bi bi-caret-down-fill" @click="toggleDropdown"></i>
                    <div class="dropdown-menu dropdown-menu-left" :class="{ 'show': isDropdownOpen }"
                        aria-labelledby="dropdownIcon">
                        <a class="dropdown-item" href="#">비밀번호 변경</a>
                        <a class="dropdown-item" href="#">회원 탈퇴</a>
                    </div>
                </div>
                <li class="nav-item"><i class="bi bi-heart-fill" style="font-size: 22px; margin-right: 20px;"></i></li>
                <li class="nav-item"><i class="bi bi-people-fill" style="font-size: 22px; margin-right: 20px;"></i></li>

            </ul>
        </div>

    </nav>
</template>

<script>

export default {
    name: 'NavBar',
    components: {

    },
    data() {
        return {
            isDropdownOpen: false
        };
    },
    methods: {
        toggleDropdown() {
            this.isDropdownOpen = !this.isDropdownOpen;
        },
        closeDropdown(event) {
            const target = event.target;
            if (!target.closest(".dropdown")) {
                this.isDropdownOpen = false;
            }
        }
    },
    mounted() {
        window.addEventListener("click", this.closeDropdown);
    },
    beforeUnmount() {
        window.removeEventListener("click", this.closeDropdown);
    }
}

</script>

<style>
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
    margin-right:30px;
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