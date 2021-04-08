package gg.om.omgg.web.dto;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.summoner.Summoner;
import lombok.Getter;

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

    public SummonerIntegrationInformationResponseDTO(Summoner summoner, Match match) {
        this.profileIconId = summoner.getProfileIconId();
        this.name = summoner.getName();
        this.summonerLevel = summoner.getSummonerLevel();
        this.id = summoner.getId();
        this.gameId = match.getGameId();
    }
}
