package com.ssafy.happyhouse.dto;

import java.util.List;

public class BookMarkResultDto {
	int result;
	List<HouseDetailDto> houseList;
	List<HouseOnGoingDto> houseOngoingList;
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