package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummonerServiceTests {

    @Autowired
    SummonerRepository summonerRepository;

    @Autowired
    SummonerService summonerService;

    @Autowired
    SummonerParser summonerParser;

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
    }

    @Test
    public void 소환사가_라이엇서버존재O_DB존재O_할때_소환사정보갱신() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293L;

        summonerRepository.save(Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build()
        );
        summonerService.renewData(name, id);

        Optional<Summoner> DBData = summonerRepository.findById(id);
        Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
        assertThat(DBData.get().getProfileIconId()).isEqualTo(JSONData.get().getProfileIconId());
        assertThat(DBData.get().getRevisionDate()).isEqualTo(JSONData.get().getRevisionDate());
        assertThat(DBData.get().getName()).isEqualTo(JSONData.get().getName());
        assertThat(DBData.get().getId()).isEqualTo(JSONData.get().getId());
        assertThat(DBData.get().getPuuid()).isEqualTo(JSONData.get().getPuuid());
        assertThat(DBData.get().getSummonerLevel()).isEqualTo(JSONData.get().getSummonerLevel());
    }

    @Test
    public void 소환사가_라이엇서버존재O_DB존재X_할때_소환사정보갱신() {
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        summonerService.renewData(name, id);

        Optional<Summoner> DBData = summonerRepository.findById(id);
        Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
        assertThat(DBData.get().getProfileIconId()).isEqualTo(JSONData.get().getProfileIconId());
        assertThat(DBData.get().getRevisionDate()).isEqualTo(JSONData.get().getRevisionDate());
        assertThat(DBData.get().getName()).isEqualTo(JSONData.get().getName());
        assertThat(DBData.get().getId()).isEqualTo(JSONData.get().getId());
        assertThat(DBData.get().getPuuid()).isEqualTo(JSONData.get().getPuuid());
        assertThat(DBData.get().getSummonerLevel()).isEqualTo(JSONData.get().getSummonerLevel());
    }

    @Test
    public void 소환사가_라이엇서버존재X_DB존재O_할때_소환사정보갱신() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        // 존재하지 않는 소환사명
        String name = "거세짱황제맨";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293L;

        summonerRepository.save(Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build()
        );
        summonerService.renewData(name, id);

        Optional<Summoner> DBData = summonerRepository.findById(id);
        summonerParser.getJSONData(name);
        assertThat(DBData.get().getAccountId()).isEqualTo(accountId);
        assertThat(DBData.get().getProfileIconId()).isEqualTo(profileIconId);
        assertThat(DBData.get().getRevisionDate()).isEqualTo(revisionDate);
        assertThat(DBData.get().getName()).isEqualTo("");
        assertThat(DBData.get().getId()).isEqualTo(id);
        assertThat(DBData.get().getPuuid()).isEqualTo(puuid);
        assertThat(DBData.get().getSummonerLevel()).isEqualTo(summonerLevel);
    }

    @Test
    public void 소환사가_라이엇서버존재X_DB존재X_할때_소환사정보갱신() {
        // 존재하지 않는 소환사명
        String name = "거세짱황제맨";
        // 존재하지 않는 ID
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI-xxxxxxx";

        summonerService.renewData(name, id);

        Optional<Summoner> DBData = summonerRepository.findById(id);
        assertThat(DBData.isPresent()).isEqualTo(false);
    }
}
