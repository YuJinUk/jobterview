const { defineConfig } = require("@vue/cli-service");
const {apiUrl, apiUrlPrefix} = require("./src/config/config.js");
const TerserPlugin = require('terser-webpack-plugin');

module.exports = defineConfig({
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      config.optimization = {
        minimize: true,
        minimizer: [
          new TerserPlugin({
            terserOptions: {
              ecma: 6,
              compress: { drop_console: true },
              output: { comments: false },
            },
          }),
        ],
      };
    }
  },
  transpileDependencies: true,
  publicPath: apiUrlPrefix,
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
