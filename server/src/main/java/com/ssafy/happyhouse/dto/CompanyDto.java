package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CompanyDto {
	private int compSeq;
	private String compId;
	private String compName;
	private String compPassword;
	private String compEmail;
	private String compAddress;
	private LocalDateTime regDt;
	private String compProfileimage;
	private int code;
	
	public CompanyDto() {}
	public CompanyDto(int compSeq, String compId, String compName, String compPassword, String compEmail,
			String compAddress, String compProfileimage, int code) {
		super();
		this.compSeq = compSeq;
		this.compId = compId;
		this.compName = compName;
		this.compPassword = compPassword;
		this.compEmail = compEmail;
		this.compAddress = compAddress;
		this.compProfileimage = compProfileimage;
		this.code = code;
	}
	
	public int getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(int compSeq) {
		this.compSeq = compSeq;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompPassword() {
		return compPassword;
	}
	public void setCompPassword(String compPassword) {
		this.compPassword = compPassword;
	}
	public String getCompEmail() {
		return compEmail;
	}
	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}
	public String getCompAddress() {
		return compAddress;
	}
	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);
	}
	public String getCompProfileimage() {
		return compProfileimage;
	}
	public void setCompProfileimage(String compProfileimage) {
		this.compProfileimage = compProfileimage;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyDto [compSeq=").append(compSeq).append(", compId=").append(compId).append(", compName=")
				.append(compName).append(", compPassword=").append(compPassword).append(", compEmail=")
				.append(compEmail).append(", compAddress=").append(compAddress).append(", regDt=").append(regDt)
				.append(", compProfileimage=").append(compProfileimage).append(", code=").append(code).append("]");
		return builder.toString();
	}
}
