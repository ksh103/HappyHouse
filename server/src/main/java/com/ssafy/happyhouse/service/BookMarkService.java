package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.BookMarkParamDto;
import com.ssafy.happyhouse.dto.BookMarkResultDto;

public interface BookMarkService {
	public BookMarkResultDto getMyBookmarkList(String userId);
	public BookMarkResultDto getFriendBookmarkList(BookMarkParamDto bookMarkParamDto);
	
	public BookMarkResultDto insertBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto);
	public BookMarkResultDto insertBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto);
	
	public BookMarkResultDto deleteBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto);
	public BookMarkResultDto deleteBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto);
}
