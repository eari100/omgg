package gg.om.omgg.api.riot.dto;

import gg.om.omgg.domain.match.Match;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Match toEntity(long gameId, int queueId, String platformId, int seasonId) {
        return Match.builder()
                .gameId(gameId)
                .queueId(queueId)
                .platformId(platformId)
                .seasonId(seasonId)
                .build();
    }
}
