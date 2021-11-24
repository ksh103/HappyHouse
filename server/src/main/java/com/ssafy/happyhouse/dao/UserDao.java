package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserImgFileDto;

@Mapper
public interface UserDao {
	public int userRegister(UserDto userDto);
	
	public int userModify(UserDto userDto);
	public int userPasswordModify(UserDto userDto);
	
	public int userDelete(UserDto userDto);
	
	public int userIdCheck(String userId);
	public UserDto login(String userId);
	
	public UserDto findPassword(String userId);
	public int updatePassword(UserDto userDto);
	
	public int userImgFileInsert(UserImgFileDto userFileDto);
	public int userImgFileDelete(int userSeq);
	
	public List<UserDto> friendSearch(String searchWord); // 친구 찾기
}
