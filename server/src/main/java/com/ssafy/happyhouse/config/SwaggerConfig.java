package com.ssafy.happyhouse.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//3.0.0 : http://localhost:8080/server/swagger-ui/index.html
@Configuration
@EnableSwagger2 
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		final ApiInfo apiInfo = new ApiInfoBuilder()
			.title("HappyHouse")
			.description("HappyHouse에서 사용되는 RestApi에 대한 문서를 제공한다.</h3>")
			.license("MIT License")
			.version("6.0").build();
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo).select()
			.apis(RequestHandlerSelectors.basePackage("com.ssafy.happhyhouse.controller"))
			.paths(PathSelectors.ant("/**/server/**")) // 3.0.0 or 2.9.2
			.build();
	}
}

