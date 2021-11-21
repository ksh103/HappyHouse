package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.CompanyFileDto;

@Mapper
public interface CompanyDao {
	public int companyRegister(CompanyDto companyDto);
	
	public int companyModify(CompanyDto companyDto);
	public int companyPasswordModify(CompanyDto companyDto);
	
	public int companyDelete(CompanyDto companyDto);
	
	public CompanyDto login(String compId);
	
	public CompanyDto findPassword(String compId);
	public int updatePassword(CompanyDto companyDto);
	
	public int companyProfileImage(CompanyDto companyDto);
	public int companyFileInsert(CompanyFileDto dto);
	public int companyFileDelete(String compId);
	public List<String> companyFileUrlDeleteList(String compId);
}
