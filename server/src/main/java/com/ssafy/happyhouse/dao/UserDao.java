package com.ssafy.happyhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserDto;

@Mapper
public interface UserDao {
	public int userRegister(UserDto userDto);
	public int userModify(UserDto userDto);
	public int userDelete(UserDto userDto);
	public UserDto login(String userId);
	public UserDto findPassword(String userId);
	public int updatePassword(UserDto userDto);
}
