package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	public UserResultDto userModify(UserDto userDto);
	public UserResultDto userDelete(UserDto userDto);
	public UserResultDto login(UserDto UserDto);
	public UserResultDto findPassword(UserDto UserDto);
	public UserResultDto updatePassword(UserDto userDto);
	public UserResultDto userProfileImage(UserDto userDto);
}
