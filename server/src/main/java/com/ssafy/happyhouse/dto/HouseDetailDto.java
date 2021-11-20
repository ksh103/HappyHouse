package com.ssafy.happyhouse.dto;

public class HouseDetailDto {
	private int houseNo;
	private String dongName;
	private String aptName;
	private String buildYear;
	private String jiBun;
	private String lat;
	private String lng;
	
	public HouseDetailDto() {}
	public HouseDetailDto(int houseNo, String dongName, String aptName, String buildYear, String jiBun, String lat,
			String lng) {
		this.houseNo = houseNo;
		this.dongName = dongName;
		this.aptName = aptName;
		this.buildYear = buildYear;
		this.jiBun = jiBun;
		this.lat = lat;
		this.lng = lng;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getJiBun() {
		return jiBun;
	}
	public void setJiBun(String jiBun) {
		this.jiBun = jiBun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
}
