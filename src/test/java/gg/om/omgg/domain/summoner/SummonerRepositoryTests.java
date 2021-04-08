package gg.om.omgg.domain.summoner;

import gg.om.omgg.api.riot.service.SummonerService;
import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummonerRepositoryTests {

    @Autowired
    SummonerRepository summonerRepository;

    @Autowired
    MatchRepository matchRepository;

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

    @Test
    public void summoner_삭제() {
        // 초기값 세팅
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

        summonerRepository.deleteById(id);

        Optional<Summoner> summoner = summonerRepository.findById(id);
        assertThat(summoner.isEmpty()).isEqualTo(true);
    }

    @Test
    public void summoner이랑match에_저장하면_summoner_match에도_저장되는지_테스트() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293;

        Summoner summoner = Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build();

        long[] gameIds = {5115327873L, 5114612211L, 5114509070L, 5113679956L, 5113381568L,
                5113123898L, 5113140714L, 5113009318L, 5113093150L, 5113040393L,
                5112862930L, 5112801333L, 5111953625L, 5111855885L, 5111560760L,
                5111318269L, 5110948806L, 5110712716L, 5110647401L, 5110663852L};

        Arrays.sort(gameIds);
        int[] queueIds = {420, 450, 420, 420, 420, 450, 450, 450, 450, 450, 420, 450, 420, 450, 450, 450, 420, 450, 450, 450};
        String platformId = "KR";
        int seasonId = 13;

        for(int i=0;i<20;i++) {
            Match match = matchRepository.save(Match.builder()
                    .gameId(gameIds[i])
                    .queueId(queueIds[i])
                    .platformId(platformId)
                    .seasonId(seasonId)
                    .build()
            );
            // 연관관계 추가
            summoner.getMatches().add(match);
        }
        summonerRepository.save(summoner);

        List<SummonerIntegrationInformationResponseDTO> matchsInfo = summonerRepository.findSummonerIntegrationInformationByName(name);
        for(int i=0;i<matchsInfo.size();i++) {
            assertThat(matchsInfo.get(i).getProfileIconId()).isEqualTo(profileIconId);
            assertThat(matchsInfo.get(i).getName()).isEqualTo(name);
            assertThat(matchsInfo.get(i).getSummonerLevel()).isEqualTo(summonerLevel);
            assertThat(matchsInfo.get(i).getId()).isEqualTo(id);
            assertThat(matchsInfo.get(i).getGameId()).isEqualTo(gameIds[i]);
        }
    }
}
