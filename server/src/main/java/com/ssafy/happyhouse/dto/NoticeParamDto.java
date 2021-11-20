package com.ssafy.happyhouse.dto;

// 글 목록을 가지고 올 때 서버쪽으로 보내는 데이터
public class NoticeParamDto {

	private int limit;
	private int offset;
	private String searchWord;
	
	// 특정 게시글에 대한 상세가 들어가기 위해
	private int noticeId;
	private int userSeq;
	
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
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	@Override
	public String toString() {
		return "NoticeParamDto [limit=" + limit + ", offset=" + offset + ", searchWord=" + searchWord + ", noticeId="
				+ noticeId + ", userSeq=" + userSeq + "]";
	}
	
}
