package com.ssafy.happyhouse.dto;

import java.util.List;

public class BookMarkResultDto {
	private int result;
	private BookMarkParamDto dto;
	private List<HouseDetailDto> houseList;
	private List<HouseOnGoingDto> houseOngoingList;
	
	public BookMarkParamDto getDto() {
		return dto;
	}
	public void setDto(BookMarkParamDto dto) {
		this.dto = dto;
	}
	public List<HouseDetailDto> getHouseList() {
		return houseList;
	}
	public void setHouseList(List<HouseDetailDto> houseList) {
		this.houseList = houseList;
	}
	public List<HouseOnGoingDto> getHouseOngoingList() {
		return houseOngoingList;
	}
	public void setHouseOngoingList(List<HouseOnGoingDto> houseOngoingList) {
		this.houseOngoingList = houseOngoingList;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
}