package gg.om.omgg.api.riot.dto;

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
}
