package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.FriendDto;

@Mapper
public interface FriendDao {
	
	public int friendFollow(FriendDto friendDto); // 팔로우 : 내가 친구 요청(추가)
	public int friendUnFollow(FriendDto friendDto); // 친구 해제
	
	public List<FriendDto> friendFollower(String toId); // 팔로워 : 나를 친구로 등록한 사람들 조회
	public int friendFollowerCount(String toId); // 팔로워 : 나를 친구로 등록한 사람들 수
	
	public List<FriendDto> friendFollowing(String fromId); // 팔로잉 : 내가 친구로 등록한 사람들 조회
	public int friendFollowingCount(String fromId); // 팔로잉 : 내가 친구로 등록한 사람들 수
}
