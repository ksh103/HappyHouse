package com.ssafy.happyhouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseOnGoingParamDto {

	private int limit;
	private int offset;
	private String keyword;
	private String keywordType;
	private String dealType;

	// 특정 게시글에 대한 상세가 들어가기 위해
	private int ongoingId;
	private int houseNo;
	private int compSeq;
}
