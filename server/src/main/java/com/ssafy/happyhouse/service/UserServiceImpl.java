package com.ssafy.happyhouse.service;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyhouse.dao.FriendDao;
import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.FriendDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserImgFileDto;
import com.ssafy.happyhouse.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendDao friendDao;

	private static final int SUCCESS = 1;	
	private static final int INCORRECT_INFO = 2;
	private static final int FAIL = -1;
	
	private static final String uploadFolder = "userProfileImage";
	private static final String uploadPath = "C:" + File.separator + "apps" + File.separator + "happyhouse"
            + File.separator + "server" 
            + File.separator + "src" 
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static"
            + File.separator + "upload";

	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			if (userDao.userRegister(userDto) == 1) {
				userResultDto.setDto(userDto);
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userModify(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			if (userDao.userModify(userDto) == 1) {
				userResultDto.setDto(userDto);
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userPasswordModify(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			if (userDao.userPasswordModify(userDto) == 1) {
				userResultDto.setDto(userDto);
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	
	@Override
	public UserResultDto userDelete(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			if (userDao.userDelete(userDto) == 1) {
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	
	@Override
	public int userIdCheck(String userId){
		return userDao.userIdCheck(userId);
	}

	
	@Override
	public UserResultDto login(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			UserDto resultDto = userDao.login(userDto.getUserId());
			
			if (resultDto != null && resultDto.getUserPassword().equals(userDto.getUserPassword())) {
				userResultDto.setDto(resultDto);
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		} catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	
	@Override
	public UserResultDto findPassword(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		System.out.println("findPw");
//		System.out.println(userDto);
		try {
			UserDto findPwResultDto = userDao.findPassword(userDto.getUserId());
//			System.out.println(findPwResultDto);
			if (findPwResultDto != null && findPwResultDto.getUserEmail().equals(userDto.getUserEmail())) {
				// 랜덤 PW로 변경
				final String newPassword = getRandomPassword(12);
				findPwResultDto.setUserPassword(newPassword);
				// 변경된 PW로 DB에 반영 후, 메일 전송
				// ?? 트랜잭션..??
				if (userDao.updatePassword(findPwResultDto) == 1 && sendInitPwEmail(findPwResultDto)) {
					userResultDto.setResult(SUCCESS);
				} else {
					userResultDto.setResult(FAIL);
				}
			} else {
				userResultDto.setResult(INCORRECT_INFO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto updatePassword(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			if (userDao.updatePassword(userDto) == 1) {
				userResultDto.setDto(userDto);
				userResultDto.setResult(SUCCESS);
			} else {
				userResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
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
	
	private static boolean sendInitPwEmail(UserDto userDto) {

		final String charSet = "utf-8";
        final String hostSMTP = "smtp.naver.com";
        final String hostSMTPid = "pde0691";
        final String hostSMTPpwd = "ssafy6th!";

        final String fromEmail = "pde0691@naver.com";
        final String fromName = "HAPPY HOUSE";
        final String subject = "Happy House 계정 패스워드 초기화 정보입니다.";
        String msg = "<div style='border: 1px solid black; padding: 10px; font-family: verdana;'>";
        msg += "<h2>안녕하세요. <span style='color: blue;'>" + userDto.getUserName() + "</span>님.</h2>";
        msg += "<p>초기화된 비밀번호를 전송해 드립니다. 비밀번호를 변경하여 사용하세요.</p>";
        msg += "<p>임시 비밀번호 : <span style='color: blue;'>" + userDto.getUserPassword() + "</span></p></div>";

        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setSSLOnConnect(true);
            email.setHostName(hostSMTP);
            email.setSmtpPort(587);

            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.setStartTLSEnabled(true);
            email.addTo(userDto.getUserEmail(), userDto.getUserName(), charSet);
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
	public UserResultDto userFileInsert(UserDto userDto, MultipartHttpServletRequest request) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			
			List<MultipartFile> fileList = request.getFiles("file");
			
			// 파일 경로 찾기
	        File uploadDir = new File(uploadPath + File.separator + uploadFolder);
	        if (!uploadDir.exists()) uploadDir.mkdir();
	        
	        // 물리 파일 삭제, 첨부파일 여러개 고려
//	        List<String> fileUrlList = userDao.userFileUrlDeleteList(userDto.getUserId());    
//	        for(String fileUrl : fileUrlList) {    
//	            File file = new File(uploadPath + File.separator, fileUrl);
//	            if(file.exists()) {
//	                file.delete();
//	            }
//	        }
	        
//	        userDao.userFileDelete(userDto.getUserId()); // 테이블 파일 삭제
			
	        for (MultipartFile part : fileList) {
	            String userId = userDto.getUserId();
	            
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
	            UserImgFileDto userFileDto = new UserImgFileDto();
//	            userFileDto.setUserId(userId);
	            userFileDto.setFileName(fileName);
	            userFileDto.setFileSize(part.getSize());
	            userFileDto.setFileContentType(part.getContentType());
	            String userFileUrl = "/" + uploadFolder + "/" + savingFileName;
	            userFileDto.setFileUrl(userFileUrl);
	            
//	            userDao.userFileInsert(userFileDto);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	
	@Override
	public UserResultDto userProfileImage(UserDto userDto, MultipartHttpServletRequest request) {
		UserResultDto userResultDto = new UserResultDto();
	    
	    try {
	    	if( userDto.getUserSeq() == userDto.getUserSeq() ) {
	    		userDto.setSameUser(true);
	        }else {
	        	userDto.setSameUser(false);
	        }
	    	
//	        List<UserImgFileDto> fileList = userDao.noticeDetailFileList(userDto.getUserSeq());
	
//	        userDto.setFileList(fileList);
	        userResultDto.setDto(userDto);
	
	        userResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        userResultDto.setResult(FAIL);
	    }
	    return userResultDto;
	}

	@Override
	public UserResultDto friendSearch(String searchWord, UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		List<UserDto> list = null;
		try {
			list = userDao.friendSearch(searchWord);
			
			List<FriendDto> friendList = friendDao.friendFollowing(userDto.getUserId());
			
			for (UserDto user : list) {
				for (FriendDto friend : friendList) {
					if (user.getUserId().equals(friend.getToId())) {
						user.setFriend(true);
					}
				}
				if (user.getUserId().equals(userDto.getUserId())) {
					user.setSameUser(true);
				}
			}

			userResultDto.setUserDto(list);
			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
}
