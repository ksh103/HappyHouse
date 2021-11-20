package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.GuDto;

@Mapper
public interface InfoDao {
	public List<GuDto> getGu(int siCode);
	public List<DongDto> getDong(int dongCode);
}
