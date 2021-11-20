package com.ssafy.happyhouse.dto;

public class DongDto {
	private int dongCode;
	private String dongName;
	public DongDto() { }
	public DongDto(int dongCode, String dongName) {
		super();
		this.dongCode = dongCode;
		this.dongName = dongName;
	}
	public int getDongCode() {
		return dongCode;
	}
	public void setDongCode(int dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
}
