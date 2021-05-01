package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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
        summonerService.renewData(name);

        List<Object[]> result = summonerRepository.findIntegrationInfoByName(name, 200);
        List<IntegrationInfoResponseDTO> dtoList = new ArrayList<>();

        for(Object[] obj : result) {
            dtoList.add(
                    IntegrationInfoResponseDTO.builder()
                            .profileIconId((int)obj[0])
                            .name((String)obj[1])
                            .summonerLevel(((Number)obj[2]).longValue())
                            .id((String)obj[3])

                            .gameId(((Number)obj[4]).longValue())
                            .queueId((int)obj[5])
                            .platformId((String)obj[6])
                            .seasonId((int)obj[7])
                            .gameCreation(((Number)obj[8]).longValue())
                            .gameDuration(((Number)obj[9]).longValue())

                            .championId((int)obj[10])
                            .spell1Id((int)obj[11])
                            .spell2Id((int)obj[12])
                            .item0((int)obj[13])
                            .item1((int)obj[14])
                            .item2((int)obj[15])
                            .item3((int)obj[16])
                            .item4((int)obj[17])
                            .item5((int)obj[18])
                            .item6((int)obj[19])
                            .kills((int)obj[20])
                            .deaths((int)obj[21])
                            .assists((int)obj[22])
                            .perk0((int)obj[23])
                            .perkSubStyle((int)obj[24])
                            .champLevel((int)obj[25])
                            .totalMinionsKilled((int)obj[26])
                            .neutralMinionsKilled((int)obj[27])
                            .visionWardsBoughtInGame((int)obj[28])

                            .player1ChampionId((int)obj[29])
                            .player2ChampionId((int)obj[30])
                            .player3ChampionId((int)obj[31])
                            .player4ChampionId((int)obj[32])
                            .player5ChampionId((int)obj[33])
                            .player6ChampionId((int)obj[34])
                            .player7ChampionId((int)obj[35])
                            .player8ChampionId((int)obj[36])
                            .player9ChampionId((int)obj[37])
                            .player10ChampionId((int)obj[38])
                            .player1SummonerName((String)obj[39])
                            .player2SummonerName((String)obj[40])
                            .player3SummonerName((String)obj[41])
                            .player4SummonerName((String)obj[42])
                            .player5SummonerName((String)obj[43])
                            .player6SummonerName((String)obj[44])
                            .player7SummonerName((String)obj[45])
                            .player8SummonerName((String)obj[46])
                            .player9SummonerName((String)obj[47])
                            .player10SummonerName((String)obj[48])
                            .win((boolean)obj[49])
                            .build()
            );
        }

        Optional<SummonerDTO> summonerDTO = summonerParser.getJSONData(name);
        assertThat(dtoList.get(0).getProfileIconId()).isEqualTo(summonerDTO.get().getProfileIconId());
        assertThat(dtoList.get(0).getName()).isEqualTo(summonerDTO.get().getName());
        assertThat(dtoList.get(0).getId()).isEqualTo(summonerDTO.get().getId());
        assertThat(dtoList.get(0).getSummonerLevel()).isEqualTo(summonerDTO.get().getSummonerLevel());
    }

    @Test
    public void 소환사가_라이엇서버존재O_DB존재X_할때_소환사정보갱신() {
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        summonerService.renewData(name);

        List<Object[]> result = summonerRepository.findIntegrationInfoByName(name, 200);
        List<IntegrationInfoResponseDTO> dtoList = new ArrayList<>();

        for(Object[] obj : result) {
            dtoList.add(
                    IntegrationInfoResponseDTO.builder()
                            .profileIconId((int)obj[0])
                            .name((String)obj[1])
                            .summonerLevel(((Number)obj[2]).longValue())
                            .id((String)obj[3])

                            .gameId(((Number)obj[4]).longValue())
                            .queueId((int)obj[5])
                            .platformId((String)obj[6])
                            .seasonId((int)obj[7])
                            .gameCreation(((Number)obj[8]).longValue())
                            .gameDuration(((Number)obj[9]).longValue())

                            .championId((int)obj[10])
                            .spell1Id((int)obj[11])
                            .spell2Id((int)obj[12])
                            .item0((int)obj[13])
                            .item1((int)obj[14])
                            .item2((int)obj[15])
                            .item3((int)obj[16])
                            .item4((int)obj[17])
                            .item5((int)obj[18])
                            .item6((int)obj[19])
                            .kills((int)obj[20])
                            .deaths((int)obj[21])
                            .assists((int)obj[22])
                            .perk0((int)obj[23])
                            .perkSubStyle((int)obj[24])
                            .champLevel((int)obj[25])
                            .totalMinionsKilled((int)obj[26])
                            .neutralMinionsKilled((int)obj[27])
                            .visionWardsBoughtInGame((int)obj[28])

                            .player1ChampionId((int)obj[29])
                            .player2ChampionId((int)obj[30])
                            .player3ChampionId((int)obj[31])
                            .player4ChampionId((int)obj[32])
                            .player5ChampionId((int)obj[33])
                            .player6ChampionId((int)obj[34])
                            .player7ChampionId((int)obj[35])
                            .player8ChampionId((int)obj[36])
                            .player9ChampionId((int)obj[37])
                            .player10ChampionId((int)obj[38])
                            .player1SummonerName((String)obj[39])
                            .player2SummonerName((String)obj[40])
                            .player3SummonerName((String)obj[41])
                            .player4SummonerName((String)obj[42])
                            .player5SummonerName((String)obj[43])
                            .player6SummonerName((String)obj[44])
                            .player7SummonerName((String)obj[45])
                            .player8SummonerName((String)obj[46])
                            .player9SummonerName((String)obj[47])
                            .player10SummonerName((String)obj[48])
                            .win((boolean)obj[49])
                            .build()
            );
        }

        Optional<SummonerDTO> summonerDTO = summonerParser.getJSONData(name);
        assertThat(dtoList.get(0).getProfileIconId()).isEqualTo(summonerDTO.get().getProfileIconId());
        assertThat(dtoList.get(0).getName()).isEqualTo(summonerDTO.get().getName());
        assertThat(dtoList.get(0).getId()).isEqualTo(summonerDTO.get().getId());
        assertThat(dtoList.get(0).getSummonerLevel()).isEqualTo(summonerDTO.get().getSummonerLevel());
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
        summonerService.renewData(name);

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

        summonerService.renewData(name);

        Optional<Summoner> DBData = summonerRepository.findById(id);
        assertThat(DBData.isPresent()).isEqualTo(false);
    }

    @Test
    public void matchesList_더보기() {
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

        summonerService.matchesListLeadMore(id, accountId, 400);
    }
}
