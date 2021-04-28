package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
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
    MatchRepository matchRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    SummonerService summonerService;
    @Autowired
    SummonerParser summonerParser;

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
        participantRepository.deleteAll();
        matchRepository.deleteAll();
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

        SummonerIntegrationInformationResponseDTO DBData = summonerRepository.findSummonerIntegrationInformationByName(name);
        Optional<SummonerDTO> summonerDTO = summonerParser.getJSONData(name);
        assertThat(DBData.getProfileIconId()).isEqualTo(summonerDTO.get().getProfileIconId());
        assertThat(DBData.getName()).isEqualTo(summonerDTO.get().getName());
        assertThat(DBData.getId()).isEqualTo(summonerDTO.get().getId());
        assertThat(DBData.getSummonerLevel()).isEqualTo(summonerDTO.get().getSummonerLevel());
        assertThat(DBData.getMatches().size()).isEqualTo(20);
        System.out.println("======gameId들을 출력합니다.======");
        DBData.getMatches().stream().forEach(match -> System.out.println(match.getGameId()));
    }

    @Test
    public void 소환사가_라이엇서버존재O_DB존재X_할때_소환사정보갱신() {
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        summonerService.renewData(name, id);

        SummonerIntegrationInformationResponseDTO DBData = summonerRepository.findSummonerIntegrationInformationByName(name);
        Optional<SummonerDTO> summonerDTO = summonerParser.getJSONData(name);
        assertThat(DBData.getProfileIconId()).isEqualTo(summonerDTO.get().getProfileIconId());
        assertThat(DBData.getName()).isEqualTo(summonerDTO.get().getName());
        assertThat(DBData.getId()).isEqualTo(summonerDTO.get().getId());
        assertThat(DBData.getSummonerLevel()).isEqualTo(summonerDTO.get().getSummonerLevel());
        assertThat(DBData.getMatches().size()).isEqualTo(20);
        System.out.println("======gameId들을 출력합니다.======");
        DBData.getMatches().stream().forEach(match -> System.out.println(match.getGameId()));
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

        // summoner.name을 공백으로 update 했기때문에 id로 조회해야 됩니다.
        Optional<Summoner> DBData = summonerRepository.findById(id);
        assertThat(DBData.get().getAccountId()).isEqualTo(accountId);
        assertThat(DBData.get().getProfileIconId()).isEqualTo(profileIconId);
        assertThat(DBData.get().getRevisionDate()).isEqualTo(revisionDate);
        // 공백으로 update 되었는 지 check
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
