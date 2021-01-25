package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchListDTO;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchListParserTests {
    @Test
    public void matchlist_parser_테스트() {
        MatchListParser parser = new MatchListParser();
        Optional<MatchListDTO> dto = parser.getJSONData("yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr");

        assertThat(dto.get().getEndIndex()).isEqualTo(20);
        assertThat(dto.get().getStartIndex()).isEqualTo(0);
        assertThat(dto.get().getMatches().size()).isEqualTo(20);
    }
}
