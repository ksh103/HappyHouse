package com.ssafy.happyhouse.dto;

import java.util.List;

public class HouseOnGoingResultDto {
	private int result; // success
	private HouseOnGoingDto dto; // 상세 
	private List<HouseOnGoingDto> list; // 목록
	private int count; // 글 개수
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public HouseOnGoingDto getDto() {
		return dto;
	}
	public void setDto(HouseOnGoingDto dto) {
		this.dto = dto;
	}
	public List<HouseOnGoingDto> getList() {
		return list;
	}
	public void setList(List<HouseOnGoingDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
