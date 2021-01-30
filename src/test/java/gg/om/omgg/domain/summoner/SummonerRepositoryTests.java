package gg.om.omgg.domain.summoner;

import gg.om.omgg.domain.match.MatchReference;
import gg.om.omgg.domain.match.MatchReferenceId;
import gg.om.omgg.domain.match.MatchReferenceRepository;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
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

    @Autowired
    private MatchReferenceRepository matchReferenceRepository;

    @After
    public void cleanUp() {
        matchReferenceRepository.deleteAll();
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
    public void QuerydslLeftJoin_summoner_and_match() {
        // given
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 300L;

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

        // fk 제약조건 때문에 미리 저장
        summonerRepository.save(summoner);

        for(long gameId : gameIds) {
            matchReferenceRepository.save(MatchReference.builder()
                    .id(
                            MatchReferenceId.builder()
                                    .summoner(summoner)
                                    .gameId(gameId)
                                    .build()
                    )
                    .build()
            );
        }

        // when
        List<SummonerIntegrationInformationResponseDTO> matchList = summonerRepository.findSummonerIntegrationInformationByName(name);

        // then
        assertThat(matchList.size()).isEqualTo(20);
    }
}
