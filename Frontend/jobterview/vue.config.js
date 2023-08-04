const { defineConfig } = require("@vue/cli-service");
const {apiUrl} = require("./src/config/config.js");

module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '/testfe/',
  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    },
  },
  devServer: {
    proxy: apiUrl, // 서버 주소에 맞게 수정
  },
  transpileDependencies: ["vuetify"],
});
