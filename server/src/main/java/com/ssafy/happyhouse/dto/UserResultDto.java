package com.ssafy.happyhouse.dto;

import java.util.List;

public class UserResultDto {
	private int result;
	private UserDto dto;
	private List<UserDto> userDto;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public UserDto getDto() {
		return dto;
	}
	public void setDto(UserDto dto) {
		this.dto = dto;
	}
	public List<UserDto> getUserDto() {
		return userDto;
	}
	public void setUserDto(List<UserDto> userDto) {
		this.userDto = userDto;
	}
}
