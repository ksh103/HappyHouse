package com.ssafy.happyhouse.service;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dao.CompanyDao;
import com.ssafy.happyhouse.dto.CompanyDto;
import com.ssafy.happyhouse.dto.CompanyImgFileDto;
import com.ssafy.happyhouse.dto.CompanyResultDto;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;

	private static final int SUCCESS = 1;
	private static final int INCORRECT_INFO = 2;
	private static final int DUPLICATED = 3; 
	private static final int NOT_DUPLICATED = 4; 
	private static final int FAIL = -1;
	
	private static final String uploadFolder = "companyProfileImage";
	private static final String uploadPath = "C:" + File.separator + "apps" + File.separator + "happyhouse"
            + File.separator + "server" 
            + File.separator + "src" 
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static"
            + File.separator + "upload";

	@Override
	public CompanyResultDto companyRegister(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			if (companyDao.companyRegister(companyDto) == 1) {
				companyResultDto.setDto(companyDto);
				companyResultDto.setResult(SUCCESS);
			} else {
				companyResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}

	@Override
	public CompanyResultDto companyModify(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			if (companyDao.companyModify(companyDto) == 1) {
				companyResultDto.setDto(companyDto);
				companyResultDto.setResult(SUCCESS);
			} else {
				companyResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}
	
	@Override
	public CompanyResultDto companyPasswordModify(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			if (companyDao.companyPasswordModify(companyDto) == 1) {
				companyResultDto.setDto(companyDto);
				companyResultDto.setResult(SUCCESS);
			} else {
				companyResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}

	@Override
	public CompanyResultDto companyDelete(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			if (companyDao.companyDelete(companyDto) == 1) {
				companyResultDto.setResult(SUCCESS);
			} else {
				companyResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}
	
	@Override
	public CompanyResultDto login(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			CompanyDto resultDto = companyDao.login(companyDto.getCompId());
			
			if (resultDto != null && resultDto.getCompPassword().equals(companyDto.getCompPassword())) {
				companyResultDto.setDto(resultDto);
				companyResultDto.setResult(SUCCESS);
			} else {
				companyResultDto.setResult(FAIL);
			}
		} catch(Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}
	
	@Override
	public CompanyResultDto companyIdCheck(String compId) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			if (companyDao.companyIdCheck(compId) == 1) {
				companyResultDto.setResult(DUPLICATED);
			} else {
				companyResultDto.setResult(NOT_DUPLICATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}
	
	@Override
	public CompanyResultDto findPassword(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		System.out.println("findPw");
//		System.out.println(userDto);
		try {
			CompanyDto findPwResultDto = companyDao.findPassword(companyDto.getCompId());
			System.out.println(findPwResultDto);
			if (findPwResultDto != null && findPwResultDto.getCompEmail().equals(companyDto.getCompEmail())) {
				// ?????? PW??? ??????
				final String newPassword = getRandomPassword(12);
				findPwResultDto.setCompPassword(newPassword);
				// ????????? PW??? DB??? ?????? ???, ?????? ??????
				// ?? ????????????..??
				if (companyDao.updatePassword(findPwResultDto) == 1 && sendInitPwEmail(findPwResultDto)) {
					System.out.println(findPwResultDto);
					companyResultDto.setResult(SUCCESS);
				} else {
					System.out.println(findPwResultDto);
					companyResultDto.setResult(FAIL);
				}
			} else {
				companyResultDto.setResult(INCORRECT_INFO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}

	@Override
	public CompanyResultDto updatePassword(CompanyDto companyDto) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			if (companyDao.updatePassword(companyDto) == 1) {
				companyResultDto.setDto(companyDto);
				companyResultDto.setResult(SUCCESS);
			} else {
				companyResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}
	

	private static String getRandomPassword(int len) {

        char[] charSet = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '@', '#', '$', '%', '^', '&', '!', '*'
        };
        StringBuilder sb = new StringBuilder(len);
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(charSet[random.nextInt(charSet.length)]);
        }
        return sb.toString();
    }
	
	private static boolean sendInitPwEmail(CompanyDto companyDto) {

		final String charSet = "utf-8";
        final String hostSMTP = "smtp.naver.com";
        final String hostSMTPid = "pde0691";
        final String hostSMTPpwd = "ssafy6th!";

        final String fromEmail = "pde0691@naver.com";
        final String fromName = "HAPPY HOUSE";
        final String subject = "Happy House ?????? ???????????? ????????? ???????????????.";
        String msg = "<div style='border: 1px solid black; padding: 10px; font-family: verdana;'>";
        msg += "<h2>???????????????. <span style='color: blue;'>" + companyDto.getCompName() + "</span>???.</h2>";
        msg += "<p>???????????? ??????????????? ????????? ????????????. ??????????????? ???????????? ???????????????.</p>";
        msg += "<p>?????? ???????????? : <span style='color: blue;'>" + companyDto.getCompPassword() + "</span></p></div>";

        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSLOnConnect(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(587);

            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setStartTLSEnabled(true);
            email.addTo(companyDto.getCompName(), companyDto.getCompPassword(), charSet);
            email.setFrom(fromEmail, fromName, charSet);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	@Override
	public CompanyResultDto companyProfileImage(CompanyDto companyDto, MultipartHttpServletRequest request) {
		CompanyResultDto companyResultDto = new CompanyResultDto();
		try {
			List<MultipartFile> fileList = request.getFiles("file");
			
			// ?????? ?????? ??????
	        File uploadDir = new File(uploadPath + File.separator + uploadFolder);
	        if (!uploadDir.exists()) uploadDir.mkdir();
	        
	        // ?????? ?????? ??????, ???????????? ????????? ??????
//	        List<String> fileUrlList = companyDao.companyFileUrlDeleteList(companyDto.getCompId());    
//	        for(String fileUrl : fileUrlList) {    
//	            File file = new File(uploadPath + File.separator, fileUrl);
//	            if(file.exists()) {
//	                file.delete();
//	            }
//	        }
	        
	        companyDao.companyFileDelete(companyDto.getCompId()); // ????????? ?????? ??????
	        
	        for (MultipartFile part : fileList) {
	            String compId = companyDto.getCompId();
	            
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
	            CompanyImgFileDto companyFileDto = new CompanyImgFileDto();
//	            companyFileDto.setCompId(compId);
	            companyFileDto.setFileName(fileName);
	            companyFileDto.setFileSize(part.getSize());
	            companyFileDto.setFileContentType(part.getContentType());
	            String companyFileUrl = "/" + uploadFolder + "/" + savingFileName;
	            companyFileDto.setFileUrl(companyFileUrl);
	            
	            companyDao.companyFileInsert(companyFileDto);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			companyResultDto.setResult(FAIL);
		}
		return companyResultDto;
	}
}
