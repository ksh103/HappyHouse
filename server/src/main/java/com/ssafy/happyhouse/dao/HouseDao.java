package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseDetailDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseDao {
	public List<HouseDetailDto> getHouseDetail(String dongString);
	public List<HouseDealDto> getHouseDeal(int houseNo);
}
