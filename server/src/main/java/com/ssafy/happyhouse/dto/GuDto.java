package com.ssafy.happyhouse.dto;

public class GuDto {
	private int guCode;
	private String guName;
	
	public GuDto() { }

	public GuDto(int guCode, String guName) {
		super();
		this.guCode = guCode;
		this.guName = guName;
	}

	public int getGuCode() {
		return guCode;
	}

	public void setGuCode(int guCode) {
		this.guCode = guCode;
	}

	public String getGuName() {
		return guName;
	}

	public void setGuName(String guName) {
		this.guName = guName;
	}
}
