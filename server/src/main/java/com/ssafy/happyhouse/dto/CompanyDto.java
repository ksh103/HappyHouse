package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyDto {
	private int compSeq;
	private String compId;
	private String compName;
	private String compPassword;
	private String compEmail;
	private String compPhone;
	private String compAddress;
	private LocalDateTime regDt;
	private String compProfileimage;
	private int code;
	
}
