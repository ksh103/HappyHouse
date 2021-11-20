package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.InfoResultDto;
import com.ssafy.happyhouse.service.InfoService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class InfoController {

	@Autowired
	InfoService infoService;
	
	private static final int SUCCESS = 1;
	
	@GetMapping("/info/gu/{siCode}")
	public ResponseEntity<InfoResultDto> getGu(@PathVariable int siCode) {
		InfoResultDto infoResultDto;
		
		infoResultDto = infoService.getGu(siCode);
		
		if (infoResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(infoResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(infoResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/info/dong/{houseNo}")
	public ResponseEntity<InfoResultDto> getDong(@PathVariable int houseNo) {
		InfoResultDto infoResultDto;
		System.out.println("guCode " + houseNo);
		infoResultDto = infoService.getDong(houseNo);
		
		if (infoResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(infoResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(infoResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/info/news")
	public ResponseEntity<InfoResultDto> getNews() {
		InfoResultDto infoResultDto = infoService.getNews();
		
		if (infoResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(infoResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(infoResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
