package com.ssafy.happyhouse.dto;
import java.util.List;

public class NoticeResultDto {
	private int result; // success
	private NoticeDto dto; // 상세 
	private List<NoticeDto> list; // 목록
	private int count; // 글 개수
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public NoticeDto getDto() {
		return dto;
	}
	public void setDto(NoticeDto dto) {
		this.dto = dto;
	}
	public List<NoticeDto> getList() {
		return list;
	}
	public void setList(List<NoticeDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
