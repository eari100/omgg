package gg.om.omgg.web;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
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
    }

}
