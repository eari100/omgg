package gg.om.omgg.api.riot.dto;

import org.junit.Test;

public class SummonerDTOTests {

    @Test
    public void 롬복_적용_테스트() {
        SummonerDTO dto = new SummonerDTO();
        // @Getter
        dto.getAccountId();
        dto.getProfileIconId();
        dto.getRevisionDate();
        dto.getName();
        dto.getId();
        dto.getPuuid();
        dto.getSummonerLevel();
    }
}
