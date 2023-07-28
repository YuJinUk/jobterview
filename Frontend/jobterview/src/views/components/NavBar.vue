<template>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
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
                <svg class="vector" width="32" height="32" viewBox="0 0 40 40" fill="none"
                    xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M20 40L17.1 37.1226C6.8 26.9428 0 20.2289 0 11.9891C0 5.2752 4.84 0 11 0C14.48 0 17.82 1.76567 20 4.55586C22.18 1.76567 25.52 0 29 0C35.16 0 40 5.2752 40 11.9891C40 20.2289 33.2 26.9428 22.9 37.1444L20 40Z"
                        fill="black" />
                </svg>
                <font-awesome-icon :icon="['faUserSecret', 'users']" />
            </ul>
        </div>
    </nav>
</template>

<script>

export default {
    name: 'NavBar',
    components: {
    }
}

</script>

<style>
.navbar {
    border-style: outset;
}

.navbar .navbar-brand {
    color: #0f4471;
    font: 700 24px/24px "Lato", sans-serif;
    margin-left: 25px;
}

.navbar .container-login {
    padding: 12px 0px 12px 0px;
    margin-left: auto;
    margin-right: 25px;
}

.navbar .container-register {
    background: #0f4471;
    padding: 12px 18px 12px 18px;
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