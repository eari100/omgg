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
import java.util.Optional;

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
        long summonerLevel = 290;

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

        RenewRequestDTO requestDto = RenewRequestDTO.builder()
                .id(id)
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

    @Test
    public void summoner_갱신_case_만약_소환사_이름이_바꼈을때() throws Exception {
        String accountId = "Ih71S9n9QHH6jtIVOch_ahuOw6z-LDjVMVZ7wEukeSyw";
        int profileIconId = 4834;
        long revisionDate = 1610525078000L;
        String name = "jooGosu"; // 예전 소환사명
        String id = "tdFWSOiOLgAZikScgZgUH_AhMnlfGmUwCcn4eWYkamkKQfo";
        String puuid = "gBuJoXbuKp6WOKu6ajO7g_tycL57HU3ugsinPRoTZzrqgQOefbri7xloSHX7dATOYgFwvjhEXrgXAQ";
        long summonerLevel = 255;

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

        RenewRequestDTO requestDto = RenewRequestDTO.builder()
                .id(id)
                .name(name)
                .build();

        HttpEntity<RenewRequestDTO> requestEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:" + port + "/api/renew";

        ResponseEntity responseEntity = restTemplate.postForEntity(url, requestEntity, Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Optional<Summoner> oldSummoner = summonerRepository.findByName(name);
        assertThat(oldSummoner.isEmpty()).isEqualTo(true);
    }
}
