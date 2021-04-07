package gg.om.omgg.api.riot.service;

import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummonerServiceTests {

    @Autowired
    SummonerRepository summonerRepository;

    @Autowired
    SummonerService summonerService;

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
    }

    @Test
    public void 수정하기() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long oldSummonerLevel = 293L;

        summonerRepository.save(Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(oldSummonerLevel)
                .build()
        );

        long newSummonerLevel = 300L;

        Summoner summoner = Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(newSummonerLevel)
                .build();

        summonerService.update(id, summoner);

        List<Summoner> summoners = summonerRepository.findAll();
        assertThat(summoners.get(0).getSummonerLevel()).isEqualTo(newSummonerLevel);
    }
}
