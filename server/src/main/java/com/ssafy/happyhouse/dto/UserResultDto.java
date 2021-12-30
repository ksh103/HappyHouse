package com.ssafy.happyhouse.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResultDto {
	private int result;
	private UserDto dto;
	private List<UserDto> userDto;
	private String uploadProfileImgUrl;
	
}
