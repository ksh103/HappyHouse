package com.ssafy.happyhouse.dto;

import java.util.List;

public class HouseResultDto {
	private int result;
	private List<HouseDetailDto> houseDetailDto;
	private List<HouseDealDto> houseDealDto;
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<HouseDetailDto> getHouseDetailDto() {
		return houseDetailDto;
	}

	public void setHouseDetailDto(List<HouseDetailDto> houseDetailDto) {
		this.houseDetailDto = houseDetailDto;
	}

	public List<HouseDealDto> getHouseDealDto() {
		return houseDealDto;
	}

	public void setHouseDealDto(List<HouseDealDto> houseDealDto) {
		this.houseDealDto = houseDealDto;
	}

	@Override
	public String toString() {
		return "HouseResultDto [result=" + result + ", houseDetailDto=" + houseDetailDto + ", houseDealDto="
				+ houseDealDto + "]";
	}
}
