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

import java.util.List;

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
    public void Summoner_등록() throws Exception {
        SummonerDTO summonerDTO = new SummonerDTO();

        String url = "http://localhost:" + port + "/lol/summoners/by-name/거세짱123";

        ResponseEntity responseEntity = restTemplate.postForEntity(url, summonerDTO, void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Summoner> all = summonerRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo("거세짱123");
        assertThat(all.get(0).getId()).isEqualTo("qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI");
        assertThat(all.get(0).getAccountId()).isEqualTo("yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr");
        assertThat(all.get(0).getPuuid()).isEqualTo("blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ");
        assertThat(all.get(0).getProfileIconId()).isEqualTo(11);
        assertThat(all.get(0).getRevisionDate()).isEqualTo(1609294136000L);
        assertThat(all.get(0).getSummonerLevel()).isEqualTo(294);
    }

    @Test
    public void Summoner_이름_검색() throws Exception {

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
}
