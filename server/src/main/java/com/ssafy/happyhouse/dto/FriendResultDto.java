package com.ssafy.happyhouse.dto;

import java.util.List;

public class FriendResultDto {
	private int result;
	private FriendDto dto; // 상세 
	private List<FriendDto> list;
	private int followerCount;
	private int followingCount;
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public FriendDto getDto() {
		return dto;
	}

	public void setDto(FriendDto dto) {
		this.dto = dto;
	}

	public List<FriendDto> getList() {
		return list;
	}

	public void setList(List<FriendDto> list) {
		this.list = list;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	
}
