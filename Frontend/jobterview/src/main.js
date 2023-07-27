import { createApp } from 'vue';
import App from './App.vue'
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import "bootstrap-icons/font/bootstrap-icons.css";
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import Vuex from 'vuex';
import axios from 'axios';
import router from './router'

const app = createApp(App);
app.use(router);
app.use(BootstrapVue3);
app.use(Vuex);
app.config.productionTip = false;
app.config.globalProperties.$axios = axios;
app.mount('#app');


