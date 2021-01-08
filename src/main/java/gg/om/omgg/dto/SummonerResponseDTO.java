package gg.om.omgg.dto;

import gg.om.omgg.domain.summoner.Summoner;
import lombok.Getter;

@Getter
public class SummonerResponseDTO {
    private int profileIconId;
    private String name;
    private long summonerLevel;

    public SummonerResponseDTO(Summoner entity) {
        this.profileIconId = entity.getProfileIconId();
        this.name = entity.getName();
        this.summonerLevel = entity.getSummonerLevel();
    }
}
