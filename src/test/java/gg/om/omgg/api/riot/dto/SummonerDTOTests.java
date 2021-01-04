package gg.om.omgg.api.riot.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SummonerDTOTests {

    @Test
    public void 롬복_적용_테스트() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293;

        SummonerDTO dto = new SummonerDTO(accountId, profileIconId, revisionDate, name, id, puuid, summonerLevel);

        assertThat(dto.getAccountId()).isEqualTo(accountId);
        assertThat(dto.getProfileIconId()).isEqualTo(profileIconId);
        assertThat(dto.getRevisionDate()).isEqualTo(revisionDate);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getPuuid()).isEqualTo(puuid);
        assertThat(dto.getSummonerLevel()).isEqualTo(summonerLevel);
    }
}
