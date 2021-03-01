package gg.om.omgg.domain.match;


import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchReferenceRepositoryTests {

    @Autowired
    MatchReferenceRepository matchReferenceRepository;
    @Autowired
    SummonerRepository summonerRepository;

    @After
    public void cleanUp() {
        matchReferenceRepository.deleteAll();
        summonerRepository.deleteAll();
    }

    @Test
    public void fk_제약조건_있는_match_저장() {

        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 300L;

        long gameId = 4950311540L;

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

        matchReferenceRepository.save(MatchReference.builder()
                .id(
                        MatchReferenceId.builder()
                        .summoner(summoner)
                        .gameId(gameId)
                        .build()
                )
                .build()
        );

        List<MatchReference> matchReferenceList = matchReferenceRepository.findAll();
        MatchReference matchReference = matchReferenceList.get(0);
        assertThat(matchReference.getId().getSummoner().getAccountId()).isEqualTo(accountId);
        assertThat(matchReference.getId().getGameId()).isEqualTo(gameId);
    }

    @Test
    public void matchReference_saveAll() {

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

        List<MatchReference> saveMatchReferenceList = new ArrayList<>();

        for(long gameId : gameIds) {
            saveMatchReferenceList.add(
                    MatchReference.builder()
                            .id(
                                    MatchReferenceId.builder()
                                            .summoner(summoner)
                                            .gameId(gameId)
                                            .build()
                            )
                            .build()
            );
        }

        // fk 제약조건 때문에 미리 저장
        summonerRepository.save(summoner);

        matchReferenceRepository.saveAll(saveMatchReferenceList);

        List<MatchReference> selectMatchReferenceList = matchReferenceRepository.findAll();

        // DB indexing으로 인해 오름차순
        Arrays.sort(gameIds);
        int gameId_index = 0;
        for(MatchReference matchReference : selectMatchReferenceList) {
            assertThat(matchReference.getId().getSummoner().getAccountId()).isEqualTo(accountId);
            assertThat(matchReference.getId().getGameId()).isEqualTo(gameIds[gameId_index++]);
        }
    }

}