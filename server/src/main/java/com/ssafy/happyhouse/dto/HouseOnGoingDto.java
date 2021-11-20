package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class HouseOnGoingDto {
	int registerId; 
	int houseNo; 
	int compSeq;
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
	
	private LocalDateTime regDt;
	private boolean sameUser;
	
	public HouseOnGoingDto() {}
	public HouseOnGoingDto(int registerId, int houseNo, int compSeq, String title, String content,
		String dealAmount, int floor, int area, String direction, String type, String fee, int room,
		int bathroom) {
		this.registerId = registerId;
		this.houseNo = houseNo;
		this.compSeq = compSeq;
		this.title = title;
		this.content = content;
		this.dealAmount = dealAmount;
		this.floor = floor;
		this.area = area;
		this.direction = direction;
		this.type = type;
		this.fee = fee;
		this.room = room;
		this.bathroom = bathroom;
	}
	
	public int getRegisterId() {
		return registerId;
	}
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public int getCompSeq() {
		return compSeq;
	}
	public void setCompSeq(int compSeq) {
		this.compSeq = compSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getBathroom() {
		return bathroom;
	}
	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}
	public boolean isSameUser() {
		return sameUser;
	}
	public void setSameUser(boolean sameUser) {
		this.sameUser = sameUser;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseOnGoingDto [registerId=").append(registerId).append(", houseNo=").append(houseNo)
				.append(", compSeq=").append(compSeq).append(", title=").append(title).append(", content=")
				.append(content).append(", dealAmount=").append(dealAmount).append(", floor=").append(floor)
				.append(", area=").append(area).append(", direction=").append(direction).append(", type=").append(type)
				.append(", fee=").append(fee).append(", room=").append(room).append(", bathroom=").append(bathroom)
				.append(", regDt=").append(regDt).append(", sameUser=").append(sameUser).append("]");
		return builder.toString();
	}
}
