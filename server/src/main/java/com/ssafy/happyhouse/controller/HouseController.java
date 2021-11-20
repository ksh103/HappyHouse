package com.ssafy.happyhouse.controller;

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

import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.HouseOnGoingDto;
import com.ssafy.happyhouse.dto.HouseOnGoingParamDto;
import com.ssafy.happyhouse.dto.HouseOnGoingResultDto;
import com.ssafy.happyhouse.dto.HouseResultDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;
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
	
	// 매물 검색 (동이름)
	@GetMapping("/house/detail/{dongName}")
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
	
	// 매물 검색 (아파트 이름)
	@GetMapping("/house/detail/{searchName}")
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
	
	// 매물 등록(현재 진행 중)
	@PostMapping(value="/house/deal/ongoing")
	public ResponseEntity<HouseOnGoingResultDto> houseOnGoingRegister(@RequestBody HouseOnGoingDto houseOnGoingDto) {
		System.out.println("register " + houseOnGoingDto);
		HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseOnGoingRegister(houseOnGoingDto);
		if (houseOnGoingResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseOnGoingResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 매물 자세히 보기 (현재 진행 중)
	@GetMapping(value="/house/deal/ongoing/{registerId}")
	public ResponseEntity<HouseOnGoingResultDto> HouseOnGoingDetail(@PathVariable int registerId, HttpSession session){
	
	    HouseOnGoingParamDto houseOnGoingParamDto = new HouseOnGoingParamDto();
	    houseOnGoingParamDto.setRegisterId(registerId);
	    
	    CompanyDto companyDto = (CompanyDto) session.getAttribute("companyDto");
	    if (companyDto != null) houseOnGoingParamDto.setCompSeq(companyDto.getCompSeq());
	    
	    HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseOnGoingDetail(houseOnGoingParamDto);
	
	    if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }         
	}
	
	// 등록된 매물 리스트
    @GetMapping(value="/house/deal/ongoing")
    public ResponseEntity<HouseOnGoingResultDto> houseOnGoingList(HouseOnGoingParamDto houseOnGoingParamDto){
    	HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
    	
    	if( houseOnGoingParamDto.getHouseNo() == 0) {
    		houseOnGoingResultDto = houseService.houseOnGoingList(houseOnGoingParamDto);
    	}else {
    		houseOnGoingResultDto = houseService.houseNoOnGoingList(houseOnGoingParamDto); // 특정 매물 클릭
    	}
    	
	    if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
    
    // 등록된 매물 리스트 수 (특정 매물 개수)
    @GetMapping(value="/house/deal/ongoing/count")
    public int houseNoOnGoingListTotalCount(){
    	HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
    	
    	int totalCount = houseOnGoingResultDto.getCount();
    	
    	if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return totalCount;
	    }else {
	    	return 0;
	    }
    }
    
	// 최근 등록 매물 5개까지
    @GetMapping(value="/house/deal/ongoing/limit")
    public ResponseEntity<HouseOnGoingResultDto> houseOnGoingLimitList(HouseOnGoingParamDto houseOnGoingParamDto){
    	HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
    	
    	houseOnGoingResultDto = houseService.houseOnGoingList(houseOnGoingParamDto);
    	
	    if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
