package com.ssafy.happyhouse.service;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.happyhouse.dto.HouseOnGoingDto;
import com.ssafy.happyhouse.dto.HouseOnGoingParamDto;
import com.ssafy.happyhouse.dto.HouseOnGoingResultDto;
import com.ssafy.happyhouse.dto.HouseResultDto;
import com.ssafy.happyhouse.dto.HouseReviewDto;
import com.ssafy.happyhouse.dto.HouseReviewParamDto;
import com.ssafy.happyhouse.dto.HouseReviewResultDto;

public interface HouseService {
	public HouseResultDto getHouseDongDetail(String dongString); // 매물 검색 (동이름)
	public HouseResultDto getHouseSearchDetail(String searchWord); // 매물 검색 (아파트 이름)
	
	public HouseResultDto getHouseDeal(int houseNo); // 매물 실거래가 조회
	
	public HouseOnGoingResultDto houseOnGoingRegister(HouseOnGoingDto houseOnGoingDto); // 매물 등록(현재 진행 중)
	
	public HouseOnGoingResultDto houseOnGoingList(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 리스트(전체)
	public HouseOnGoingResultDto houseNoOnGoingList(int houseNo); // 등록된 매물 리스트(특정 매물 클릭)
	public HouseOnGoingResultDto houseOnGoingLimitList(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 리스트(5개)
	
	public HouseOnGoingResultDto houseOnGoingDetail(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 보기

	public HouseReviewResultDto houseReviewRegister(HouseReviewDto houseReviewDto, HttpServletRequest request); // 리뷰 등록
	public HouseReviewResultDto houseReviewDetail(HouseReviewParamDto houseReviewParamDto); // 리뷰 데이터 조회
}
