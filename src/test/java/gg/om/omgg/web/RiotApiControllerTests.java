package gg.om.omgg.web;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.dto.SummonerResponseDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RiotApiControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SummonerRepository summonerRepository;

    @After
    public void tearDown() throws Exception {
        summonerRepository.deleteAll();
    }

    @Test
    public void 이름으로_소환사_찾기_case_DB에_정보_존재() throws Exception {

        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 294;

        summonerRepository.save(Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build()
        );

        String url = "http://localhost:" + port + "/lol/summoners/by-name/거세짱123";

        ResponseEntity responseEntity = restTemplate.getForEntity(url, Object.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void 이름으로_소환사_찾기_case_DB에_정보_없음() throws Exception {

        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 294;

        String url = "http://localhost:" + port + "/lol/summoners/by-name/거세짱123";

        ResponseEntity responseEntity = restTemplate.getForEntity(url, Object.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void 이름으로_소환사_찾기_case_존재하지않는_소환사_검색() throws Exception {
        String url = "http://localhost:" + port + "/lol/summoners/by-name/거세짱999";

        ResponseEntity responseEntity = restTemplate.getForEntity(url, Object.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(responseEntity.getBody());
    }
}
