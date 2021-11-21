package com.ssafy.happyhouse.dto;

// 글 목록을 가지고 올 때 서버쪽으로 보내는 데이터
public class HouseReviewParamDto {

	private int limit;
	private int offset;
	
	// 특정 게시글에 대한 상세가 들어가기 위해
	private int reviewId;
	private int houseNo;
	private int userSeq;
	
	public HouseReviewParamDto() {}
	public HouseReviewParamDto(int limit, int offset, int reviewId, int houseNo, int userSeq) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.reviewId = reviewId;
		this.houseNo = houseNo;
		this.userSeq = userSeq;
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
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseReviewParamDto [limit=").append(limit).append(", offset=").append(offset)
				.append(", reviewId=").append(reviewId).append(", houseNo=").append(houseNo).append(", userSeq=")
				.append(userSeq).append("]");
		return builder.toString();
	}
}
