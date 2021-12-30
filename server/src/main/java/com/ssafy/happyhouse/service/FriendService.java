package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.FriendDto;
import com.ssafy.happyhouse.dto.FriendResultDto;

public interface FriendService {
	 
	public FriendResultDto friendFollow(FriendDto FriendDto); // 팔로우 : 내가 친구 요청(추가)
	public FriendResultDto friendUnFollow(FriendDto FriendDto); // 친구 해제
	
	public FriendResultDto friendCount(String id);
	public FriendResultDto friendFollower(String toId); // 팔로워 : 나를 친구로 등록한 사람들 조회
	public FriendResultDto friendFollowing(String fromId); // 팔로잉 : 내가 친구로 등록한 사람들 조회
}
