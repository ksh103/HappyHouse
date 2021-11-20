package com.ssafy.happyhouse.dto;

import java.util.List;

public class InfoResultDto {
	public int result;
	public List<DongDto> dongDto;
	public List<GuDto> guDto;
	public List<NewsDto> newsDto;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<DongDto> getDongDto() {
		return dongDto;
	}
	public void setDongDto(List<DongDto> dongDto) {
		this.dongDto = dongDto;
	}
	public List<GuDto> getGuDto() {
		return guDto;
	}
	public void setGuDto(List<GuDto> guDto) {
		this.guDto = guDto;
	}
	public List<NewsDto> getNewsDto() {
		return newsDto;
	}
	public void setNewsDto(List<NewsDto> newsDto) {
		this.newsDto = newsDto;
	}
	@Override
	public String toString() {
		return "InfoResultDto [result=" + result + ", dongDto=" + dongDto + ", guDto=" + guDto + "]";
	}
}
