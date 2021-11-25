package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseReviewDto {
	int reviewId;
	int houseNo;
	String aptName;
	int userSeq;
	String userId;
	String userName;
	LocalDateTime regDt;
	String content;
	int trafficScore;
	int surroundingScore;
	int livingScore;
	int recommendScore;
	
	private boolean sameUser;
	
}
