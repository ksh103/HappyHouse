package com.ssafy.happyhouse.dto;

public class HouseReviewDto {
	int reviewId;
	int houseNo;
	int userSeq;
	String title;
	String content;
	double trafficScore;
	double surroundingScore;
	double livingScore;
	double recommendScore;
	
	private boolean sameUser;
	
	public HouseReviewDto() {}
	public HouseReviewDto(int reviewId, int houseNo, int userSeq, String title, String content, double trafficScore,
			double surroundingScore, double livingScore, double recommendScore) {
		this.reviewId = reviewId;
		this.houseNo = houseNo;
		this.userSeq = userSeq;
		this.title = title;
		this.content = content;
		this.trafficScore = trafficScore;
		this.surroundingScore = surroundingScore;
		this.livingScore = livingScore;
		this.recommendScore = recommendScore;
	}
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
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
	public double getTrafficScore() {
		return trafficScore;
	}
	public void setTrafficScore(double trafficScore) {
		this.trafficScore = trafficScore;
	}
	public double getSurroundingScore() {
		return surroundingScore;
	}
	public void setSurroundingScore(double surroundingScore) {
		this.surroundingScore = surroundingScore;
	}
	public double getLivingScore() {
		return livingScore;
	}
	public void setLivingScore(double livingScore) {
		this.livingScore = livingScore;
	}
	public double getRecommendScore() {
		return recommendScore;
	}
	public void setRecommendScore(double recommendScore) {
		this.recommendScore = recommendScore;
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
		builder.append("HouseReviewDto [reviewId=").append(reviewId).append(", houseNo=").append(houseNo)
				.append(", userSeq=").append(userSeq).append(", title=").append(title).append(", content=")
				.append(content).append(", trafficScore=").append(trafficScore).append(", surroundingScore=")
				.append(surroundingScore).append(", livingScore=").append(livingScore).append(", recommendScore=")
				.append(recommendScore).append("]");
		return builder.toString();
	}
}
