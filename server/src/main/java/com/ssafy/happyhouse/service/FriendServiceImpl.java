package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.FriendDao;
import com.ssafy.happyhouse.dto.FriendDto;
import com.ssafy.happyhouse.dto.FriendResultDto;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	FriendDao friendDao;
	
	private static final int SUCCESS = 1;	
	private static final int INCORRECT_INFO = 2;
	private static final int FAIL = -1;
	
	@Override
	public FriendResultDto friendFollow(FriendDto friendDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			if (friendDao.friendFollow(friendDto) == 1) {
				friendResultDto.setDto(friendDto);
				friendResultDto.setResult(SUCCESS);
			} else {
				friendResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendUnFollow(FriendDto friendDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			if (friendDao.friendUnFollow(friendDto) == 1) {
				friendResultDto.setDto(friendDto);
				friendResultDto.setResult(SUCCESS);
			} else {
				friendResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendFollower(String toId) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			List<FriendDto> followerList = friendDao.friendFollower(toId);
			
			friendResultDto.setList(followerList);
			friendResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}
	
	@Override
	@Transactional
	public FriendResultDto friendCount(String id) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			friendResultDto.setFollowerCount(friendDao.friendFollowerCount(id));
			friendResultDto.setFollowingCount(friendDao.friendFollowingCount(id));
			friendResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}
	
	@Override
	public FriendResultDto friendFollowing(String fromId) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			List<FriendDto> followingList = friendDao.friendFollowing(fromId);
			
			friendResultDto.setList(followingList);
			friendResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}
	
}
