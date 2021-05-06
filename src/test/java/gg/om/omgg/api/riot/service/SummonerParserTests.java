package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SummonerParserTests {

    @Before
    public void 시간지연() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    @Test
    public void httpclient_적용_테스트() {

        SummonerParser parser = new SummonerParser();
        Optional<SummonerDTO> dto = parser.getJSONData("거세짱123");

        assertThat(dto.get().getName()).isEqualTo("거세짱123");
        assertThat(dto.get().getId()).isEqualTo("qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI");
        assertThat(dto.get().getAccountId()).isEqualTo("yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr");
        assertThat(dto.get().getPuuid()).isEqualTo("blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ");
        // 아이콘
        //assertThat(dto.get().getProfileIconId()).isEqualTo(11);
        //assertThat(dto.get().getRevisionDate()).isEqualTo(1609294136000L);
        // 레벨
        //assertThat(dto.get().getSummonerLevel()).isEqualTo(293);
    }
}
