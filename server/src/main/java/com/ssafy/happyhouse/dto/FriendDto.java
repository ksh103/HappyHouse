package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendDto {
	private int id;
	private String fromId;
	private String toId;
	
	private int userSeq;
	private String userId;
	private String userName;
	private String userEmail;
	private LocalDateTime regDt;
	private String userProfileImage;
	private int code;
}