package com.ssafy.happyhouse.service;

import java.util.Random;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	private static final int SUCCESS = 1;
	private static final int INCORRECT_INFO = 2;
	private static final int FAIL = -1;

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
}
