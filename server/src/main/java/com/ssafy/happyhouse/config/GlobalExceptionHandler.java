package com.ssafy.happyhouse.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssafy.happyhouse.dto.NoticeResultDto;

//@ControllerAdvice 
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(Exception.class)
	public ResponseEntity<NoticeResultDto> noticeErrorResponseEntity() { // 비동기 방식
		System.out.println("!!!");
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		noticeResultDto.setResult(-1);
		return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
