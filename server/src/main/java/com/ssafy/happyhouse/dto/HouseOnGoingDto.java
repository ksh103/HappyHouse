package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import lombok.ToString;

@ToString
public class HouseOnGoingDto {
	int ongoingId; 
	int houseNo; 
	int compSeq;
	String AptName;
	String compName;
	String compAddress;
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
	
	boolean bookmark;
	
	private LocalDateTime regDt;
	private boolean sameUser;
	
	private List<HouseOnGoingFileDto> fileList;
	
	public int getOngoingId() {
		return ongoingId;
	}
	public void setOngoingId(int ongoingId) {
		this.ongoingId = ongoingId;
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
	
	public boolean isBookmark() {
		return bookmark;
	}
	public void setBookmark(boolean bookmark) {
		this.bookmark = bookmark;
	}
	public List<HouseOnGoingFileDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<HouseOnGoingFileDto> fileList) {
		this.fileList = fileList;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseOnGoingDto [ongoingId=").append(ongoingId).append(", houseNo=").append(houseNo)
				.append(", compSeq=").append(compSeq).append(", title=").append(title).append(", content=")
				.append(content).append(", dealAmount=").append(dealAmount).append(", floor=").append(floor)
				.append(", area=").append(area).append(", direction=").append(direction).append(", type=").append(type)
				.append(", fee=").append(fee).append(", room=").append(room).append(", bathroom=").append(bathroom)
				.append(", regDt=").append(regDt).append(", sameUser=").append(sameUser).append(", fileList=")
				.append(fileList).append("]");
		return builder.toString();
	}
}
