package gg.om.omgg.web.dto;

import gg.om.omgg.domain.match.MatchReference;
import gg.om.omgg.domain.summoner.Summoner;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SummonerIntegrationInformationResponseDTO {
    private int profileIconId;
    private String name;
    private long summonerLevel;
    private String id;

    private long gameId;

    public SummonerIntegrationInformationResponseDTO(Summoner entity) {
        this.profileIconId = entity.getProfileIconId();
        this.name = entity.getName();
        this.summonerLevel = entity.getSummonerLevel();
        this.id = entity.getId();
    }

    public SummonerIntegrationInformationResponseDTO(MatchReference matchReference) {
        this.profileIconId = matchReference.getId().getSummoner().getProfileIconId();
        this.name = matchReference.getId().getSummoner().getName();
        this.summonerLevel = matchReference.getId().getSummoner().getSummonerLevel();
        this.id = matchReference.getId().getSummoner().getId();

        this.gameId = matchReference.getId().getGameId();
    }
}
