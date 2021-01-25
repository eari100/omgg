package gg.om.omgg.api.riot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gg.om.omgg.api.riot.Key;
import gg.om.omgg.api.riot.dto.MatchListDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Optional;

public class MatchListParser {

    public Optional<MatchListDTO> getJSONData(String accountId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestURL = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/" + accountId
                + "?api_key=" + Key.api_key + "&endIndex=20&beginIndex=0";
        Optional<MatchListDTO> matchList = Optional.empty();

        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
            HttpResponse response = client.execute(getRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                matchList = Optional.of(objectMapper.readValue(body, MatchListDTO.class)); // String to Object로 변환
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchList;
    }
}
