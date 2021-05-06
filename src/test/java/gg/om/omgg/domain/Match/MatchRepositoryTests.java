package gg.om.omgg.domain.Match;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchRepositoryTests {

    @Autowired
    MatchRepository matchRepository;

    @After
    public void cleanUp() { matchRepository.deleteAll(); }

    @Test
    public void 전체_경기_기록_조회() {
        long[] gameIds = {5115327873L, 5114612211L, 5114509070L, 5113679956L, 5113381568L,
                5113123898L, 5113140714L, 5113009318L, 5113093150L, 5113040393L,
                5112862930L, 5112801333L, 5111953625L, 5111855885L, 5111560760L,
                5111318269L, 5110948806L, 5110712716L, 5110647401L, 5110663852L};
        Arrays.sort(gameIds);
        int[] queueIds = {420, 450, 420, 420, 420, 450, 450, 450, 450, 450, 420, 450, 420, 450, 450, 450, 420, 450, 450, 450};
        String platformId = "KR";
        int seasonId = 13;
        long gameCreation = 1604932783165L;
        long gameDutation = 897L;

        for(int i=0;i<20;i++) {
            matchRepository.save(Match.builder()
                    .gameId(gameIds[i])
                    .queueId(queueIds[i])
                    .platformId(platformId)
                    .seasonId(seasonId)
                    .gameCreation(gameCreation)
                    .gameDuration(gameDutation)
                    .build()
            );
        }

        List<Match> matchList = matchRepository.findAll();

        for(int i=0;i<20;i++) {
            Match match = matchList.get(i);
            assertThat(match.getGameId()).isEqualTo(gameIds[i]);
            assertThat(match.getQueueId()).isEqualTo(queueIds[i]);
            assertThat(match.getPlatformId()).isEqualTo(platformId);
            assertThat(match.getSeasonId()).isEqualTo(seasonId);
            assertThat(match.getGameCreation()).isEqualTo(gameCreation);
            assertThat(match.getGameDuration()).isEqualTo(gameDutation);
        }
    }
}
