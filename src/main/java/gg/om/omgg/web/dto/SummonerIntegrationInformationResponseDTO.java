package gg.om.omgg.web.dto;

import gg.om.omgg.domain.summoner.Summoner;
import lombok.Getter;

@Getter
public class SummonerIntegrationInformationResponseDTO {
    private int profileIconId;
    private String name;
    private long summonerLevel;
    private String id;

    public SummonerIntegrationInformationResponseDTO(Summoner entity) {
        this.profileIconId = entity.getProfileIconId();
        this.name = entity.getName();
        this.summonerLevel = entity.getSummonerLevel();
        this.id = entity.getId();
    }
}
