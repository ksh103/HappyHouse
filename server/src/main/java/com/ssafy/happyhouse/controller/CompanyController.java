package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.CompanyResultDto;
import com.ssafy.happyhouse.service.CompanyService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	private static final int SUCCESS = 1;
	private static final int INCORRECT_INFO = 2;
	
	
	@PostMapping(value="/company")
	public ResponseEntity<CompanyResultDto> register(@RequestBody CompanyDto companyDto) {
		System.out.println("register " + companyDto);
		CompanyResultDto companyResultDto = companyService.companyRegister(companyDto);
		if (companyResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(companyResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(companyResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/company")
	public ResponseEntity<CompanyResultDto> modify(@RequestBody CompanyDto companyDto) {
		System.out.println("modify " + companyDto);
		CompanyResultDto companyResultDto = companyService.companyModify(companyDto);
		if (companyResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(companyResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(companyResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value="/company")
	public ResponseEntity<CompanyResultDto> delete(HttpSession session) {
		CompanyDto companyDto = (CompanyDto) session.getAttribute("companyDto");
		System.out.println("delete " + companyDto);
		CompanyResultDto companyResultDto = companyService.companyDelete(companyDto);
		if (companyResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(companyResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(companyResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/company/login")
	public ResponseEntity<CompanyResultDto> login(@RequestBody CompanyDto dto, HttpSession session, HttpServletRequest request) {
		CompanyResultDto companyResultDto = companyService.login(dto);
		System.out.println("result  " + companyResultDto);
		if (companyResultDto.getResult() == SUCCESS) {
			session.setAttribute("companyDto", companyResultDto.getDto());
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/company/logout")
	public ResponseEntity<CompanyResultDto> logout(HttpSession session) {
		session.invalidate();
		CompanyResultDto companyResultDto = new CompanyResultDto();
		companyResultDto.setResult(SUCCESS);
		return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/company/password")
	public ResponseEntity<CompanyResultDto> findPassword(@RequestBody CompanyDto dto, HttpServletRequest request) {
		CompanyResultDto companyResultDto = companyService.findPassword(dto);
		System.out.println("controller result  " + companyResultDto);
		if (companyResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.OK);
		} else if (companyResultDto.getResult() == INCORRECT_INFO){
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/company/profileImg")
	public ResponseEntity<CompanyResultDto> userProfileImage(@RequestBody CompanyDto dto) {
		CompanyResultDto companyResultDto = companyService.companyProfileImage(dto);
		System.out.println("companyProfileImage result  " + companyResultDto);
		if (companyResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<CompanyResultDto>(companyResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
