package com.ssafy.happyhouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookMarkParamDto {
	private int ongoingId;
	private int houseNo;
	private String userId;
	private String friendId;
}
