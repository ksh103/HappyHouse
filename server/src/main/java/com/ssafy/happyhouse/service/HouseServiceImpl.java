package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dao.HouseDao;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseDetailDto;
import com.ssafy.happyhouse.dto.HouseResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public HouseResultDto getHouseDetail(String dongString) {
		HouseResultDto houseResultDto = new HouseResultDto();
		List<HouseDetailDto> list = null;
		try {
			list = houseDao.getHouseDetail(dongString);
			houseResultDto.setHouseDetailDto(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto getHouseDeal(int houseNo) {
		HouseResultDto houseResultDto = new HouseResultDto();
		List<HouseDealDto> list = null;
		try {
			list = houseDao.getHouseDeal(houseNo);
			houseResultDto.setHouseDealDto(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}
}
