package gg.om.omgg.web;

import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import org.junit.After;
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

    @After
    public void tearDown() throws Exception {
        summonerRepository.deleteAll();
    }

    @Test
    public void 메인페이지_로딩() {
        String body = this.restTemplate.getForObject("/", String.class);

        assertThat(body).contains("OMGG");
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

        String url = "http://localhost:" + port + "/userName=거세짱123";

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
    public void 이름으로_소환사_찾기_case_DB에_정보_없음() throws Exception {

        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        //int profileIconId = 11;
        //long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        //long summonerLevel = 294;

        String url = "http://localhost:" + port + "/userName=거세짱123";

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
    public void 이름으로_소환사_찾기_case_존재하지않는_소환사_검색() throws Exception {
        String url = "http://localhost:" + port + "/userName=거세짱999킹재욱";

        String body = this.restTemplate.getForObject(url, String.class);
        assertThat(body).contains("OMGG에 등록되지 않은 소환사입니다. 오타를 확인 후 다시 검색해주세요.");
    }
}
