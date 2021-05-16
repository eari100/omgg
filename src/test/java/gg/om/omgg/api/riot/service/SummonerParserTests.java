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
        Optional<SummonerDTO> dto = parser.getJSONData("디아블로한다");

        assertThat(dto.get().getName()).isEqualTo("디아블로한다");
        assertThat(dto.get().getId()).isEqualTo("SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w");
        assertThat(dto.get().getAccountId()).isEqualTo("rbNJhW3DdOJ_6pQtyFMOMOvpO20qHDSFbj_pKvxckH8");
        assertThat(dto.get().getPuuid()).isEqualTo("gMPAG5C3sFtOyfn058rTaR86Ha78nQlZHJF0jEOIOozDLO-uTIa9QqcRK6e0uqXeHk9lLxw0Lra_gg");
        // 아이콘
        //assertThat(dto.get().getProfileIconId()).isEqualTo(11);
        //assertThat(dto.get().getRevisionDate()).isEqualTo(1609294136000L);
        // 레벨
        //assertThat(dto.get().getSummonerLevel()).isEqualTo(293);
    }
}
