package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseResultDto;
import com.ssafy.happyhouse.dto.HouseReviewDto;
import com.ssafy.happyhouse.dto.HouseReviewParamDto;
import com.ssafy.happyhouse.dto.HouseReviewResultDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;
import com.ssafy.happyhouse.service.HouseService;
import com.ssafy.happyhouse.service.UserService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class HouseController {

	@Autowired
	HouseService houseService;
	
	private static final int SUCCESS = 1;
	
	// 매물 검색 (동이름)
	@GetMapping("/house/detail/dong/{dongName}")
	public ResponseEntity<HouseResultDto> getHouseDongDetail(@PathVariable String dongName) {
		System.out.println("house detail " + dongName);
		HouseResultDto houseResultDto;
		
		houseResultDto = houseService.getHouseDongDetail(dongName);
		
		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 매물 검색 (동+아파트)
	@GetMapping("/house/detail/keyword/{searchWord}")
	public ResponseEntity<HouseResultDto> getHouseSearchDetail(@PathVariable String searchWord) {
		System.out.println("house detail " + searchWord);
		HouseResultDto houseResultDto;
		
		houseResultDto = houseService.getHouseSearchDetail(searchWord);
		
		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 매물 실거래가 조회
	@GetMapping("/house/deal/{houseNo}")
	public ResponseEntity<HouseResultDto> getHouseDetail(@PathVariable int houseNo) {
		System.out.println("house detail " + houseNo);
		HouseResultDto houseResultDto;
		
		houseResultDto = houseService.getHouseDeal(houseNo);
		
		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 리뷰 등록
	@PostMapping(value="/house/review")
	public ResponseEntity<HouseReviewResultDto> houseReviewRegister(@RequestBody HouseReviewDto houseReviewDto, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    UserDto userDto = (UserDto) session.getAttribute("userDto");
	    
	    userDto.setUserSeq(userDto.getUserSeq());
	            
	    HouseReviewResultDto houseReviewResultDto = houseService.houseReviewRegister(houseReviewDto, request);
		
		System.out.println("register " + houseReviewDto);

		if (houseReviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseReviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseReviewResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 리뷰 상세 조회 
	@GetMapping(value="/house/review/{houseNo}")
	public ResponseEntity<HouseReviewResultDto> noticeDetail(@PathVariable int houseNo, HttpSession session){
	
		HouseReviewParamDto houseReviewParamDto = new HouseReviewParamDto();
		houseReviewParamDto.setHouseNo(houseNo);
	    UserDto userDto = (UserDto) session.getAttribute("userDto");
	    
	    if (userDto != null) houseReviewParamDto.setUserSeq(userDto.getUserSeq());
	    
	    HouseReviewResultDto houseReviewResultDto = houseService.houseReviewDetail(houseReviewParamDto);
	
	    if( houseReviewResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseReviewResultDto>(houseReviewResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseReviewResultDto>(houseReviewResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }         
	}
}
