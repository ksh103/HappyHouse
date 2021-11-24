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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.HouseOnGoingDto;
import com.ssafy.happyhouse.dto.HouseOnGoingParamDto;
import com.ssafy.happyhouse.dto.HouseOnGoingResultDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.HouseService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class HouseOngoingController {

	@Autowired
	HouseService houseService;
	
	private static final int SUCCESS = 1;
	
	// 매물 등록
	@PostMapping(value="/house/deal/ongoing/register")
	public ResponseEntity<HouseOnGoingResultDto> houseOnGoingRegister(HouseOnGoingDto houseOnGoingDto, MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
	    CompanyDto companyDto = (CompanyDto) session.getAttribute("companyDto");
	    
	    houseOnGoingDto.setCompSeq(companyDto.getCompSeq());
		
		System.out.println("houseOnGoingRegister " + houseOnGoingDto);
		
		HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseOnGoingRegister(houseOnGoingDto, request);
		
		if (houseOnGoingResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(houseOnGoingResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 매물 자세히 보기 
	@GetMapping(value="/house/deal/ongoing/{ongoingId}")
	public ResponseEntity<HouseOnGoingResultDto> HouseOnGoingDetail(@PathVariable int ongoingId, HttpSession session){
	
	    HouseOnGoingParamDto houseOnGoingParamDto = new HouseOnGoingParamDto();
	    houseOnGoingParamDto.setOngoingId(ongoingId);
	    
	    CompanyDto companyDto = (CompanyDto) session.getAttribute("companyDto");
	    if (companyDto != null) houseOnGoingParamDto.setCompSeq(companyDto.getCompSeq());
	    
	    HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseOnGoingDetail(houseOnGoingParamDto);
	
	    if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }         
	}
	
	// 등록된 매물 리스트 (전체)
    @GetMapping(value="/house/deal/ongoing")
    public ResponseEntity<HouseOnGoingResultDto> houseOnGoingList(HouseOnGoingParamDto houseOnGoingParamDto){
    	HouseOnGoingResultDto houseOnGoingResultDto;
    	
    	houseOnGoingResultDto = houseService.houseOnGoingList(houseOnGoingParamDto);
    	
    	System.out.println(houseOnGoingParamDto);
    
	    if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
    
    @DeleteMapping(value="/house/deal/ongoing/{ongoingId}")
    public ResponseEntity<HouseOnGoingResultDto> houseOnGoingDelete(@PathVariable int ongoingId, HttpSession session){
//    	UserDto userDto = (UserDto) session.getAttribute("userDto");
    	
    	HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseOnGoingDelete(ongoingId);
    	
    	if( houseOnGoingResultDto.getResult() == SUCCESS ) {
    		return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
 	@PostMapping(value="/house/deal/ongoing/{ongoingId}")
 	public ResponseEntity<HouseOnGoingResultDto> houseOnGoingUpdate(HouseOnGoingDto houseOnGoingDto, MultipartHttpServletRequest request) {
 		
		HttpSession session = request.getSession();
		CompanyDto companyDto = (CompanyDto) session.getAttribute("companyDto");
		houseOnGoingDto.setCompSeq(companyDto.getCompSeq());
 		
 	   System.out.println("houseOnGoingUpdate " + houseOnGoingDto);
 		
 		HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseOnGoingUpdate(houseOnGoingDto, request);
 		
 		if (houseOnGoingResultDto.getResult() == SUCCESS) {
 			return new ResponseEntity<>(houseOnGoingResultDto, HttpStatus.OK);
 		} else {
 			return new ResponseEntity<>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
 		}
 	}
    
    // 등록된 특정 매물 리스트 
    @GetMapping(value="/house/deal/ongoing/list/{houseNo}")
    public ResponseEntity<HouseOnGoingResultDto> houseNoOnGoingList(@PathVariable int houseNo, HttpSession session){
    	UserDto userDto = (UserDto) session.getAttribute("userDto");
    	
    	HouseOnGoingResultDto houseOnGoingResultDto = houseService.houseNoOnGoingList(houseNo, userDto);
    	
    	if( houseOnGoingResultDto.getResult() == SUCCESS ) {
    		return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    // 등록된 매물 리스트 수 (특정 매물 개수)
//    @GetMapping(value="/house/deal/ongoing/count/{houseNo}")
//    public int houseNoOnGoingListTotalCount(@PathVariable int houseNo){
//    	HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
//    	houseOnGoingResultDto = houseService.houseNoOnGoingList(houseNo);
//
//    	if( houseOnGoingResultDto.getResult() == SUCCESS ) {
//    		return houseOnGoingResultDto.getCount();
//    	}else {
//    		return 0;
//    	}
//    }
    
	// 최근 등록 매물 5개까지
    @GetMapping(value="/house/deal/ongoing/latest")
    public ResponseEntity<HouseOnGoingResultDto> houseOnGoingLimitList(HouseOnGoingParamDto houseOnGoingParamDto){
    	HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
    	
    	houseOnGoingResultDto = houseService.houseOnGoingLimitList(houseOnGoingParamDto);
    	
	    if( houseOnGoingResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseOnGoingResultDto>(houseOnGoingResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
