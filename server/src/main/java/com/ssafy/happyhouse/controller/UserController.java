package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;
import com.ssafy.happyhouse.service.UserService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
	private static final int INCORRECT_INFO = 2;
	
	
	@PostMapping(value="/user/register")
	public ResponseEntity<UserResultDto> register(@RequestBody UserDto userDto) {
		System.out.println("register " + userDto);
		UserResultDto userResultDto = userService.userRegister(userDto);
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/user/modify")
	public ResponseEntity<UserResultDto> modify(@RequestBody UserDto userDto) {
		System.out.println("modify " + userDto);
		UserResultDto userResultDto = userService.userModify(userDto);
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/user/password")
	public ResponseEntity<UserResultDto> passwordModify(@RequestBody UserDto userDto, HttpSession session) {
		UserDto dto = (UserDto) session.getAttribute("userDto");
	    if (userDto != null) userDto.setUserId(dto.getUserId());
	    System.out.println("pwdmodify " + userDto);
		UserResultDto userResultDto = userService.userPasswordModify(userDto);
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value="/user")
	public ResponseEntity<UserResultDto> delete(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		System.out.println("delete " + userDto);
		UserResultDto userResultDto = userService.userDelete(userDto);
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/user/info/id/{userId}")
	public ResponseEntity<Integer> userIdCheck(@PathVariable String userId) {
		return ResponseEntity.ok(userService.userIdCheck(userId));
	}
	
	@PostMapping(value = "/user/login")
	public ResponseEntity<UserResultDto> login(@RequestBody UserDto dto, HttpSession session, HttpServletRequest request) {
		UserResultDto userResultDto = userService.login(dto);
		System.out.println("result  " + userResultDto);
		if (userResultDto.getResult() == SUCCESS) {
			session.setAttribute("userDto", userResultDto.getDto());
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/user/logout")
	public ResponseEntity<UserResultDto> logout(HttpSession session) {
		session.invalidate();
		UserResultDto userResultDto = new UserResultDto();
		userResultDto.setResult(SUCCESS);
		return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/user/password")
	public ResponseEntity<UserResultDto> findPassword(@RequestBody UserDto dto, HttpServletRequest request) {
		UserResultDto userResultDto = userService.findPassword(dto);
		System.out.println("controller result  " + userResultDto);
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else if (userResultDto.getResult() == INCORRECT_INFO){
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 프로필 이미지 등록
	@PutMapping(value = "/user/profileImg")
	public ResponseEntity<UserResultDto> userProfileImage(MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("/u/pi");
		UserDto userDto = new UserDto();
	    userDto = (UserDto) session.getAttribute("userDto");
	    
	    userDto.setUserSeq(userDto.getUserSeq());
		
		UserResultDto userResultImg = userService.userProfileImage(userDto, request);
		
		if (userResultImg.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultImg, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultImg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 친구 찾기
	@GetMapping("/user/{searchWord}")
	public ResponseEntity<UserResultDto> friendSearch(@PathVariable String searchWord, HttpSession session) {
		System.out.println("friendSearch" + searchWord);
		
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		 
		UserResultDto userResultDto = userService.friendSearch(searchWord, userDto);
		
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
