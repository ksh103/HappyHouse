package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseOnGoingDto {
	int ongoingId; 
	int houseNo; 
	int compSeq;
	String AptName;
	String compName;
	String compAddress;
	String compEmail;
	String compPhone;
	String title; // 매물 이름
	String content; // 매물 내용
	String dealAmount; // 가격
	int floor; // 층수
	int area; // 평수
	String direction; // 방향 (남향 / 북향)
	String type; // 월세/전세/매매
	String fee; // 관리비
	int room; // 방 개수
	int bathroom; // 화장실 개수
	String dong;
	String jibun;
	
	boolean bookmark;
	
	private LocalDateTime regDt;
	private boolean sameUser;
	
	private List<HouseOnGoingFileDto> fileList;
	
}
