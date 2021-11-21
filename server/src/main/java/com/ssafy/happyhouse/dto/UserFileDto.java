package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;

// 회원 프로필 이미지 업로드
public class UserFileDto {
	private int fileId;
	private String userId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUrl;
	private LocalDateTime regDt;
	
	public UserFileDto() {}
	public UserFileDto(int fileId, String userId, String fileName, long fileSize, String fileContentType,
			String fileUrl, LocalDateTime regDt) {
		super();
		this.fileId = fileId;
		this.userId = userId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileContentType = fileContentType;
		this.fileUrl = fileUrl;
		this.regDt = regDt;
	}
	
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserFileDto [fileId=").append(fileId).append(", userId=").append(userId).append(", fileName=")
				.append(fileName).append(", fileSize=").append(fileSize).append(", fileContentType=")
				.append(fileContentType).append(", fileUrl=").append(fileUrl).append(", regDt=").append(regDt)
				.append("]");
		return builder.toString();
	}
}