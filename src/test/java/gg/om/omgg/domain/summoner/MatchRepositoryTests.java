package gg.om.omgg.domain.summoner;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchId;
import gg.om.omgg.domain.match.MatchRepository;
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
public class MatchRepositoryTests {

    @Autowired
    MatchRepository matchRepository;

    @After
    public void cleanUp() { matchRepository.deleteAll(); }

    @Test
    public void 매치_저장() {
        String accoutId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        Long gameId = 4950311540L;

        matchRepository.save(Match.builder()
                .id(
                        MatchId.builder()
                        .accountId(accoutId)
                        .gameId(gameId)
                        .build()
                )
                .build()
        );

        List<Match> matchList = matchRepository.findAll();
        Match match = matchList.get(0);
        assertThat(match.getId().getAccountId()).isEqualTo(accoutId);
        assertThat(match.getId().getGameId()).isEqualTo(gameId);
    }
}
