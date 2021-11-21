package com.ssafy.happyhouse.dto;

public class HouseOnGoingParamDto {

	private int limit;
	private int offset;
	private String searchWord;

	// 특정 게시글에 대한 상세가 들어가기 위해
	private int registerId;
	private int houseNo;
	private int compSeq;
	
	public HouseOnGoingParamDto() {}
	public HouseOnGoingParamDto(int limit, int offset, String searchWord, int registerId, int houseNo,
			int compSeq) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.searchWord = searchWord;
		this.registerId = registerId;
		this.houseNo = houseNo;
		this.compSeq = compSeq;
	}
	
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
	public int getRegisterId() {
		return registerId;
	}
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseOnGoingParamDto [limit=").append(limit).append(", offset=").append(offset)
				.append(", searchWord=").append(searchWord).append(", registerId=").append(registerId).
				append(", houseNo=").append(houseNo).append(", compSeq=").append(compSeq).append("]");
		return builder.toString();
	}
}
