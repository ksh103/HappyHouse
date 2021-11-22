package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.NoticeFileDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserFileDto;

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
	
	public int userProfileImage(UserDto userDto);
	public int userFileInsert(UserFileDto userFileDto);
	public int userFileDelete(String userId);
	public List<UserFileDto> noticeDetailFileList(int noticeId);
	public List<String> userFileUrlDeleteList(String userId);
	
	public UserDto userInfo(String userId);
}
