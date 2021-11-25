package com.ssafy.happyhouse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.CompanyResultDto;
import com.ssafy.happyhouse.dto.UserResultDto;

public interface CompanyService {
	public CompanyResultDto companyRegister(CompanyDto companyDto);
	
	public CompanyResultDto companyModify(CompanyDto companyDto);
	public CompanyResultDto companyPasswordModify(CompanyDto companyDto);
	
	public CompanyResultDto companyDelete(CompanyDto companyDto);
	
	public CompanyResultDto companyIdCheck(String compId);
	public CompanyResultDto login(CompanyDto companyDto);
	
	public CompanyResultDto findPassword(CompanyDto companyDto);
	public CompanyResultDto updatePassword(CompanyDto companyDto);
	
	public CompanyResultDto companyProfileImage(CompanyDto companyDto, MultipartHttpServletRequest request);
}
