package gg.om.omgg.api.riot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlayerDTO {
    private int profileIcon;
    private String accountId;
    private String matchHistoryUri;
    private String currentAccountId;
    private String currentPlatformId;
    private String summonerName;
    private String summonerId;
    private String platformId;
}
