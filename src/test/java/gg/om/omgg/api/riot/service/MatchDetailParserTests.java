package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchDTO;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchDetailParserTests {
    @Test
    public void matchDetail_parser_테스트() {
        MatchDetailParser parser = new MatchDetailParser();
        Optional<MatchDTO> dto = parser.getJSONData(4773318406L);

        assertThat(dto.get().getGameId()).isEqualTo(4773318406L);
        assertThat(dto.get().getQueueId()).isEqualTo(450);
        assertThat(dto.get().getGameType()).isEqualTo("MATCHED_GAME");
    }
}
