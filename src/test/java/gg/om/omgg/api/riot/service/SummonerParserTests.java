package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SummonerParserTests {
    @Test
    public void httpclient_적용_테스트() {
        SummonerParser parser = new SummonerParser();
        SummonerDTO dto = new SummonerDTO();

        dto = parser.getJSONData("거세짱123");
        assertThat(dto.getId()).isEqualTo("qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI");
        assertThat(dto.getAccountId()).isEqualTo("yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr");
        assertThat(dto.getPuuid()).isEqualTo("blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ");
        // 아이콘
        //assertThat(dto.getProfileIconId()).isEqualTo(11);
        assertThat(dto.getRevisionDate()).isEqualTo(1609294136000L);
        // 레벨
        //assertThat(dto.getSummonerLevel()).isEqualTo(293);
    }
}
