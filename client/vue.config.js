// const { createProxyMiddleware } = require('http-proxy-middleware');

// module.exports = function(app) {
//   app.use(
//     createProxyMiddleware('/naver', {
//       target: 'https://openapi.naver.com',
//       pathRewrite: { '^/naver':'' },
//       changeOrigin: true
//     })
//   )
// };

module.exports = {
  outputDir: 'C:/Users/park/git/Happy_House_5_Spring_강소현_박대언/happy_house_5_spring_-_/Happy_House_5_Spring/src/main/resources/static',
  devServer: {
    proxy: { // proxyTable 설정
      '/naver': { // api 로 시작하는 소스 는 traget으로 잡아준다. > 사용할때 url 이 api 가 있어야 한다.
        // target: process.env.VUE_APP_PROD_BASE_DOMAIN,
        target: 'https://openapi.naver.com/',
        changeOrigin: true
      },
    }
  }
}