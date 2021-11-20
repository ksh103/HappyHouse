package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.CompanyResultDto;

public interface CompanyService {
	public CompanyResultDto companyRegister(CompanyDto companyDto);
	public CompanyResultDto companyModify(CompanyDto companyDto);
	public CompanyResultDto companyDelete(CompanyDto companyDto);
	public CompanyResultDto login(CompanyDto companyDto);
	public CompanyResultDto findPassword(CompanyDto companyDto);
	public CompanyResultDto updatePassword(CompanyDto companyDto);
	public CompanyResultDto companyProfileImage(CompanyDto companyDto);
}
