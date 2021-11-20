package com.ssafy.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CompanyDto;

@Mapper
public interface CompanyDao {
	public int companyRegister(CompanyDto companyDto);
	public int companyModify(CompanyDto companyDto);
	public int companyDelete(CompanyDto companyDto);
	public CompanyDto login(String compId);
	public CompanyDto findPassword(String compId);
	public int updatePassword(CompanyDto companyDto);
	public int companyProfileImage(CompanyDto companyDto);
}
