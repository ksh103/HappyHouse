package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class NoticeDto {
	private int noticeId;
	private int userSeq;
	private String userName;
	private String title;
	private String content;
	private int code; // 권한 분류 
	
	private LocalDateTime regDt;
	private int readCount;
	
	private boolean sameUser;
	
	private List<NoticeFileDto> fileList;
	
	public NoticeDto() {}
	public NoticeDto(int userSeq, String title, String content, int code) {
		this.userSeq = userSeq;
		this.title = title;
		this.content = content;
		this.code = code;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<NoticeFileDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<NoticeFileDto> fileList) {
		this.fileList = fileList;
	}
	
	public boolean isSameUser() {
		return sameUser;
	}
	public void setSameUser(boolean sameUser) {
		this.sameUser = sameUser;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoticeDto [noticeId=").append(noticeId).append(", userSeq=").append(userSeq)
				.append(", userName=").append(userName).append(", title=").append(title).append(", content=")
				.append(content).append(", userLevel=").append(code).append(", regDt=").append(regDt)
				.append(", readCount=").append(readCount).append("]");
		return builder.toString();
	}
}