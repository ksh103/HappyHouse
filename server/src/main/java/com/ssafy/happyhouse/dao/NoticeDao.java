package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeFileDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {
	public NoticeDto noticeDetail(NoticeParamDto noticeParamDto);
	public List<NoticeFileDto> noticeDetailFileList(int noticeId);

	public int noticeUserReadCount(NoticeParamDto noticeParamDto); 

	public int noticeUserReadInsert(
	        @Param("noticeId") int noticeId, 
	        @Param("userSeq") int userSeq ); 

	public int noticeReadCountUpdate(int noticeId);

	public int noticeDelete(int noticeId);    
	public int noticeFileDelete(int noticeId);
	public List<String> noticeFileUrlDeleteList(int noticeId);
	public int noticeReadCountDelete(int noticeId);

	public int noticeInsert(NoticeDto dto);
	public int noticeFileInsert(NoticeFileDto dto);

	public List<NoticeDto> noticeList(NoticeParamDto noticeParamDto);
	public List<NoticeDto> noticeLimitList(NoticeParamDto noticeParamDto);
	public int noticeListTotalCount();

	public List<NoticeDto> noticeListSearchWord(NoticeParamDto noticeParamDto);
	public int noticeListSearchWordTotalCount(NoticeParamDto noticeParamDto);

	public int noticeUpdate(NoticeDto dto);
}
