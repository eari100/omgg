package gg.om.omgg.domain.participant;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipantRepositoryTests {
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Before
    public void 시간지연() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    @After
    public void cleanUp() {
        participantRepository.deleteAll();
        matchRepository.deleteAll();
    }

    @Test
    public void 게임참여자_전체조회() {
        long gameId = 5115327873L;
        int queueId = 420;
        String platformId = "KR";
        int seasonId = 13;
        long gameCreation = 1604932783165L;
        long gameDutation = 897L;

        Match match = Match.builder()
                .gameId(gameId)
                .queueId(queueId)
                .platformId(platformId)
                .seasonId(seasonId)
                .gameCreation(gameCreation)
                .gameDuration(gameDutation)
                .build();

        matchRepository.save(match);

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

        List<Participant> participantList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            participantList.add(
                    Participant.builder()
                            .participantId(
                                    ParticipantId.builder()
                                            .gameId(gameId)
                                            .participantId(i+1)
                                            .build()
                            )
                            .championId(championIds[i])
                            .teamId(teamIds[i])
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
                            .totalMinionsKilled(totalMinionsKilled_list[i])
                            .neutralMinionsKilled(neutralMinionsKilled_list[i])
                            .visionWardsBoughtInGame(visionWardsBoughtInGame_list[i])
                            .accoutId(accoutIds[i])
                            .summonerName(summonerNames[i])
                            .summonerId(summonerIds[i])
                            .build()
            );
        }

        participantRepository.saveAll(participantList);

        List<Participant> findParticipantList = participantRepository.findAll(Sort.by(Sort.Direction.ASC, "participantId"));

        for(int i=0;i<10;i++) {
            Participant participant = findParticipantList.get(i);

            assertThat(participant.getParticipantId().getGameId()).isEqualTo(gameId);
            assertThat(participant.getChampionId()).isEqualTo(championIds[i]);
            assertThat(participant.getTeamId()).isEqualTo(teamIds[i]);
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
            assertThat(participant.getTotalMinionsKilled()).isEqualTo(totalMinionsKilled_list[i]);
            assertThat(participant.getNeutralMinionsKilled()).isEqualTo(neutralMinionsKilled_list[i]);
            assertThat(participant.getVisionWardsBoughtInGame()).isEqualTo(visionWardsBoughtInGame_list[i]);
            assertThat(participant.getAccoutId()).isEqualTo(accoutIds[i]);
            assertThat(participant.getSummonerName()).isEqualTo(summonerNames[i]);
            assertThat(participant.getSummonerId()).isEqualTo(summonerIds[i]);
        }
    }
}
