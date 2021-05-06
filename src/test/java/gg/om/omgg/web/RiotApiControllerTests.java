package gg.om.omgg.web;

import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.RenewRequestDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
    public void summoner_갱신() throws Exception {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 290L;

        long[] gameIds = {
                4954204682L, 4954109151L, 4951865595L, 4951709489L, 4951119896L,
                4950311540L, 4949572489L, 4949471123L, 4949055012L, 4948045142L,
                4947938785L, 4947838960L, 4947852300L, 4946666634L, 4946590274L,
                4946361968L, 4944515686L, 4943127213L, 4943131472L, 4943037678L
        };

        Summoner summoner = Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build();

        summonerRepository.save(summoner);

        RenewRequestDTO requestDto = RenewRequestDTO.builder()
                .name(name)
                .build();

        HttpEntity<RenewRequestDTO> requestEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:" + port + "/api/renew";

        ResponseEntity responseEntity = restTemplate.postForEntity(url, requestEntity, Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Summoner> all = summonerRepository.findAll();
        assertThat(all.get(0).getAccountId()).isEqualTo(accountId);
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getPuuid()).isEqualTo(puuid);
    }
}
