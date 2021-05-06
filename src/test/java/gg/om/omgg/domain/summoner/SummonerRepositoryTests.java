package gg.om.omgg.domain.summoner;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.Participant;
import gg.om.omgg.domain.participant.ParticipantId;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummonerRepositoryTests {

    @Autowired
    SummonerRepository summonerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    ParticipantRepository participantRepository;

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
        participantRepository.deleteAll();
        matchRepository.deleteAll();
    }

    @Test
    public void 소환사_정보_불러오기() {

        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 294;

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

        List<Summoner> summonersList = summonerRepository.findAll();

        Summoner summoner = summonersList.get(0);
        assertThat(summoner.getAccountId()).isEqualTo(accountId);
        //assertThat(summoner.getProfileIconId()).isEqualTo(profileIconId);
        assertThat(summoner.getRevisionDate()).isEqualTo(revisionDate);
        assertThat(summoner.getName()).isEqualTo(name);
        assertThat(summoner.getId()).isEqualTo(id);
        assertThat(summoner.getPuuid()).isEqualTo(puuid);
        //assertThat(summoner.getSummonerLevel()).isEqualTo(summonerLevel);
    }

    @Test
    public void 이름으로_소환사_정보_불러오기() {
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293;

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

        Optional<Summoner> summoner = summonerRepository.findByName("거세짱123");
        assertThat(summoner.get().getAccountId()).isEqualTo(accountId);
        //assertThat(summoner.get().getProfileIconId()).isEqualTo(profileIconId);
        assertThat(summoner.get().getRevisionDate()).isEqualTo(revisionDate);
        assertThat(summoner.get().getName()).isEqualTo(name);
        assertThat(summoner.get().getId()).isEqualTo(id);
        assertThat(summoner.get().getPuuid()).isEqualTo(puuid);
        //assertThat(summoner.get().getSummonerLevel()).isEqualTo(summonerLevel);
    }

    @Test
    public void summoner_삭제() {
        // 초기값 세팅
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 294;

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

        summonerRepository.deleteById(id);

        Optional<Summoner> summoner = summonerRepository.findById(id);
        assertThat(summoner.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 데이터가_존재하는_findSummonerIntegrationInformationByName_조회테스트() {
        // summoner
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293;

        Summoner summoner = Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build();

        // match
        Long[] gameIds = {5115327873L, 5114612211L, 5114509070L, 5113679956L, 5113381568L,
                5113123898L, 5113140714L, 5113009318L, 5113093150L, 5113040393L,
                5112862930L, 5112801333L, 5111953625L, 5111855885L, 5111560760L,
                5111318269L, 5110948806L, 5110712716L, 5110647401L, 5110663852L};
        Arrays.sort(gameIds, Comparator.reverseOrder());

        int queueId = 420;
        String platformId = "KR";
        int seasonId = 13;

        // participant
        int[] championIds = {157, 101, 63, 55, 53, 84, 104, 75, 1, 119};
        int[] teamIds = {100, 100, 100, 100, 100, 200, 200, 200, 200, 200};
        int spell1Id = 1;
        int spell2Id = 4;
        int item0 = 3072;
        int item1 = 3094;
        int item2 = 0;
        int item3 = 0;
        int item4 = 0;
        int item5 = 3006;
        int item6 = 2052;
        int kills = 3;
        int deaths = 12;
        int assists = 9;
        int perk0 = 8005;
        int perkSubStyle = 8100;
        int champLevel = 18;
        int[] totalMinionsKilled_list = {50, 100, 150, 200, 250, 300, 350, 400, 450, 400};
        int[] neutralMinionsKilled_list = {50, 100, 150, 200, 250, 300, 350, 400, 450, 400};
        int[] visionWardsBoughtInGame_list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] accoutIds = {"vHuk1H17ddLGPoLZ9E5avI-17xPTY3QR8KvU1a9PAFTRjCM", "pL3JnDpEXvfs6Cg13qPHzQyUVq6S-RZQBMRAK1kaiWNIlx8",
                "gg_KK6R6lbQFLVT81HgoJslHjJ5luxMLFeESgvTYt5me", "8jWj2lLvs5ijQiJHpTAYVEl8cKEjNdHdEV7AAiguMf6UJhI", "_jKuGjotrfp-Q0Bk83Y3Iyxs9e4P96BFjD8xLF2wAUVxk-Q",
                "OMt_JGZCTrUIq0lCzOtbptboWK6ebJs-vJiqkDjDhZ9p", "1PGAJqehSvcIxnog4jePhoEOlpZZbtF7169qw95AL08", "7aUeEN0eFHYG0XCrDaDaYamhjXidpwfIBVZQjznKDbu4iVcfgROaZfuQ",
                "BGuEVk-8SNN5FkhBkqk8vMv4hHqPerU71mLw1l35n9JXdKrze9DdqGe-", "KNrfGudz2T1GqsafokjuvjNuu_U4X5jW1xftqqGqnZ1M95JRIi6qbpdF"};
        String[] summonerNames = {"아나스타샤르", "Maxim Chu", "거세짱123", "양스나", "육세웅15", "DNA Genius", "솔렘니스", "Cylvestrian", "엔젤정원", "고인남욱몽"};
        String[] summonerIds = {"nV3yU54l6-p23lTmHwE0Byh8u4egDMWB0VZgVH-KBODhm4o", "3xJRvpAzz8DCrl68y6_Pe4ih4F4HPKLXvjOFLRdZs2H3xXc",
                "H9h7OYqErUcfzYNV9TYDyPyIlmP3P6KihCztidNhTcyxxHs", "by5pAc9A9aWsb6vaRZEbUtSuQMpr5cGJl-DUXvS8uWWhklE", "Fa-NvjjixlevSqN5dfqC_LNS-RgEvcAUf7fco0qvKdIl7gc",
                "kdpMk97BRlWxo1XxvcnxpXYFx7Ud2dBKPnKZRTkXmqO8bA", "R2CG5kVuifWF-080i6Qd3JD4G8TnZ931gDC4p6cAggNR4A", "kB7fd-D3zbbweFEzSF2M8v4H5dcbNxdeAuqA6V1_BfsuFTo",
                "iZ7_Zg_23rr1ppp8uZKI2BY8IoJGiuuoBmINRBzXBpNbfak", "SKk28IZ2fIzRyXsC-DzI79ps_8IMr5CMZbyjABD_CorRu-w"};


        for(int i=0;i<20;i++) {
            Match match = matchRepository.save(
                    Match.builder()
                            .gameId(gameIds[i])
                            .queueId(queueId)
                            .platformId(platformId)
                            .seasonId(seasonId)
                            .build()
            );

            for(int j=0;j<10;j++) {
                participantRepository.save(
                        Participant.builder()
                                .participantId(
                                        ParticipantId.builder()
                                                .gameId(gameIds[i])
                                                .participantId(j+1)
                                                .build()
                                )
                                .championId(championIds[j])
                                .teamId(teamIds[j])
                                .spell1Id(spell1Id)
                                .spell2Id(spell2Id)
                                .item0(item0)
                                .item1(item1)
                                .item2(item2)
                                .item3(item3)
                                .item4(item4)
                                .item5(item5)
                                .item6(item6)
                                .kills(kills)
                                .deaths(deaths)
                                .assists(assists)
                                .perk0(perk0)
                                .perkSubStyle(perkSubStyle)
                                .champLevel(champLevel)
                                .totalMinionsKilled(totalMinionsKilled_list[j])
                                .neutralMinionsKilled(neutralMinionsKilled_list[j])
                                .visionWardsBoughtInGame(visionWardsBoughtInGame_list[j])
                                .accoutId(accoutIds[j])
                                .summonerName(summonerNames[j])
                                .summonerId(summonerIds[j])
                                .build()
                );
            }
            // 연관관계 추가
            summoner.getMatches().add(match);
        }
        // summoner_match 데이터 삽입
        summonerRepository.save(summoner);

        SummonerIntegrationInformationResponseDTO result = summonerRepository.findSummonerIntegrationInformationByName(name);

        assertThat(result.getProfileIconId()).isEqualTo(profileIconId);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getSummonerLevel()).isEqualTo(summonerLevel);
        assertThat(result.getId()).isEqualTo(id);

        for(int i=0;i<result.getMatches().size();i++) {
            Match match = result.getMatches().get(i);
            assertThat(match.getGameId()).isEqualTo(gameIds[i]);
            assertThat(match.getQueueId()).isEqualTo(queueId);
            assertThat(match.getPlatformId()).isEqualTo(platformId);
            assertThat(match.getSeasonId()).isEqualTo(seasonId);
            for(int j=0;j<match.getParticipants().size();j++) {
                Participant participant = match.getParticipants().get(j);
                assertThat(participant.getParticipantId().getGameId()).isEqualTo(gameIds[i]);
                assertThat(participant.getChampionId()).isEqualTo(championIds[j]);
                assertThat(participant.getTeamId()).isEqualTo(teamIds[j]);
                assertThat(participant.getSpell1Id()).isEqualTo(spell1Id);
                assertThat(participant.getSpell2Id()).isEqualTo(spell2Id);
                assertThat(participant.getItem0()).isEqualTo(item0);
                assertThat(participant.getItem1()).isEqualTo(item1);
                assertThat(participant.getItem2()).isEqualTo(item2);
                assertThat(participant.getItem4()).isEqualTo(item3);
                assertThat(participant.getItem5()).isEqualTo(item5);
                assertThat(participant.getItem6()).isEqualTo(item6);
                assertThat(participant.getKills()).isEqualTo(kills);
                assertThat(participant.getDeaths()).isEqualTo(deaths);
                assertThat(participant.getAssists()).isEqualTo(assists);
                assertThat(participant.getPerk0()).isEqualTo(perk0);
                assertThat(participant.getPerkSubStyle()).isEqualTo(perkSubStyle);
                assertThat(participant.getChampLevel()).isEqualTo(champLevel);
                assertThat(participant.getTotalMinionsKilled()).isEqualTo(totalMinionsKilled_list[j]);
                assertThat(participant.getNeutralMinionsKilled()).isEqualTo(neutralMinionsKilled_list[j]);
                assertThat(participant.getVisionWardsBoughtInGame()).isEqualTo(visionWardsBoughtInGame_list[j]);
                assertThat(participant.getAccoutId()).isEqualTo(accoutIds[j]);
                assertThat(participant.getSummonerName()).isEqualTo(summonerNames[j]);
                assertThat(participant.getSummonerId()).isEqualTo(summonerIds[j]);
            }
        }
    }

    @Test
    public void 데이터가_존재하지_않는_findSummonerIntegrationInformationByName_조회테스트() {
        String name = "거세짱123";
        summonerRepository.findSummonerIntegrationInformationByName(name);
    }

    @Test
    public void 데이터가_존재하는_findIntegrationInfoByName_조회테스트() {
        // summoner
        String accountId = "yy15F-qXoM8a1kqFL8iJ0xMUTF6e6ZZlWKPdlrvgZIcr";
        int profileIconId = 11;
        long revisionDate = 1609294136000L;
        String name = "거세짱123";
        String id = "qOshc-BI3WAaQuvgpPI7GY7w0ZfjTt2WJHX_46zdQVqotlI";
        String puuid = "blugvIvgoZB2GPmLQryiiVl_61CnLNNf50b_UGKkCqilTFa42mL_ZEfSEUJTICP_X-n6xuMjMg65YQ";
        long summonerLevel = 293;

        Summoner summoner = Summoner.builder()
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .id(id)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build();

        // match
        Long[] gameIds = {5115327873L, 5114612211L, 5114509070L, 5113679956L, 5113381568L,
                5113123898L, 5113140714L, 5113009318L, 5113093150L, 5113040393L,
                5112862930L, 5112801333L, 5111953625L, 5111855885L, 5111560760L,
                5111318269L, 5110948806L, 5110712716L, 5110647401L, 5110663852L};
        Arrays.sort(gameIds, Comparator.reverseOrder());

        int queueId = 420;
        String platformId = "KR";
        int seasonId = 13;

        // participant
        int[] championIds = {157, 101, 63, 55, 53, 84, 104, 75, 1, 119};
        int[] teamIds = {100, 100, 100, 100, 100, 200, 200, 200, 200, 200};
        int spell1Id = 1;
        int spell2Id = 4;
        int item0 = 3072;
        int item1 = 3094;
        int item2 = 0;
        int item3 = 0;
        int item4 = 0;
        int item5 = 3006;
        int item6 = 2052;
        int kills = 3;
        int deaths = 12;
        int assists = 9;
        int perk0 = 8005;
        int perkSubStyle = 8100;
        int champLevel = 18;
        int[] totalMinionsKilled_list = {50, 100, 150, 200, 250, 300, 350, 400, 450, 400};
        int[] neutralMinionsKilled_list = {50, 100, 150, 200, 250, 300, 350, 400, 450, 400};
        int[] visionWardsBoughtInGame_list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] accoutIds = {"vHuk1H17ddLGPoLZ9E5avI-17xPTY3QR8KvU1a9PAFTRjCM", "pL3JnDpEXvfs6Cg13qPHzQyUVq6S-RZQBMRAK1kaiWNIlx8",
                "gg_KK6R6lbQFLVT81HgoJslHjJ5luxMLFeESgvTYt5me", "8jWj2lLvs5ijQiJHpTAYVEl8cKEjNdHdEV7AAiguMf6UJhI", "_jKuGjotrfp-Q0Bk83Y3Iyxs9e4P96BFjD8xLF2wAUVxk-Q",
                "OMt_JGZCTrUIq0lCzOtbptboWK6ebJs-vJiqkDjDhZ9p", "1PGAJqehSvcIxnog4jePhoEOlpZZbtF7169qw95AL08", "7aUeEN0eFHYG0XCrDaDaYamhjXidpwfIBVZQjznKDbu4iVcfgROaZfuQ",
                "BGuEVk-8SNN5FkhBkqk8vMv4hHqPerU71mLw1l35n9JXdKrze9DdqGe-", "KNrfGudz2T1GqsafokjuvjNuu_U4X5jW1xftqqGqnZ1M95JRIi6qbpdF"};
        String[] summonerNames = {"아나스타샤르", "Maxim Chu", "거세짱123", "양스나", "육세웅15", "DNA Genius", "솔렘니스", "Cylvestrian", "엔젤정원", "고인남욱몽"};
        String[] summonerIds = {"nV3yU54l6-p23lTmHwE0Byh8u4egDMWB0VZgVH-KBODhm4o", "3xJRvpAzz8DCrl68y6_Pe4ih4F4HPKLXvjOFLRdZs2H3xXc",
                "H9h7OYqErUcfzYNV9TYDyPyIlmP3P6KihCztidNhTcyxxHs", "by5pAc9A9aWsb6vaRZEbUtSuQMpr5cGJl-DUXvS8uWWhklE", "Fa-NvjjixlevSqN5dfqC_LNS-RgEvcAUf7fco0qvKdIl7gc",
                "kdpMk97BRlWxo1XxvcnxpXYFx7Ud2dBKPnKZRTkXmqO8bA", "R2CG5kVuifWF-080i6Qd3JD4G8TnZ931gDC4p6cAggNR4A", "kB7fd-D3zbbweFEzSF2M8v4H5dcbNxdeAuqA6V1_BfsuFTo",
                "iZ7_Zg_23rr1ppp8uZKI2BY8IoJGiuuoBmINRBzXBpNbfak", "SKk28IZ2fIzRyXsC-DzI79ps_8IMr5CMZbyjABD_CorRu-w"};
        boolean[] wins = {true, true, true, true, true, false, false, false, false, false};

        for(int i=0;i<20;i++) {
            Match match = matchRepository.save(
                    Match.builder()
                            .gameId(gameIds[i])
                            .queueId(queueId)
                            .platformId(platformId)
                            .seasonId(seasonId)
                            .build()
            );

            for(int j=0;j<10;j++) {
                participantRepository.save(
                        Participant.builder()
                                .participantId(
                                        ParticipantId.builder()
                                                .gameId(gameIds[i])
                                                .participantId(j+1)
                                                .build()
                                )
                                .championId(championIds[j])
                                .teamId(teamIds[j])
                                .spell1Id(spell1Id)
                                .spell2Id(spell2Id)
                                .item0(item0)
                                .item1(item1)
                                .item2(item2)
                                .item3(item3)
                                .item4(item4)
                                .item5(item5)
                                .item6(item6)
                                .kills(kills)
                                .deaths(deaths)
                                .assists(assists)
                                .perk0(perk0)
                                .perkSubStyle(perkSubStyle)
                                .champLevel(champLevel)
                                .totalMinionsKilled(totalMinionsKilled_list[j])
                                .neutralMinionsKilled(neutralMinionsKilled_list[j])
                                .visionWardsBoughtInGame(visionWardsBoughtInGame_list[j])
                                .accoutId(accoutIds[j])
                                .summonerName(summonerNames[j])
                                .summonerId(summonerIds[j])
                                .win(wins[j])
                                .build()
                );
            }
            // 연관관계 추가
            summoner.getMatches().add(match);
        }
        // summoner_match 데이터 삽입
        summonerRepository.save(summoner);

        List<Object[]> result = summonerRepository.findIntegrationInfoByName(name, 0,200);
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

        assertThat(dtoList.size()).isEqualTo(20);
        for(int i=0;i<dtoList.size();i++) {
            IntegrationInfoResponseDTO dto = dtoList.get(i);
            assertThat(dto.getProfileIconId()).isEqualTo(profileIconId);
            assertThat(dto.getName()).isEqualTo(name);
            assertThat(dto.getSummonerLevel()).isEqualTo(summonerLevel);
            assertThat(dto.getId()).isEqualTo(id);
        }
    }

    @Test
    public void 데이터가_존재하지_않는_findIntegrationInfoByName_조회테스트() {
        String name = "거세짱123";
        List<Object[]> result = summonerRepository.findIntegrationInfoByName(name, 0,200);
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

        assertThat(dtoList.size()).isEqualTo(0);
    }
}
