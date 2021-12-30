package com.ssafy.happyhouse.dto;
import java.util.List;

public class HouseReviewResultDto {
	private int result; // success
	private HouseReviewDto dto; // 상세 
	private List<HouseReviewDto> list; // 목록
	private int count; // 글 개수
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public HouseReviewDto getDto() {
		return dto;
	}
	public void setDto(HouseReviewDto dto) {
		this.dto = dto;
	}
	public List<HouseReviewDto> getList() {
		return list;
	}
	public void setList(List<HouseReviewDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
