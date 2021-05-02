package gg.om.omgg.api.riot.dto;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.participant.Participant;
import gg.om.omgg.domain.participant.ParticipantId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MatchDTO {
    private long gameId;
    private List<ParticipantIdentityDTO> participantIdentities;
    private int queueId;
    private String gameType;
    private long gameDuration;
    private List<TeamStatsDTO> teams;
    private String platformId;
    private long gameCreation;
    private int seasonId;
    private String gameVersion;
    private int mapId;
    private String gameMode;
    private List<ParticipantDTO> participants;

    public Match matchToEntity() {
        return Match.builder()
                .gameId(gameId)
                .queueId(queueId)
                .platformId(platformId)
                .seasonId(seasonId)
                .gameCreation(gameCreation)
                .gameDuration(gameDuration)
                .build();
    }

    public List<Participant> participantToEntity() {
        List<Participant> participantList = new ArrayList<>();

        for(int i=0;i<10;i++) {
            ParticipantDTO participantDTO = participants.get(i);
            ParticipantIdentityDTO participantIdentityDTO = participantIdentities.get(i);

            participantList.add(
                Participant.builder()
                        .participantId(
                                ParticipantId.builder()
                                        .gameId(gameId)
                                        .participantId(participantDTO.getParticipantId())
                                        .build()
                        )
                        .championId(participantDTO.getChampionId())
                        .teamId(participantDTO.getTeamId())
                        .spell1Id(participantDTO.getSpell1Id())
                        .spell2Id(participantDTO.getSpell2Id())
                        .item0(participantDTO.getStats().getItem0())
                        .item1(participantDTO.getStats().getItem1())
                        .item2(participantDTO.getStats().getItem2())
                        .item3(participantDTO.getStats().getItem3())
                        .item4(participantDTO.getStats().getItem4())
                        .item5(participantDTO.getStats().getItem5())
                        .item6(participantDTO.getStats().getItem6())
                        .kills(participantDTO.getStats().getKills())
                        .deaths(participantDTO.getStats().getDeaths())
                        .assists(participantDTO.getStats().getAssists())
                        .perk0(participantDTO.getStats().getPerk0())
                        .perkSubStyle(participantDTO.getStats().getPerkSubStyle())
                        .champLevel(participantDTO.getStats().getChampLevel())
                        .totalMinionsKilled(participantDTO.getStats().getTotalMinionsKilled())
                        .neutralMinionsKilled(participantDTO.getStats().getNeutralMinionsKilled())
                        .win(participantDTO.getStats().isWin())
                        .visionWardsBoughtInGame(participantDTO.getStats().getVisionWardsBoughtInGame())
                        .accoutId(participantIdentityDTO.getPlayer().getAccountId())
                        .summonerName(participantIdentityDTO.getPlayer().getSummonerName())
                        .summonerId(participantIdentityDTO.getPlayer().getSummonerId())
                        .build()
            );
        }
        return participantList;
    }
}
