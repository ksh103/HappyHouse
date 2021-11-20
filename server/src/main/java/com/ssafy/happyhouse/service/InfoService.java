package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.InfoResultDto;

public interface InfoService {
	public InfoResultDto getGu(int siCode);
	public InfoResultDto getDong(int dongCode);
	public InfoResultDto getNews();
}
