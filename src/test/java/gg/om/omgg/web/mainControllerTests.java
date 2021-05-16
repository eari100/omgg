package gg.om.omgg.web;

import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class mainControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SummonerRepository summonerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    ParticipantRepository participantRepository;

    @Before
    public void 시간지연() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    @After
    public void tearDown() throws Exception {
        summonerRepository.deleteAll();
        participantRepository.deleteAll();
        matchRepository.deleteAll();
    }

    @Test
    public void 메인페이지_로딩() {
        String body = this.restTemplate.getForObject("/", String.class);

        assertThat(body).contains("OMGG");
    }

    @Test
    public void 이름으로_소환사_찾기_DB에는_정보_존재() throws Exception {

        String accountId = "rbNJhW3DdOJ_6pQtyFMOMOvpO20qHDSFbj_pKvxckH8";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "디아블로한다";
        String id = "SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w";
        String puuid = "gMPAG5C3sFtOyfn058rTaR86Ha78nQlZHJF0jEOIOozDLO-uTIa9QqcRK6e0uqXeHk9lLxw0Lra_gg";
        long summonerLevel = 294;

        String url = "http://localhost:" + port + "/userName=디아블로한다";

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

        String body = this.restTemplate.getForObject(url, String.class);

        assertThat(body).contains("OMGG");
    }

    @Test
    public void 이름으로_소환사_찾기_DB에는_정보_없음() throws Exception {

        String accountId = "rbNJhW3DdOJ_6pQtyFMOMOvpO20qHDSFbj_pKvxckH8";
        //int profileIconId = 11;
        //long revisionDate = 1609294136000L;
        String name = "디아블로한다";
        String id = "SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w";
        String puuid = "gMPAG5C3sFtOyfn058rTaR86Ha78nQlZHJF0jEOIOozDLO-uTIa9QqcRK6e0uqXeHk9lLxw0Lra_gg";
        //long summonerLevel = 294;

        String url = "http://localhost:" + port + "/userName=디아블로한다";

        String body = this.restTemplate.getForObject(url, String.class);
        assertThat(body).contains("OMGG");

        List<Summoner> all = summonerRepository.findAll();
        assertThat(all.get(0).getAccountId()).isEqualTo(accountId);
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getPuuid()).isEqualTo(puuid);
//        assertThat(all.get(0).getProfileIconId()).isEqualTo(profileIconId);
//        assertThat(all.get(0).getRevisionDate()).isEqualTo(revisionDate);
//        assertThat(all.get(0).getSummonerLevel()).isEqualTo(summonerLevel);
    }

    @Test
    public void 이름으로_소환사_찾기_존재하지않는_소환사_검색_DB에는_정보_없음() throws Exception {
        String url = "http://localhost:" + port + "/userName=거세짱999킹재욱";

        String body = this.restTemplate.getForObject(url, String.class);
        assertThat(body).contains("OMGG에 등록되지 않은 소환사입니다. 오타를 확인 후 다시 검색해주세요.");
    }
}
