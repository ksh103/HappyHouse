package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.HouseResultDto;

public interface HouseService {
	public HouseResultDto getHouseDetail(String dongString);
	public HouseResultDto getHouseDeal(int houseNo);
}
