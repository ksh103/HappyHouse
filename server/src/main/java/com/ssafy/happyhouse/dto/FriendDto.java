package com.ssafy.happyhouse.dto;

public class FriendDto {
	int id;
	String fromId;
	String toId;
	
	public FriendDto() {}
	public FriendDto(int id, String fromId, String toId) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FriendDto [id=").append(id).append(", fromId=").append(fromId).append(", toId=").append(toId)
				.append("]");
		return builder.toString();
	}
}
