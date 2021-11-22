package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.happyhouse.dto.BookMarkParamDto;
import com.ssafy.happyhouse.dto.BookMarkResultDto;
import com.ssafy.happyhouse.dto.FriendResultDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.BookMarkService;

@Controller("/bookmark")
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
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("/houseongoing")
	public ResponseEntity<BookMarkResultDto> insertBookmarkHouseOngoing(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping("/house")
	public ResponseEntity<BookMarkResultDto> deleteBookmarkHouseDetail(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping("/houseongoing")
	public ResponseEntity<BookMarkResultDto> deleteBookmarkHouseOngoing(@RequestBody BookMarkParamDto paramDto, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
}
