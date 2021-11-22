package com.ssafy.happyhouse.dto;

public class HouseOnGoingParamDto {

	private int limit;
	private int offset;
	private String searchWord;

	// 특정 게시글에 대한 상세가 들어가기 위해
	private int ongoingId;
	private int houseNo;
	private int compSeq;
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getOngoingId() {
		return ongoingId;
	}
	public void setOngoingId(int ongoingId) {
		this.ongoingId = ongoingId;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public int getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(int compSeq) {
		this.compSeq = compSeq;
	}
	
	
}
