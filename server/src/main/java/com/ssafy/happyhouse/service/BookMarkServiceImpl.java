package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.BookMarkDao;
import com.ssafy.happyhouse.dto.BookMarkParamDto;
import com.ssafy.happyhouse.dto.BookMarkResultDto;
import com.ssafy.happyhouse.dto.FriendResultDto;

@Service
public class BookMarkServiceImpl implements BookMarkService {

	@Autowired
	BookMarkDao dao;
	
	private static final int SUCCESS = 1;	
	private static final int INCORRECT_INFO = 2;
	private static final int FAIL = -1;
	
	@Override
	@Transactional
	public BookMarkResultDto getMyBookmarkList(String userId) {
		BookMarkResultDto resultDto = new BookMarkResultDto();
		
		try {
			resultDto.setHouseList(dao.getBookmarkHouseDetailListById(userId));
			resultDto.setHouseOngoingList(dao.getBookmarkHouseOngoingListById(userId));
			resultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	@Transactional
	public BookMarkResultDto getFriendBookmarkList(BookMarkParamDto bookMarkParamDto) {
		BookMarkResultDto resultDto = new BookMarkResultDto();
		
		try {
			resultDto.setHouseList(dao.getBookmarkHouseDetailListById(bookMarkParamDto.getFriendId()));
			resultDto.setHouseOngoingList(dao.getBookmarkHouseOngoingListById(bookMarkParamDto.getFriendId()));
			resultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public BookMarkResultDto insertBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto) {
		BookMarkResultDto resultDto = new BookMarkResultDto();
		
		try {
			if (dao.insertBookmarkHouseDetail(bookMarkParamDto) == 1) {
				resultDto.setDto(bookMarkParamDto);
				resultDto.setResult(SUCCESS);
			} else {
				resultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
		
		
	}

	@Override
	public BookMarkResultDto insertBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto) {
		BookMarkResultDto resultDto = new BookMarkResultDto();
		
		try {
			if (dao.insertBookmarkHouseOngoing(bookMarkParamDto) == 1) {
				resultDto.setDto(bookMarkParamDto);
				resultDto.setResult(SUCCESS);
			} else {
				resultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public BookMarkResultDto deleteBookmarkHouseDetail(BookMarkParamDto bookMarkParamDto) {
		BookMarkResultDto resultDto = new BookMarkResultDto();
		
		try {
			if (dao.deleteBookmarkHouseDetail(bookMarkParamDto) == 1) {
				resultDto.setDto(bookMarkParamDto);
				resultDto.setResult(SUCCESS);
			} else {
				resultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public BookMarkResultDto deleteBookmarkHouseOngoing(BookMarkParamDto bookMarkParamDto) {
		BookMarkResultDto resultDto = new BookMarkResultDto();
		
		try {
			if (dao.deleteBookmarkHouseOngoing(bookMarkParamDto) == 1) {
				resultDto.setDto(bookMarkParamDto);
				resultDto.setResult(SUCCESS);
			} else {
				resultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

}
