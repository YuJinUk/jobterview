import { createApp } from 'vue'
import App from './App.vue'
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faUserSecret } from '@fortawesome/free-solid-svg-icons';
import Vuex from 'vuex';
import axios from 'axios';
import router from './router'
import store from './store'

library.add(faUserSecret);

const app = createApp(App).use(store);
app.use(Vuex);
app.use(router);
app.use(BootstrapVue3);
app.component('font-awesome-icon', FontAwesomeIcon);
app.config.productionTip = false;
app.config.globalProperties.$axios = axios;

app.config.globalProperties.localhost = "http://localhost:5000/";
app.config.globalProperties.second = 1;

app.mount('#app');