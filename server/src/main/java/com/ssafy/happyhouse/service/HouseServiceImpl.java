package com.ssafy.happyhouse.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dao.BookMarkDao;
import com.ssafy.happyhouse.dao.HouseDao;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseDetailDto;
import com.ssafy.happyhouse.dto.HouseOnGoingDto;
import com.ssafy.happyhouse.dto.HouseOnGoingFileDto;
import com.ssafy.happyhouse.dto.HouseOnGoingParamDto;
import com.ssafy.happyhouse.dto.HouseOnGoingResultDto;
import com.ssafy.happyhouse.dto.HouseResultDto;
import com.ssafy.happyhouse.dto.HouseReviewDto;
import com.ssafy.happyhouse.dto.HouseReviewParamDto;
import com.ssafy.happyhouse.dto.HouseReviewResultDto;
import com.ssafy.happyhouse.dto.UserDto;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;
	@Autowired
	private BookMarkDao bookmarkDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	private static final String uploadFolder = "houseOnGoingImage";
	private static final String uploadPath = "C:" + File.separator + "apps" + File.separator + "happyhouse"
            + File.separator + "server" 
            + File.separator + "src" 
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static"
            + File.separator + "upload";
	
	// 매물 검색 (동이름)
	@Override
	public HouseResultDto getHouseDongDetail(String dongString, UserDto userDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		List<HouseDetailDto> list = null;
		try {
			list = houseDao.getHouseDongDetail(dongString);
			
			// 세션이 있다면, 해당 사용자의 북마크 데이터인지 여부 판별
	        if (userDto != null) {
	        	List<HouseDetailDto> userBookMarkList = bookmarkDao.getBookmarkHouseDetailListById(userDto.getUserId());
	        	System.out.println(userBookMarkList);
	        	for (HouseDetailDto item : userBookMarkList) {
	        		for (HouseDetailDto innerItem : list) {
	        			if (item.getHouseNo() == innerItem.getHouseNo()) {
	        				innerItem.setBookmark(true);
	        			}
	        		}
	        	}
	        }
			
			houseResultDto.setHouseDetailDto(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}
	
	// 매물 검색 (동 + 아파트 이름)
	@Override
	public HouseResultDto getHouseSearchDetail(String searchWord, UserDto userDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		List<HouseDetailDto> list = null;
		try {
			list = houseDao.getHouseSearchDetail(searchWord);
			houseResultDto.setHouseDetailDto(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	// 매물 실거래가 조회
	@Override
	public HouseResultDto getHouseDeal(int houseNo) {
		HouseResultDto houseResultDto = new HouseResultDto();
		List<HouseDealDto> list = null;
		try {
			list = houseDao.getHouseDeal(houseNo);
			houseResultDto.setHouseDealDto(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	// 매물 등록
	@Override
	public HouseOnGoingResultDto houseOnGoingRegister(HouseOnGoingDto houseDto, MultipartHttpServletRequest request) {
		HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
		try {
			houseDao.houseOnGoingRegister(houseDto);
			
			 List<MultipartFile> fileList = request.getFiles("file");

	         File uploadDir = new File(uploadPath + File.separator + uploadFolder);
	         if (!uploadDir.exists()) uploadDir.mkdir();
	         
	         for (MultipartFile part : fileList) {

	            int ongoingId = houseDto.getOngoingId();
	            
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
	            HouseOnGoingFileDto houseOnGoingFileDto = new HouseOnGoingFileDto();
	            houseOnGoingFileDto.setOngoingId(ongoingId);;
	            houseOnGoingFileDto.setFileName(fileName);
	            houseOnGoingFileDto.setFileSize(part.getSize());
	            houseOnGoingFileDto.setFileContentType(part.getContentType());
	            String houseOnGoingFileUrl = "/" + uploadFolder + "/" + savingFileName;
	            houseOnGoingFileDto.setFileUrl(houseOnGoingFileUrl);
	            
	            houseDao.houseOnGoingFileInsert(houseOnGoingFileDto);
	         }
	         houseOnGoingResultDto.setResult(SUCCESS);
	        
		} catch (Exception e) {
			e.printStackTrace();
			houseOnGoingResultDto.setResult(FAIL);
		}
		return houseOnGoingResultDto;
	}

	// 등록된 매물 자세히 보기
	@Override
	public HouseOnGoingResultDto houseOnGoingDetail(HouseOnGoingParamDto houseOnGoingParamDto) {
		HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
		
		try {
			HouseOnGoingDto houseOnGoingDto = houseDao.houseOnGoingDetail(houseOnGoingParamDto);
			if(houseOnGoingDto.getCompSeq() != 0 && houseOnGoingDto.getCompSeq() == houseOnGoingParamDto.getCompSeq()){
				houseOnGoingDto.setSameUser(true);
			}else {
				houseOnGoingDto.setSameUser(false);
			}			
			houseOnGoingResultDto.setDto(houseOnGoingDto);
			houseOnGoingResultDto.setResult(SUCCESS);
			
			List<HouseOnGoingFileDto> fileList = houseDao.houseOnGoingDetailFileList(houseOnGoingDto.getOngoingId());
			
			houseOnGoingDto.setFileList(fileList);
			houseOnGoingResultDto.setDto(houseOnGoingDto);
			
			houseOnGoingResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			houseOnGoingResultDto.setResult(FAIL);
		}
		return houseOnGoingResultDto;
	}

	// 등록된 매물 리스트(전체)
	@Override
	public HouseOnGoingResultDto houseOnGoingList(HouseOnGoingParamDto houseOnGoingParamDto) {
		HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
	    
	    try {
	        List<HouseOnGoingDto> list = houseDao.houseOnGoingList(houseOnGoingParamDto);
	        
	        for (HouseOnGoingDto item : list) {
	        	List<HouseOnGoingFileDto> fileDtoList = houseDao.houseOnGoingDetailFileList(item.getOngoingId());
	        	if (fileDtoList != null && !fileDtoList.isEmpty()) {
	        		item.setFileList(fileDtoList);
	        	}
	        }

	        houseOnGoingResultDto.setList(list);
	        houseOnGoingResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        houseOnGoingResultDto.setResult(FAIL);
	    }
	    return houseOnGoingResultDto;
	}
	
	// 등록된 매물 리스트(특정 매물 클릭)
	@Override
	@Transactional
	public HouseOnGoingResultDto houseNoOnGoingList(int houseNo, UserDto userDto) {
		HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
	    
	    try {
	        List<HouseOnGoingDto> list = houseDao.houseNoOnGoingList(houseNo);
	        int count = houseDao.houseNoOnGoingListTotalCount(houseNo);
	        System.out.println("NOONGOING   " + userDto);
	        // 세션이 있다면, 해당 사용자의 북마크 데이터인지 여부 판별
	        if (userDto != null) {
	        	List<HouseOnGoingDto> userBookMarkList = bookmarkDao.getBookmarkHouseOngoingListById(userDto.getUserId());
	        	System.out.println(userBookMarkList);
	        	for (HouseOnGoingDto item : userBookMarkList) {
	        		for (HouseOnGoingDto innerItem : list) {
	        			if (item.getOngoingId() == innerItem.getOngoingId()) {
	        				innerItem.setBookmark(true);
	        			}
	        		}
	        	}
	        }
	        
	        houseOnGoingResultDto.setList(list);
	        houseOnGoingResultDto.setCount(count);
	        
	        houseOnGoingResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        houseOnGoingResultDto.setResult(FAIL);
	    }
	    return houseOnGoingResultDto;
	}
	


	// 등록된 매물 리스트(5개)
	@Override
	public HouseOnGoingResultDto houseOnGoingLimitList(HouseOnGoingParamDto houseOnGoingParamDto) {
		HouseOnGoingResultDto houseOnGoingResultDto = new HouseOnGoingResultDto();
	    
	    try {
	        List<HouseOnGoingDto> list = houseDao.houseOnGoingLimitList(houseOnGoingParamDto);

	        houseOnGoingResultDto.setList(list);
	        houseOnGoingResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        houseOnGoingResultDto.setResult(FAIL);
	    }
	    return houseOnGoingResultDto;
	}
	
	// 리뷰 등록
	@Override
	public HouseReviewResultDto houseReviewRegister(HouseReviewDto houseReviewDto, HttpServletRequest request) {
		 HouseReviewResultDto houseReviewResultDto = new HouseReviewResultDto();
		try {
			if (houseDao.houseReviewRegister(houseReviewDto) == 1) {
				houseReviewResultDto.setDto(houseReviewDto);
				houseReviewResultDto.setResult(SUCCESS);
			} else {
				houseReviewResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			houseReviewResultDto.setResult(FAIL);
		}
		return houseReviewResultDto;
	}

	// 특정 건물에 대한 리뷰 리스트 조회
	@Override
	public HouseReviewResultDto houseReviewList(HouseReviewParamDto houseReviewParamDto) {
		HouseReviewResultDto houseReviewResultDto = new HouseReviewResultDto();
		
		try {
			List<HouseReviewDto> houseReviewList = houseDao.houseReviewList(houseReviewParamDto);
			
			if (houseReviewParamDto.getUserSeq() != 0 && houseReviewList != null && !houseReviewList.isEmpty()) {
				for (HouseReviewDto houseReviewDto : houseReviewList) {
					if(houseReviewParamDto.getUserSeq() == houseReviewDto.getUserSeq()){
						houseReviewDto.setSameUser(true);
					}else {
						houseReviewDto.setSameUser(false);
					}
				}
			}
			
			houseReviewResultDto.setList(houseReviewList);
			houseReviewResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			houseReviewResultDto.setResult(FAIL);
		}
		return houseReviewResultDto;
	}

	@Override
	public HouseReviewResultDto houseReviewAllListByUserSeq(HouseReviewParamDto houseReviewParamDto) {
		HouseReviewResultDto houseReviewResultDto = new HouseReviewResultDto();
		
		try {
			List<HouseReviewDto> houseReviewList = houseDao.houseReviewAllListByUserSeq(houseReviewParamDto.getUserSeq());
			
			houseReviewResultDto.setList(houseReviewList);
			houseReviewResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			houseReviewResultDto.setResult(FAIL);
		}
		return houseReviewResultDto;
	}

	@Override
	public HouseReviewResultDto houseReviewDelete(int reviewId) {
		HouseReviewResultDto resultDto = new HouseReviewResultDto();
		
		try {
			if (houseDao.houseReviewDelete(reviewId) == 1) {
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
