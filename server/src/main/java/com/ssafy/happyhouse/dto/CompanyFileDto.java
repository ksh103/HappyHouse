package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;

// 회원 프로필 이미지 업로드
public class CompanyFileDto {
	private int fileId;
	private String compId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUrl;
	private LocalDateTime regDt;
	
	public CompanyFileDto() {}
	public CompanyFileDto(int fileId, String compId, String fileName, long fileSize, String fileContentType,
			String fileUrl, LocalDateTime regDt) {
		super();
		this.fileId = fileId;
		this.compId = compId;
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
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
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
		builder.append("CompanyFileDto [fileId=").append(fileId).append(", compId=").append(compId)
				.append(", fileName=").append(fileName).append(", fileSize=").append(fileSize)
				.append(", fileContentType=").append(fileContentType).append(", fileUrl=").append(fileUrl)
				.append(", regDt=").append(regDt).append("]");
		return builder.toString();
	}
}