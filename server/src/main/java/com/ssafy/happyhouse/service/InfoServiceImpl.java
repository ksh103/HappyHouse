package com.ssafy.happyhouse.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.InfoDao;
import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.GuDto;
import com.ssafy.happyhouse.dto.InfoResultDto;
import com.ssafy.happyhouse.dto.NewsDto;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	InfoDao infoDao;
	
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;
    private static final String clientId = "YE6pcP8mN1UBsI36owQ9";
    private static final String clientSecret = "hcKPaPKtVe";
    
	@Override
	public InfoResultDto getGu(int siCode) {
		InfoResultDto infoResultDto = new InfoResultDto();
		List<GuDto> list = null;
		try {
			list = infoDao.getGu(siCode);
			if (!list.isEmpty()) {
				infoResultDto.setGuDto(list);;
				infoResultDto.setResult(SUCCESS);
			} else {
				infoResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			infoResultDto.setResult(FAIL);
		}
		return infoResultDto;
	}
	@Override
	public InfoResultDto getDong(int dongCode) {
		InfoResultDto infoResultDto = new InfoResultDto();
		List<DongDto> list = null;
		try {
			list = infoDao.getDong(dongCode);
			if (!list.isEmpty()) {
				infoResultDto.setDongDto(list);;
				infoResultDto.setResult(SUCCESS);
			} else {
				infoResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			infoResultDto.setResult(FAIL);
		}
		return infoResultDto;
	}
	@Override
	public InfoResultDto getNews() {
		InfoResultDto infoResultDto = new InfoResultDto();
		List<NewsDto> list = null;
		try {
			String apiURL = String.format("https://openapi.naver.com/v1/search/news.json?query=%s&display=5&sort=date", URLEncoder.encode("부동산", "UTF-8"));
 
			Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        
	        String responseBody = get(apiURL,requestHeaders);
	        System.out.println(responseBody);
	        
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(responseBody);
	        JSONArray items = (JSONArray) obj.get("items");
	        list = new ArrayList<>();
	        for (int i = 0; i < items.size(); i++) {
	        	NewsDto newsDto = new NewsDto();
	        	JSONObject item = (JSONObject) items.get(i);
	        	
	        	newsDto.setTitle((String) item.get("title"));
	        	newsDto.setLink((String) item.get("link"));
	        	
	        	list.add(newsDto);
	        }
	        infoResultDto.setNewsDto(list);
	        infoResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			infoResultDto.setResult(FAIL);
		}
		return infoResultDto;
	}
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
