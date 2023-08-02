import { createApp } from "vue";
import App from "./App.vue";
import BootstrapVue3 from "bootstrap-vue-3";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

/* fontawesome */
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";
import { fas } from "@fortawesome/free-solid-svg-icons";

/* vuetify */
import "vuetify/styles";
import { createVuetify } from "vuetify";
import { VDataTable } from 'vuetify/labs/VDataTable'
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

import Vuex from "vuex";
import axios from "axios";
import router from "./router/index";
import store from "@/store/store";

const vuetify = createVuetify({
  components,
  directives,
  VDataTable,
});

library.add(faUserSecret);
library.add(fas);

const app = createApp(App);
app.use(Vuex);
app.use(store);
app.use(router);
app.use(vuetify);
app.use(BootstrapVue3);
app.component("font-awesome-icon", FontAwesomeIcon);
app.config.productionTip = false;
app.config.globalProperties.$axios = axios;
app.mount("#app");