package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BookMarkParamDto;
import com.ssafy.happyhouse.dto.BookMarkResultDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.BookMarkService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/bookmark")
public class BookMarkController {
	
	@Autowired
	BookMarkService service;

	private static final int SUCCESS = 1;
	
	@GetMapping("/user")
	public ResponseEntity<BookMarkResultDto> getMyBookmarkList(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		BookMarkResultDto resultDto = service.getMyBookmarkList(userDto.getUserId());
		
		if( resultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}

	@GetMapping("/friend")
	public ResponseEntity<BookMarkResultDto> getFriendBookmarkList(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		paramDto.setUserId(userDto.getUserId());
		
		BookMarkResultDto resultDto = service.getFriendBookmarkList(paramDto);
		
		if( resultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}

	@PostMapping("/house")
	public ResponseEntity<BookMarkResultDto> insertBookmarkHouseDetail(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		paramDto.setUserId(userDto.getUserId());
		
		BookMarkResultDto resultDto = service.insertBookmarkHouseDetail(paramDto);
		
		if( resultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}

	@PostMapping("/houseongoing")
	public ResponseEntity<BookMarkResultDto> insertBookmarkHouseOngoing(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		paramDto.setUserId(userDto.getUserId());
		
		BookMarkResultDto resultDto = service.insertBookmarkHouseOngoing(paramDto);
		
		if( resultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}

	@DeleteMapping("/house")
	public ResponseEntity<BookMarkResultDto> deleteBookmarkHouseDetail(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		paramDto.setUserId(userDto.getUserId());
		
		BookMarkResultDto resultDto = service.deleteBookmarkHouseDetail(paramDto);
		
		if( resultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}

	@DeleteMapping("/houseongoing")
	public ResponseEntity<BookMarkResultDto> deleteBookmarkHouseOngoing(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		paramDto.setUserId(userDto.getUserId());
		
		BookMarkResultDto resultDto = service.deleteBookmarkHouseOngoing(paramDto);
		
		if( resultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<BookMarkResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
}
