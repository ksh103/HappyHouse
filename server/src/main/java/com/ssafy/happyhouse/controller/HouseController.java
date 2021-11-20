package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseResultDto;
import com.ssafy.happyhouse.service.HouseService;

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
	
	@GetMapping("/house/detail/{dongName}")
	public ResponseEntity<HouseResultDto> getHouseDetail(@PathVariable String dongName) {
		System.out.println("house detail " + dongName);
		HouseResultDto houseResultDto;
		
		houseResultDto = houseService.getHouseDetail(dongName);
		
		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
}
