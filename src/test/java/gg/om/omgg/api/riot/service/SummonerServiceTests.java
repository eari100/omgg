package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import org.junit.After;
import org.junit.Before;
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

    @Before
    public void 시간지연() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
        participantRepository.deleteAll();
        matchRepository.deleteAll();
    }

    @Test
    public void 소환사가_라이엇서버존재O_DB존재O_할때_소환사정보갱신() {
        String accountId = "rbNJhW3DdOJ_6pQtyFMOMOvpO20qHDSFbj_pKvxckH8";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "디아블로한다";
        String id = "SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w";
        String puuid = "gMPAG5C3sFtOyfn058rTaR86Ha78nQlZHJF0jEOIOozDLO-uTIa9QqcRK6e0uqXeHk9lLxw0Lra_gg";
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

        List<Object[]> result = summonerRepository.findIntegrationInfoByName(name, 0,200);
        List<IntegrationInfoResponseDTO> dtoList = new ArrayList<>();

        for(Object[] obj : result) {
            dtoList.add(
                    IntegrationInfoResponseDTO.builder()
                            .profileIconId(Integer.parseInt(String.valueOf(obj[0])))
                            .name((String)obj[1])
                            .summonerLevel(((Number)obj[2]).longValue())
                            .id((String)obj[3])

                            .gameId(((Number)obj[4]).longValue())
                            .queueId(Integer.parseInt(String.valueOf(obj[5])))
                            .platformId((String)obj[6])
                            .seasonId(Integer.parseInt(String.valueOf(obj[7])))
                            .gameCreation(((Number)obj[8]).longValue())
                            .gameDuration(((Number)obj[9]).longValue())

                            .championId(Integer.parseInt(String.valueOf(obj[10])))
                            .spell1Id(Integer.parseInt(String.valueOf(obj[11])))
                            .spell2Id(Integer.parseInt(String.valueOf(obj[12])))
                            .item0(Integer.parseInt(String.valueOf(obj[13])))
                            .item1(Integer.parseInt(String.valueOf(obj[14])))
                            .item2(Integer.parseInt(String.valueOf(obj[15])))
                            .item3(Integer.parseInt(String.valueOf(obj[16])))
                            .item4(Integer.parseInt(String.valueOf(obj[17])))
                            .item5(Integer.parseInt(String.valueOf(obj[18])))
                            .item6(Integer.parseInt(String.valueOf(obj[19])))
                            .kills(Integer.parseInt(String.valueOf(obj[20])))
                            .deaths(Integer.parseInt(String.valueOf(obj[21])))
                            .assists(Integer.parseInt(String.valueOf(obj[22])))
                            .perk0(Integer.parseInt(String.valueOf(obj[23])))
                            .perkSubStyle(Integer.parseInt(String.valueOf(obj[24])))
                            .champLevel(Integer.parseInt(String.valueOf(obj[25])))
                            .totalMinionsKilled(Integer.parseInt(String.valueOf(obj[26])))
                            .neutralMinionsKilled(Integer.parseInt(String.valueOf(obj[27])))
                            .visionWardsBoughtInGame(Integer.parseInt(String.valueOf(obj[28])))

                            .player1ChampionId(Integer.parseInt(String.valueOf(obj[29])))
                            .player2ChampionId(Integer.parseInt(String.valueOf(obj[30])))
                            .player3ChampionId(Integer.parseInt(String.valueOf(obj[31])))
                            .player4ChampionId(Integer.parseInt(String.valueOf(obj[32])))
                            .player5ChampionId(Integer.parseInt(String.valueOf(obj[33])))
                            .player6ChampionId(Integer.parseInt(String.valueOf(obj[34])))
                            .player7ChampionId(Integer.parseInt(String.valueOf(obj[35])))
                            .player8ChampionId(Integer.parseInt(String.valueOf(obj[36])))
                            .player9ChampionId(Integer.parseInt(String.valueOf(obj[37])))
                            .player10ChampionId(Integer.parseInt(String.valueOf(obj[38])))
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
        String name = "디아블로한다";
        summonerService.renewData(name);

        List<Object[]> result = summonerRepository.findIntegrationInfoByName(name, 0,200);
        List<IntegrationInfoResponseDTO> dtoList = new ArrayList<>();

        for(Object[] obj : result) {
            dtoList.add(
                    IntegrationInfoResponseDTO.builder()
                            .profileIconId(Integer.parseInt(String.valueOf(obj[0])))
                            .name((String)obj[1])
                            .summonerLevel(((Number)obj[2]).longValue())
                            .id((String)obj[3])

                            .gameId(((Number)obj[4]).longValue())
                            .queueId(Integer.parseInt(String.valueOf(obj[5])))
                            .platformId((String)obj[6])
                            .seasonId(Integer.parseInt(String.valueOf(obj[7])))
                            .gameCreation(((Number)obj[8]).longValue())
                            .gameDuration(((Number)obj[9]).longValue())

                            .championId(Integer.parseInt(String.valueOf(obj[10])))
                            .spell1Id(Integer.parseInt(String.valueOf(obj[11])))
                            .spell2Id(Integer.parseInt(String.valueOf(obj[12])))
                            .item0(Integer.parseInt(String.valueOf(obj[13])))
                            .item1(Integer.parseInt(String.valueOf(obj[14])))
                            .item2(Integer.parseInt(String.valueOf(obj[15])))
                            .item3(Integer.parseInt(String.valueOf(obj[16])))
                            .item4(Integer.parseInt(String.valueOf(obj[17])))
                            .item5(Integer.parseInt(String.valueOf(obj[18])))
                            .item6(Integer.parseInt(String.valueOf(obj[19])))
                            .kills(Integer.parseInt(String.valueOf(obj[20])))
                            .deaths(Integer.parseInt(String.valueOf(obj[21])))
                            .assists(Integer.parseInt(String.valueOf(obj[22])))
                            .perk0(Integer.parseInt(String.valueOf(obj[23])))
                            .perkSubStyle(Integer.parseInt(String.valueOf(obj[24])))
                            .champLevel(Integer.parseInt(String.valueOf(obj[25])))
                            .totalMinionsKilled(Integer.parseInt(String.valueOf(obj[26])))
                            .neutralMinionsKilled(Integer.parseInt(String.valueOf(obj[27])))
                            .visionWardsBoughtInGame(Integer.parseInt(String.valueOf(obj[28])))

                            .player1ChampionId(Integer.parseInt(String.valueOf(obj[29])))
                            .player2ChampionId(Integer.parseInt(String.valueOf(obj[30])))
                            .player3ChampionId(Integer.parseInt(String.valueOf(obj[31])))
                            .player4ChampionId(Integer.parseInt(String.valueOf(obj[32])))
                            .player5ChampionId(Integer.parseInt(String.valueOf(obj[33])))
                            .player6ChampionId(Integer.parseInt(String.valueOf(obj[34])))
                            .player7ChampionId(Integer.parseInt(String.valueOf(obj[35])))
                            .player8ChampionId(Integer.parseInt(String.valueOf(obj[36])))
                            .player9ChampionId(Integer.parseInt(String.valueOf(obj[37])))
                            .player10ChampionId(Integer.parseInt(String.valueOf(obj[38])))
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
        String accountId = "rbNJhW3DdOJ_6pQtyFMOMOvpO20qHDSFbj_pKvxckH8";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        // 존재하지 않는 소환사명
        String name = "거세짱황제맨";
        String id = "SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w";
        String puuid = "gMPAG5C3sFtOyfn058rTaR86Ha78nQlZHJF0jEOIOozDLO-uTIa9QqcRK6e0uqXeHk9lLxw0Lra_gg";
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
        String id = "SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w-xxxxxxx";

        summonerService.renewData(name);

        Optional<Summoner> DBData = summonerRepository.findById(id);
        assertThat(DBData.isPresent()).isEqualTo(false);
    }

    @Test
    public void matchesList_더보기() {
        String accountId = "rbNJhW3DdOJ_6pQtyFMOMOvpO20qHDSFbj_pKvxckH8";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "디아블로한다";
        String id = "SLLwPTSZ7Qk88Z0ONNNww04gv94F9Sby2XK4cPocWvXo7w";
        String puuid = "gMPAG5C3sFtOyfn058rTaR86Ha78nQlZHJF0jEOIOozDLO-uTIa9QqcRK6e0uqXeHk9lLxw0Lra_gg";
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

        summonerService.matchesListLeadMore(name, accountId, 200, 400);
    }
}
