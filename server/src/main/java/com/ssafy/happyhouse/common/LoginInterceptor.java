package com.ssafy.happyhouse.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssafy.happyhouse.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String method = request.getMethod();
		System.out.println(">>>>>>>>>>>>>>> " + uri);
		System.out.println(">>>>>>>>>>> " + method);
		
		// 회원가입은 허용
        if (uri.equals("/user") && method.equals("POST")) {
        	return true;
        }
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        // CORS의 put, delete에서 options request 가 브라우저에 의해서 발생되는 데이터 무조건 수락
        if (request.getMethod().equals("OPTIONS")) {
        	return true;
        }
        if (userDto == null) {
        	Gson gson = new Gson();

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "login");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);

        	return false;
        }
        return true;
    }
}
