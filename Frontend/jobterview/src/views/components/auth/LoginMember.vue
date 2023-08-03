<template>
    <div>
        <div class="logintotalForm">
            <div id="loginForm">
                <br>
                <h1 style="color:#ffffff"><b>로그인</b></h1>
                <div class="loginform-group">
                    <label for="email"></label>
                    <input type="text" id="email" style="border-radius: 5px; height: 50px;" v-model="email" placeholder="이메일" size="70">
                </div>
                <div class="loginform-group">
                    <label for="password"></label>
                    <input type="password" id="password" style="border-radius: 5px; height: 50px;" v-model="password" placeholder="비밀번호" size="70">
                </div>

                <div style="display: flex; justify-content: center; align-items: center;">
                    <a href="http://localhost:8080/oauth2/authorization/google">
                    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAABUFBMVEX////qQzU0qFNChfT7vAWmwfk2f/QoevP5+/8fd/P7uQD/vQDqPi/7uADqQDEspk7pLRn1tbHpOyvpNCLpMB0lpEkXokL8wQD4y8j98/I7gvTY7Nzw+PJDg/v/+/voKRHsW1DxioPubmXpOTf+9eD80nVbk/VNi/S/4Mfk8uex2br1sKzrTUDvfnftZFr86OfoIQD3w8D51tTzpJ/ymJP+7Mjx9v7K2vv/+/Lo7/6Wt/g+jtBiuHczqkI+q1rP6NVXtG7rVUnxkozwg3v73tz5yZzsVjD936HwcSn0kR3957j4rA/7xDzuZCzuc2vygiP81ob2nRj8ylrxjnv7wDC3zfr+8NX8x098p/f93JXU16AAa/Lc5v1fqkTZuB6xsy+BxJGBrkCZzqXCtSmKr/eUsDr0zWk7l6w3oIGc0KhAi9w8k7s5nJU2o20zqzqgwezt4P8pAAAIH0lEQVR4nO2aaXfaRhSGZUy8gEBYQjIYapY6NmCWYLIXs9hu0yapm6ZJkzZLiZumpev//1ZJYIxgNBqhGY3wuc+39uQgPb4z951FggAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgWI33Ugd3i6cF/P9qk6/XqwVbh+mGuld3m9Gg0aqVcvLuYQiy6IYHyOKsqwkckq+1ko1eL+hF9Kpk7qsu8VXbIjrnnK9sKSWja3iSlIW7eSuEOVkvHi4bJK7zbqsiLa1m7dU5PrWEs3Ks4KuR2w3kVQKZ7zfnIxU/pbsVm+EfKt+xPvtnWn2c67LN1XIXD7F2wBPM58gn3w2jkGu41Ex4aF+E8dELaCNNX2apOBnICst3jIotuIL9hcUSj9wQzVdTHqcgFbE5G3eSlaaCsUCjlD6QZqNpznafitGV23y9rqkUVUYCOrkTnirjUi5X6GRkjxP87bTOaTbYqzIef6T8STJzs9QrPKOjRqjKTght8VXsHjdBeusBRMgyJbz6z5EC9e9gi22McFfsHndBc+UBVYycVE2ITlm5D0Hd6vu1qJxWUnmlGqxVjg5KdSKfSWXxByFr/CvoHDuZjsoKkq1tnVkWUSnj7ZOq/anqtwFD8n3g3FFrh/anPQ2tooycrTzHqJCg1hQTPZb2N1Bo9WfP7/iXkGhTzgJxUS96XgZsdusz5yx8hdsEUZ9ok54ft3sT0cPf8EGmaAsuzhmaV1dBfAXFIpEffTWqavLskZ+XEbuTYZwMSOLrrfmt83ZGIAK7vYJFiRKcYETpJQsBkFQOCQo4YLn1WfVAAxRIb3iXMLkou/ZCICg8KVzCYNzVL0I97effPW5g2DAL3EdeBQLh7/GKi53BQUhrLP9DUYxCL3CC49jpuKTFTtHJWC3fq55GjbZjnyLVpTPeb+hR/Zi4THb36EU49UgXBV54VkkPFH8HmGYXO42Koz6zERxPjbkAu8X9MqdWNjiOBMb8fgSfXqH5mYkbFW0xsayJ6FgHaThudgQ87zfzzN3InOK4fBVbOSW5NNJDD+hDCexIRZ5v593XiEEzdgwHXO879u9s4cU1BVjRmyIdd7v552ZrJiJjYV3vQECOQ3HinpsLPt6Tee5raARGz/wfj3v3H+BMQxH7pD+zsbDG955ycJwz36Q6ry4T2z4Zs07BywM7RuNUcLnxL+zsblKgbsMDB/hDGOP/DVce8vA8BlulEZ+9NnwHgPDT3SmISVDFq0GvWYb85T8d+gYPqQviA2LyE2fDXdeMzDElTBC3mgoGe4zMMSGxeNrb0i8oqGVhwcb1A33sIbkYUHLkH7k4w33wJC24eoDMARDl4a+dxrfDX1PCwaG2DyM+Z34TAwxgv6v2lhsgfEr72d+GzJY01xecKN55bshfUHsYWL4hc+GLFbec5eHVsgDMbiGmCNvV800sDvg8ac0dq2GfJMf2FMMfOS7OKgJ7kkUNhBdrEyDe5qIPWyLxn4mNnyzTsQa1pDFiTCumUbD73q0H/d2HafI4lQf02qi70MhLUP5cTdwRWRyM2N/+RT9JRQKSQPKj3u9Yy/IJCx00CvTaPhDyEDLUn3YBkZwde0G1WdNQE7E6Pt3pmBIvaD6sHu4acimlaInYvTX0CUq1SJ+xNaQSaNBJWI0+lvoypBqEbFhwabRCPOJqIdEaAqaM/ElbpDufKT3ICuPrRPRCIlppDa9Rx3gSshqGurDdNowaoaEBa1E60nYPrO6yWCDP+b59Aj9MCuoQ2uc7uP6DJPN4ZirbjoJCQvqgM5zXuIXpYzS0ORymE6FhHWcVmg85S7OTx+k9E8Srxht9C0hMVPFLoWnfMSWkOUgHZ8Lz4TETEMte34INikYD1Jz5TYbEjOGHa/d5gG2gqur64wWNGP2YvMhMavo7Ql3D3B9lN2+YsInVEjQrCI2KIwSMov7MRnVyVBX9DAX9x3GKNs+YzIgUVx0w7/hJMi6zxiUnQ1DkrpYLj5wmIMGbPuMyQWB4mKrm3s7joI+lFAQsgSCuqL7kfqQ4CR1x4cSCkJJI3LUBq5+tauqX3wWiBLqtCWyMkrks7Hc1v9sx787KrLbN1nIkhVRL2OnQpSNmYFm/tGO/1jFzsR1Jt/oo6iQKuqOQ6dwzJba2uWgUDt/YsroQxZOIByno7HaLtkXMtsddLSpH5PUv+wVN5ncVti8GLGg8daa1Bt25y2z3WE7pM3+rY7/tssMJpeGtnSJx+m4NKrUaV9USt2MTrdUGQ7aHVX/n4h/e/zPAbKMO8zOENEM3SmONVXNxHCzH+cSOjbWfRyjJgTr04X5FxEbfkXhFD3ybuOa+dhYY70tRJDtMFScjY0dBh8FO1NmaDgbG2yutZ0V3XcbF0zHBvONvR0Ztor/7I/LuOnbas1nRel4FBvrvkb9DGVMrlHAjA2ugroiy45qxMYBZ0E9NHoMo9+Ijf84C+oMWE5GOnc9XllgjUqIROWmhwJdRv1G9XK2TJdsm0UZNYofBninMreV9YoUjCl4RblHt4xqLzAjdEJFopcbqjTkrYMiO0AeS7hH0gbBK+CITJvCdJS0XkAyAknXq6PuR+3DI0bodVx8Pi6Bn0F5oC1WSFVr0/6Ymhmlnuay60iq1hnS/c6YMeVhD33ii9ZTOxdLU74rypV2R8Od/ZpyRvHalaCmgyPZTGXQkzSUp2S4aaHeoJJZqsGJQte8GPQ6kjaFGuq1B8PS8stNkc1my5muQSZT1v+D9/sAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAwDz/A7zEGLPvvKneAAAAAElFTkSuQmCC" 
                    alt="구글" width="50">
                    </a>
                    &nbsp;
                    &nbsp;
                    &nbsp;
                    <a href="http://localhost:8080/oauth2/authorization/kakao">
                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAqFBMVEX64QA8Hh795AA5Gh7/5wD/6gA6HB4xDR/54AA4GB4jAB8nAB/w1wA1FB+chBTbwwu3nAvRuAgtAx83Fh5SNBr12wB7YRZ0XRovBx8zEB81FR8wCx9PNBxhSBrozwCslRNrUhpJLBxCJB28og1vVhjHrwxEJhxcQhufhRFdRBuFbBVVOhuWexGpkRJmThqPdBPYvgfLsgodACB/Zhfkygb/8ABrVBpFJBsrmgknAAAJSklEQVR4nO2cZ2OqPBTHJSwRUEZpwYEDrVZw3dLe7//NHhJEmdreasA+5/eqjrb5c5KzEmi1AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/p8gDJdAXtU9pNsRCeOtcH/wPE0bYzTN8w77UOEjqXUP7qdEZuO5nhe8T9786YxVHRvjqMxs6o+2z8G41+K5x7UmQkq4CJ4M2RBVU2JZgTkjsKxk9h1blp+CRai0HlAk4pTF5p0xRJa5DCsazPPGU/iHEhnNTb27fXVU4Yq8o0FVZzYJ9vzDrErUDrVRRzS/Ju8o0nQ6T9GqfASNCOmBb0vfUJcgGdPVofEaEe+uh86/6MOwzvSl1+gFidr7teFc8y0XNaryRG+uHbmwKzg/kBfTN1ZhMyWij/FS/Yn9TqjL1UcDNaLeXP7X9ZdHkke9uvXkQa3Nk3ojfRh1umnWakTW7p8daDnsYOdydcs6g3Tfvqk+jO3rzZG4mPZvLjByqrNF3cKOIO/VvIPAyOHMvEasRbSSv5OBfgdWXjVAYnss30kfRh636xaIxsy9LIgRmHHNVkRj4yZpTLVEeVOrRG5h3NOCBKNOd8P1/NvG+TKk4b62uIhC/5aZWhXqsrZag1sPKAhkmMFzTUbkFveME2mMRS0SUW94/0UYIw17dczTqJygJJBhnJ1FXyDSv9Uu/BkCo9M3IuffJ90ux/SpK0SHDkWBUYLqUXc2zzRC4RlzTnklIu+uCXcR4XVBd55aQdaRCmyB0xUQCtei+E5UC16RSNmdRvlaZkSi9FRgFifloj2cGpkmhylPp/mKZKD6duqaSYZs564C++TSNCKnZdIZY8MX+djMokHagcXzvXlq9OYo/OAWw4xEIfjgW+8n56yOvL02y10EY0XT1/CTtFXY19Kra72rzGCH97A5NyWIpGBcJl3ovyh4XgyPZlPnLod4LSuQMSc8RYWW/JX/3RWFaVwWcN75F2T8FrdJJ+1iF/cqlOf4sjkjPB+R6+eNSNGGyDMyc4xBR44fxyjPqhPEbZb0uu0oWLKW/guJQhKATJ90u1FvmldIsRTmAjHzvwernksg7s6Kfw43osDgOI0tkvK9VxT2l3GWjZ7zaa8T0DMiesl1gE1/hPHx4NB+Tl4sBUEaKSi6HPg3vFniGy8rlF4PxIKtbqH2NF+o2RCFb/m6iZUwHaJQH6rkFR55dNWVJZ5dyjbxlBcVSgxp5SNlZzB5pDdqtX6koTw+y0eFyaedaEjo8LrG756m6SWF0tQjTovbiUwBdkitwECL1/KULVF4/FSYcaTfuMWxQE+86QWFDhM7k6ynTRCm1BK3KCktJ6dwsMF+Zq6SlPIjycsuKLQXxIJWUL6TZWq0XA2nVVT32VkqvEYvUfjKit1Wyv9eULghAvlVxZ936CkcV+wWZm3ovCtx6IxTlr19TeGCBE/ecyrKFnvcMIUSDob8MvKpJn5fOYaYSoXxtjbSKo+rNEghGSJLomMbKzHwgmxtpCsKY7TKyrNBCskQ1Wecf5L8ThrxuO8Rp2HXFLbXVTvmBj2FWkUHI6NQxskJ906++oeoijPrSwrjWLGs+PM0fWlFKzg9SwWTFAhvjhkh46t/jHIXFFokJUX7ilazQC31/lLEd9Y43Ovz7SRihKtXpPy5ojCYkDoejUuNSLFVg/Sny1kbViiSFAtxccFPphdP0tlKhVZg2HEzhuuWLUXW31NT6I7Kp1FqlvYnxbYKpw0uKSSZd7zU2m8lVpRG9Do1XL56KtrQKdmcRj0cCC4qFNR4nurT4jXs06ueChVwUWF5HdDGBcbl+lCdKETiuZ5MfYtiBVyxc3iepf24RZ1qvZGyD3eXEoVCTE4hI8Vepx0UCkQSfmhhXVYoMAPS+rMmLwlz4k3DKIcjCpFmSDGymFUoMGTbHrWf8itBptkv5UelG09nhTIeMvI6ZoIzw3UDv3ZihS2U2LYdmNlOlOTESzHMldl9qt1ETivNrE6z1NwSPemmKK73SaURKzyBHXNGISOu4+i4yi52mVpGQ4YV/i2LiCeFpC2G3G3KIYpx0O9ECrOzzdo5pIA8FR8Co+FvcNk6O2m90qIdlFWpYpfH9mUZc8uhaIhpf2hOrOitg4wr//RQkTs3owIy+qyX9AZYSedaCGULYTGge8KtPHETmINiLd6iNWprrnWYZ8L2rBta+60ZpSYL1zrj7qJvsYHb7r2fZqXp64qrDdP/gfruWuQmy3wNO91OSKwWZqPPXP7MMstP4jyiL31OEj7j7MUZfaZPH/WH29Es8+vqC119LTLfShBM87hAJbOwUpO3WMk8I5V+nTWl7ByR6Z834d5onlSgGypi0KHYlb4fRg2nTVrtLr0TQ+qKvr5jCkYHijsWGTiP1pGajlbT8Ut+XV5E3ZrBjr6biUFuaVC8Neqc6imMrMSyUvzWSFNq7ZkSuMNt7ji8AMsear39iR/f+QwmO9PqWoRH2hV7fbfC6Narr4U72fnzWTeEjfd06pbYvXrq7p8Fzqie9KqkrbH38agSW1ekz8MfZvc4TqtODzU7mTPcYX777GYwr+9uoCLIXXdufKdzZ1dfJlMG+hjf9B6ovq817vkYqLe++rSdr8LK61pukrkCsrS/9i00ssZfz2qgwAjO6g5/9GQTjOBMN1aDXEwOXu9WHOr7Ko6w0xsTI8pA1ucPSkZpYDz3GjpBE1Avfzr7ywgq46+a/8gvrurY4lXz2cy7FzZeH24xfj+5Edj+4M9y01Me4uF77vx7zwARJEec+S+b8FEe1obC8mN3rFR2p5M6kJnRerPooQeR1yrciJFIMfyl2ZFl2TDsQYRhyHKnY/vvq0XoKo/1nE++7KBNFMJbPI/C/UHTxqvVaqN5B91FPE+eY1r3kL8JV9KXGszjZwWlntD6uM9nRWGhz983giY97+mncKvcXQSsOdIfIMZ9HZS7OVhiur/JgIX9NtZm9PZvMmD+uF1/2v1l+iI2qV1he0nzoB0llPVpGUrGOvxVK5CAbwCK9QmOf/hVLvQIWhhJjPiNBmyd7s8WHKOpjaSfwpPzihKz/l0x8AxyccomPv1WA+JoKDOs/NzEVu6NQLo6GC4ep5b9Bzhv3KTdonvwQM0IAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAxvIfRUK+ZtRN13cAAAAASUVORK5CYII=" 
                        alt="카카오 로그인" width="50">
                    </a>
                </div>

                
                <br>
                <br>
                
                <div style="display: flex; justify-content: center; align-items: center;"> 
                    <button class="button col-8" @click="login" style="background-color:#2c3e50; color:#ffffff; border-radius: 8px; border-color:#ffffff">로그인</button>
                </div>
                
            </div>

        </div>
    </div>
</template>
<script>

export default {

    data(){
        return {
      email: "",
      password: "",
    };
        
    },
    methods: {
    login() {
      let user = {
        email: this.email,
        password: this.password,
        };

        const frm = new FormData();
        frm.append("email", user.email);
        frm.append("password", user.password);
        this.$store.dispatch("loginStore/setLoginUser", frm);
        
        }
    }
}
</script>
<style>
.logintotalForm{
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 600px;
    background-color: #0F4471;
   

}
#loginForm {
    display: block;
    align-items: center;
    justify-content: center;
    width: 700px; /* 너비를 200px로 설정 */
    height: 400px; /* 높이를 300px로 설정 */
    border-style: solid;
    border: 5px solid #eeeeee;
} 
.loginform-group {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center; 
    padding: 15px;
    width: 100%;
}
h1 {
      text-align: center;
}

</style>