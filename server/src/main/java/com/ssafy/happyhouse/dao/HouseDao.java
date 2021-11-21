package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseDetailDto;
import com.ssafy.happyhouse.dto.HouseOnGoingDto;
import com.ssafy.happyhouse.dto.HouseOnGoingParamDto;
import com.ssafy.happyhouse.dto.HouseReviewDto;
import com.ssafy.happyhouse.dto.HouseReviewParamDto;
import com.ssafy.happyhouse.dto.HouseReviewResultDto;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;

@Mapper
public interface HouseDao {
	public List<HouseDetailDto> getHouseDongDetail(String dongString); // 매물 검색 (동이름)
	public List<HouseDetailDto> getHouseSearchDetail(String searchWord); // 매물 검색 (동+아파트이름)
	
	public List<HouseDealDto> getHouseDeal(int houseNo); // 매물 실거래가 조회
	
	public int houseOnGoingRegister(HouseOnGoingDto houseOnGoingDto); // 매물 등록(현재 진행 중)
	
	public List<HouseOnGoingDto> houseOnGoingList(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 리스트(전체)
	public List<HouseOnGoingDto> houseNoOnGoingList(int houseNo); // 등록된 매물 리스트(특정 매물 클릭)
	public List<HouseOnGoingDto> houseOnGoingLimitList(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 리스트(5개)
	
	public int houseOnGoingListTotalCount(); // 전체 매물 개수
	public int houseNoOnGoingListTotalCount(int houseNo); // 등록된 매물 리스트 수 (특정 매물 개수)
	
	public HouseOnGoingDto houseOnGoingDetail(HouseOnGoingParamDto houseOnGoingParamDto); // 등록된 매물 보기
	
	public int houseReviewRegister(HouseReviewDto houseReviewDto); // 리뷰 등록
	public HouseReviewDto houseReviewDetail(HouseReviewParamDto houseReviewParamDto); // 리뷰 데이터 조회
}
