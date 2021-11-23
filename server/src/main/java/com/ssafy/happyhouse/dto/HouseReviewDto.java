package com.ssafy.happyhouse.dto;

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
	String regDt;
	String title;
	String content;
	double trafficScore;
	double surroundingScore;
	double livingScore;
	double recommendScore;
	
	private boolean sameUser;
	
}
