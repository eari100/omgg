package gg.om.omgg.api.riot.dto;

import gg.om.omgg.domain.summoner.Summoner;
import lombok.*;

@Getter
@NoArgsConstructor
public class SummonerDTO {
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private long summonerLevel;

    public Summoner toEntity() {
        return Summoner.builder()
                .id(id)
                .accountId(accountId)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .name(name)
                .puuid(puuid)
                .summonerLevel(summonerLevel)
                .build();
    }
}
