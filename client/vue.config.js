module.exports = {
  outputDir: 'C:/Users/park/git/Happy_House_5_Spring_강소현_박대언/happy_house_5_spring_-_/Happy_House_5_Spring/src/main/resources/static',

  chainWebpack: (config) => {
    config.module
      .rule("vue")
      .use("vue-loader")
      .loader("vue-loader")
      .tap((options) => {
        options.transformAssetUrls = {
          img: "src",
          image: "xlink:href",
          "b-avatar": "src",
          "b-img": "src",
          "b-img-lazy": ["src", "blank-src"],
          "b-card": "img-src",
          "b-card-img": "src",
          "b-card-img-lazy": ["src", "blank-src"],
          "b-carousel-slide": "img-src",
          "b-embed": "src",
        };

        return options;
      });
  },
  css: {
    loaderOptions: {
      sass: {
        prependData: `@import "@/styles/_variables.scss";  @import "@/styles/_mixins.scss";`,
      },
    },
  },
};