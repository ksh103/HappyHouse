package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class UserDto {
	private int userSeq;
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private LocalDateTime regDt;
	private String userProfileimage;
	private int code;
	
	public UserDto() {}
	public UserDto(int userSeq, String userId, String userName, String userPassword, String userEmail, String userProfileimage, int code) {
		this.userSeq = userSeq;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userProfileimage = userProfileimage;
		this.code = code;
	}
	
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);
	}
	public String getUserProfileimage() {
		return userProfileimage;
	}
	public void setUserProfileimage(String userProfileimage) {
		this.userProfileimage = userProfileimage;
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
		builder.append("UserDto [userSeq=").append(userSeq).append(", userId=").append(userId).append(", userName=")
				.append(userName).append(", userPassword=").append(userPassword).append(", userEmail=")
				.append(userEmail).append(", regDt=").append(regDt).append(", userProfileimage=")
				.append(userProfileimage).append(", code=").append(code).append("]");
		return builder.toString();
	}
	
}
