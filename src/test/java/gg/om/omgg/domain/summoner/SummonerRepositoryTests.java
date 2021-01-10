package gg.om.omgg.domain.summoner;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummonerRepositoryTests {

    @Autowired
    SummonerRepository summonerRepository;

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
    }

    @Test
    public void 소환사_정보_불러오기() {

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

        List<Summoner> summonersList = summonerRepository.findAll();

        Summoner summoner = summonersList.get(0);
        assertThat(summoner.getAccountId()).isEqualTo(accountId);
        //assertThat(summoner.getProfileIconId()).isEqualTo(profileIconId);
        assertThat(summoner.getRevisionDate()).isEqualTo(revisionDate);
        assertThat(summoner.getName()).isEqualTo(name);
        assertThat(summoner.getId()).isEqualTo(id);
        assertThat(summoner.getPuuid()).isEqualTo(puuid);
        //assertThat(summoner.getSummonerLevel()).isEqualTo(summonerLevel);
    }

    @Test
    public void 이름으로_소환사_정보_불러오기() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293;

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

        Optional<Summoner> summoner = summonerRepository.findByName("거세짱123");
        assertThat(summoner.get().getAccountId()).isEqualTo(accountId);
        //assertThat(summoner.get().getProfileIconId()).isEqualTo(profileIconId);
        assertThat(summoner.get().getRevisionDate()).isEqualTo(revisionDate);
        assertThat(summoner.get().getName()).isEqualTo(name);
        assertThat(summoner.get().getId()).isEqualTo(id);
        assertThat(summoner.get().getPuuid()).isEqualTo(puuid);
        //assertThat(summoner.get().getSummonerLevel()).isEqualTo(summonerLevel);
    }
}
