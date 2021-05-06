package gg.om.omgg.config;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.match.QMatch;
import gg.om.omgg.domain.summoner.QSummoner;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuerydslConfigurationTests {
    @Autowired
    JPASQLQuery<?> jpasqlQuery;

    @Autowired
    SummonerRepository summonerRepository;
    @Autowired
    MatchRepository matchRepository;

    @Test
    public void JPASQLQuery적용Test() {
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

        Long[] gameIds = {5115327873L, 5114612211L, 5114509070L, 5113679956L, 5113381568L,
                5113123898L, 5113140714L, 5113009318L, 5113093150L, 5113040393L,
                5112862930L, 5112801333L, 5111953625L, 5111855885L, 5111560760L,
                5111318269L, 5110948806L, 5110712716L, 5110647401L, 5110663852L};
        Arrays.sort(gameIds, Comparator.reverseOrder());

        int[] queueIds = {420, 450, 420, 420, 420, 450, 450, 450, 450, 450, 420, 450, 420, 450, 450, 450, 420, 450, 450, 450};
        String platformId = "KR";
        int seasonId = 13;

        for (int i = 0; i < 20; i++) {
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

        QSummoner qsummoner = QSummoner.summoner;
        QSummoner qsummoner_match1 = new QSummoner("summoner_match");
        QMatch qsummoner_match2 = new QMatch("summoner_match");
        QMatch qmatch = QMatch.match;

        List<Tuple> result = jpasqlQuery.select(qsummoner, qmatch, SQLExpressions.rowNumber().over())
                .from(qsummoner)
                .leftJoin(qsummoner_match1).on(qsummoner.accountId.eq(qsummoner_match1.accountId))
                .leftJoin(qmatch).on(qsummoner_match2.gameId.eq(qmatch.gameId))
                .where(qsummoner.name.eq(name))
                .offset(0)
                .limit(20)
                .orderBy(qmatch.gameId.desc())
                .fetch();

        for(int i=0;i<result.size();i++) {
            Match match = result.get(i).get(qmatch);
            assertThat(match.getGameId()).isEqualTo(gameIds[i]);
            assertThat(match.getQueueId()).isEqualTo(queueIds[i]);
            assertThat(match.getPlatformId()).isEqualTo(platformId);
            assertThat(match.getSeasonId()).isEqualTo(seasonId);
        }
    }
}
