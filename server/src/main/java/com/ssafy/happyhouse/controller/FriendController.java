package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.FriendDto;
import com.ssafy.happyhouse.dto.FriendResultDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.FriendService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	private static final int SUCCESS = 1;
	private static final int INCORRECT_INFO = 2;
	
	@PostMapping(value="/friend")
	public ResponseEntity<FriendResultDto> friendFollow(@RequestBody FriendDto friendDto, HttpSession session) {
		
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendFollow(friendDto);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@DeleteMapping(value="/friend")
	public ResponseEntity<FriendResultDto> friendUnfollow(@RequestBody FriendDto friendDto, HttpSession session) {
		
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendUnFollow(friendDto);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@GetMapping("/friend/follower")
	public ResponseEntity<FriendResultDto> friendFollower(@RequestBody FriendDto friendDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendFollower(userDto.getUserId());
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@GetMapping("/friend/count")
	public ResponseEntity<FriendResultDto> friendCount(HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendCount(userDto.getUserId());
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@GetMapping("/friend/following")
	public ResponseEntity<FriendResultDto> friendFollowing(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		FriendResultDto friendResultDto = friendService.friendFollowing(userDto.getUserId());
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
}
