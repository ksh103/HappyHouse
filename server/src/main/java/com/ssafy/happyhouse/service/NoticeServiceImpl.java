package com.ssafy.happyhouse.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dao.NoticeDao;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeFileDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDao dao;
	//C:\apps\happyhouse\server
	private static final String uploadFolder = "upload";
	private static final String uploadPath = "C:" + File.separator + "apps" + File.separator + "happyhouse"
            + File.separator + "server" 
            + File.separator + "src" 
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static";
//	String uploadPath = "C:" + File.separator + "Users" + File.separator + "park" + File.separator + "git" + File.separator + "HappyHouse_Vue"
//            + File.separator + "server" 
//            + File.separator + "src" 
//            + File.separator + "main"
//            + File.separator + "resources"
//            + File.separator + "static";
    
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;
    
	@Override
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		    
	    try {
	        int userReadCnt = dao.noticeUserReadCount(noticeParamDto);
	        if( userReadCnt == 0 ) {
	            dao.noticeUserReadInsert(noticeParamDto.getNoticeId(), noticeParamDto.getUserSeq());
	            dao.noticeReadCountUpdate(noticeParamDto.getNoticeId());
	        }
	        
	        NoticeDto noticeDto = dao.noticeDetail(noticeParamDto);
	        if( noticeDto.getUserSeq() != 0 && noticeDto.getUserSeq() == noticeParamDto.getUserSeq()) {
	            noticeDto.setSameUser(true);
	        } else {
	            noticeDto.setSameUser(false);
	        }
	        
	        List<NoticeFileDto> fileList = dao.noticeDetailFileList(noticeDto.getNoticeId());
	
	        noticeDto.setFileList(fileList);
	        noticeResultDto.setDto(noticeDto);
	
	        noticeResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    return noticeResultDto;
	}
	
	@Override
	public NoticeResultDto noticeDelete(int noticeId) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
	    try {
	    	List<String> fileUrlList = dao.noticeFileUrlDeleteList(noticeId);
  	        for(String fileUrl : fileUrlList) {
  	            File file = new File(uploadPath + File.separator, fileUrl);                
  	            if(file.exists()) {
  	                file.delete();
  	            }
  	        }
  	        dao.noticeFileDelete(noticeId);
  	        dao.noticeReadCountDelete(noticeId);
  	        dao.noticeDelete(noticeId); // 원본글 삭제       
  	        noticeResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    
	    return noticeResultDto;
	}
	@Override
	public NoticeResultDto noticeUpdate(NoticeDto dto, MultipartHttpServletRequest request) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
			
	    try {
	        dao.noticeUpdate(dto);
	
	        List<MultipartFile> fileList = request.getFiles("file");
	        
	        // 파일 경로 찾기
	        File uploadDir = new File(uploadPath + File.separator + uploadFolder);
	        if (!uploadDir.exists()) uploadDir.mkdir();
	        
	        // 물리 파일 삭제, 첨부파일 여러개 고려
	        List<String> fileUrlList = dao.noticeFileUrlDeleteList(dto.getNoticeId());    
	        for(String fileUrl : fileUrlList) {    
	            File file = new File(uploadPath + File.separator, fileUrl);
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	
	        dao.noticeFileDelete(dto.getNoticeId()); // 테이블 파일 삭제
	        
	        for (MultipartFile part : fileList) {
	            int noticeId = dto.getNoticeId();
	            
	            String fileName = part.getOriginalFilename();
	            
	            //Random File Id
	            UUID uuid = UUID.randomUUID();
	            
	            //file extension
	            String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
	        
	            String savingFileName = uuid + "." + extension;
	        
	            File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
	            
	            System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
	            part.transferTo(destFile);
	        
	            // Table Insert
	            NoticeFileDto noticeFileDto = new NoticeFileDto();
	            noticeFileDto.setNoticeId(noticeId);
	            noticeFileDto.setFileName(fileName);
	            noticeFileDto.setFileSize(part.getSize());
	            noticeFileDto.setFileContentType(part.getContentType());
	            String noticeFileUrl = "/" + uploadFolder + "/" + savingFileName;
	            noticeFileDto.setFileUrl(noticeFileUrl);
	            
	            dao.noticeFileInsert(noticeFileDto);
	        }
	        noticeResultDto.setResult(SUCCESS);
	        
	    }catch(IOException e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    return noticeResultDto;
	}
	@Override
	public NoticeResultDto noticeInsert(NoticeDto dto, MultipartHttpServletRequest request) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();

        try {
            dao.noticeInsert(dto);

            List<MultipartFile> fileList = request.getFiles("file");

            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();

            for (MultipartFile part : fileList) {

	            int noticeId = dto.getNoticeId();
	            
	            String fileName = part.getOriginalFilename();
	            
	            //Random File Id
	            UUID uuid = UUID.randomUUID();
	            
	            //file extension
	            String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
	        
	            String savingFileName = uuid + "." + extension;
	        
	            File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
	            
	            System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
	            part.transferTo(destFile);
	        
	            // Table Insert
	            NoticeFileDto noticeFileDto = new NoticeFileDto();
	            noticeFileDto.setNoticeId(noticeId);
	            noticeFileDto.setFileName(fileName);
	            noticeFileDto.setFileSize(part.getSize());
	            noticeFileDto.setFileContentType(part.getContentType());
	            String noticeFileUrl = "/" + uploadFolder + "/" + savingFileName;
	            noticeFileDto.setFileUrl(noticeFileUrl);
	            
	            dao.noticeFileInsert(noticeFileDto);
	        }
            noticeResultDto.setResult(SUCCESS);
        	
	    }catch(IOException e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    return noticeResultDto;
	}
	
	@Override
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		    
	    try {
	        List<NoticeDto> list = dao.noticeList(noticeParamDto);
	        int count = dao.noticeListTotalCount();            
	        noticeResultDto.setList(list);
	        noticeResultDto.setCount(count);
	        noticeResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    
	    return noticeResultDto;
	}
	
	@Override
	public NoticeResultDto noticeLatestList(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
	    
	    try {
	        List<NoticeDto> list = dao.noticeLatestList(noticeParamDto);           
	        noticeResultDto.setList(list);
	        noticeResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    
	    return noticeResultDto;
	}
	
	@Override
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto) {

		NoticeResultDto noticeResultDto = new NoticeResultDto();
	    
	    try {
	        List<NoticeDto> list = dao.noticeListSearchWord(noticeParamDto);
	        int count = dao.noticeListSearchWordTotalCount(noticeParamDto);
	        
	        noticeResultDto.setList(list);
	        noticeResultDto.setCount(count);
	        
	        noticeResultDto.setResult(SUCCESS);
	    
	    }catch(Exception e) {
	        e.printStackTrace();
	        noticeResultDto.setResult(FAIL);
	    }
	    
	    return noticeResultDto;
	}


}
