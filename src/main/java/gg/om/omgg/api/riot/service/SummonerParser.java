package gg.om.omgg.api.riot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gg.om.omgg.api.riot.Key;
import gg.om.omgg.api.riot.dto.SummonerDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SummonerParser {
    public SummonerDTO getJSONData(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ name + "?api_key=" + Key.api_key;
        SummonerDTO summoner = null;

        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
            HttpResponse response = client.execute(getRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                summoner = objectMapper.readValue(body, SummonerDTO.class); // String to Object로 변환
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return summoner;
    }
}
