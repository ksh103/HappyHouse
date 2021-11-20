package com.ssafy.happyhouse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;

public interface NoticeService {
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto);
	public NoticeResultDto noticeDelete(int noticeId);
	public NoticeResultDto noticeUpdate(NoticeDto dto, MultipartHttpServletRequest request);
	public NoticeResultDto noticeInsert(NoticeDto dto, MultipartHttpServletRequest request);
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto);
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto);
}
