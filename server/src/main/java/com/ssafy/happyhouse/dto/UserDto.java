package com.ssafy.happyhouse.dto;

public class UserDto {
	private int userSeq;
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private int userLevel;
	
	public UserDto() { }
	public UserDto(int userSeq, String userId, String userName, String userPassword, String userEmail, int userLevel) {
		this.userSeq = userSeq;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userLevel = userLevel;
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
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDto [userSeq=").append(userSeq).append(", userId=").append(userId).append(", userName=")
				.append(userName).append(", userPassword=").append(userPassword).append(", userEmail=")
				.append(userEmail).append(", userLevel=").append(userLevel).append("]");
		return builder.toString();
	}
}
