package gg.om.omgg.api.riot.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class SummonerDTO {
    private final String accountId;
    private final int profileIconId;
    private final long revisionDate;
    private final String name;
    private final String id;
    private final String puuid;
    private final long summonerLevel;
}
