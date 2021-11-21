package com.ssafy.happyhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.common.LoginInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(loginInterceptor)
    	.addPathPatterns("/**")
    	.excludePathPatterns("/", "/*.html", "favicon.ico")
    	.excludePathPatterns("/include/**", "/js/**", "/css/**", "/img/**", "/images/**", "/board/**", "/assets/**", "/error/**")
    	.excludePathPatterns("/info/**", "/house/**", "/dealinfo/**", "/notices/**")	// 주소 (구/동) 정보, 공지사항, 실거래가 조회 허용
    	.excludePathPatterns("/user/login", "/user/logout", "/user/password")		// 로그인, 로그아웃 허용
    	.excludePathPatterns("/notice")							// 공지사항 접속 주소 허용
    	.excludePathPatterns("/boards")
    	.excludePathPatterns("/swagger-resources/**", "/swagger-ui/index.html/**", "/v3/api-docs", "/webjars/**");
    }
}