package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BookMarkParamDto;
import com.ssafy.happyhouse.dto.HouseDetailDto;
import com.ssafy.happyhouse.dto.HouseOnGoingDto;

@Mapper
public interface BookMarkDao {
	public List<HouseDetailDto> getBookmarkHouseDetailListById(String userId);
	public List<HouseOnGoingDto> getBookmarkHouseOngoingListById(String userId);
	
	public int insertBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto);
	public int insertBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto);
	
	public int deleteBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto);
	public int deleteBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto);
}
