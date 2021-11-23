package com.ssafy.happyhouse.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.HouseOnGoingDto;
import com.ssafy.happyhouse.dto.HouseOnGoingParamDto;
import com.ssafy.happyhouse.dto.HouseOnGoingResultDto;
import com.ssafy.happyhouse.dto.HouseResultDto;
import com.ssafy.happyhouse.dto.HouseReviewDto;
import com.ssafy.happyhouse.dto.HouseReviewParamDto;
import com.ssafy.happyhouse.dto.HouseReviewResultDto;
import com.ssafy.happyhouse.dto.UserDto;

public interface HouseService {
	public HouseResultDto getHouseDongDetail(String dongString, UserDto userDto); // 매물 검색 (동이름)
	public HouseResultDto getHouseSearchDetail(String searchWord, UserDto userDto); // 매물 검색 (아파트 이름)
	
	public HouseResultDto getHouseDeal(int houseNo); // 매물 실거래가 조회
	
	public HouseOnGoingResultDto houseOnGoingRegister(HouseOnGoingDto houseOnGoingDto, MultipartHttpServletRequest request); // 매물 등록(현재 진행 중)
	
	public HouseOnGoingResultDto houseOnGoingList(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 리스트(전체)
	public HouseOnGoingResultDto houseNoOnGoingList(int houseNo, UserDto userDto); // 등록된 매물 리스트(특정 매물 클릭)
	public HouseOnGoingResultDto houseOnGoingLimitList(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 리스트(5개)
	
	public HouseOnGoingResultDto houseOnGoingDetail(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 보기

	public HouseReviewResultDto houseReviewRegister(HouseReviewDto houseReviewDto, HttpServletRequest request); // 리뷰 등록
	public HouseReviewResultDto houseReviewList(HouseReviewParamDto houseReviewParamDto); // 리뷰 데이터 조회
	public HouseReviewResultDto houseReviewAllListByUserSeq(HouseReviewParamDto houseReviewParamDto);	
	public HouseReviewResultDto houseReviewDelete(int reviewId);

}
