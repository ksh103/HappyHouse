package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	private int userSeq;
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userPhone;
	private LocalDateTime regDt;
	private String userProfileimage;
	private int code;
	
	private boolean sameUser;
	
	private boolean friend;
	
	private List<UserImgFileDto> fileList;
	
}
