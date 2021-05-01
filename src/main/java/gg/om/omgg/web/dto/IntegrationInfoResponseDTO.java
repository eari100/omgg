package gg.om.omgg.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IntegrationInfoResponseDTO {
    // summoner
    private int profileIconId;
    private String name;
    private long summonerLevel;
    private String id;
    
    // match
    private long gameId;
    private int queueId;
    private String platformId;
    private int seasonId;
    private long gameCreation;
    private long gameDuration;
    
    // participant
    private int championId;
    private int spell1Id;
    private int spell2Id;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int kills;
    private int deaths;
    private int assists;
    private int perk0;
    private int perkSubStyle;
    private int champLevel;
    private int totalMinionsKilled;
    private int neutralMinionsKilled;
    private int visionWardsBoughtInGame;
    private boolean win;
    
    // participant alias
    private int player1ChampionId;
    private int player2ChampionId;
    private int player3ChampionId;
    private int player4ChampionId;
    private int player5ChampionId;
    private int player6ChampionId;
    private int player7ChampionId;
    private int player8ChampionId;
    private int player9ChampionId;
    private int player10ChampionId;
    private String player1SummonerName;
    private String player2SummonerName;
    private String player3SummonerName;
    private String player4SummonerName;
    private String player5SummonerName;
    private String player6SummonerName;
    private String player7SummonerName;
    private String player8SummonerName;
    private String player9SummonerName;
    private String player10SummonerName;
    
    @Builder
    public IntegrationInfoResponseDTO(
            int profileIconId, String name, long summonerLevel, String id,
            long gameId, int queueId, String platformId, int seasonId, long gameCreation, long gameDuration,
            int championId, int spell1Id, int spell2Id, int item0, int item1,
            int item2, int item3, int item4, int item5, int item6,
            int kills, int deaths, int assists, int perk0, int perkSubStyle,
            int champLevel, int totalMinionsKilled, int neutralMinionsKilled, int visionWardsBoughtInGame,
            int player1ChampionId, int player2ChampionId, int player3ChampionId, int player4ChampionId, int player5ChampionId,
            int player6ChampionId, int player7ChampionId, int player8ChampionId, int player9ChampionId, int player10ChampionId,
            String player1SummonerName, String player2SummonerName, String player3SummonerName, String player4SummonerName, String player5SummonerName,
            String player6SummonerName, String player7SummonerName, String player8SummonerName, String player9SummonerName, String player10SummonerName,
            boolean win) {

        this.profileIconId = profileIconId;
        this.name = name;
        this.summonerLevel = summonerLevel;
        this.id = id;
        this.gameId = gameId;
        this.queueId = queueId;
        this.platformId = platformId;
        this.seasonId = seasonId;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.championId = championId;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 =item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.perk0 = perk0;
        this.perkSubStyle = perkSubStyle;
        this.champLevel = champLevel;
        this.totalMinionsKilled = totalMinionsKilled;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.player1ChampionId = player1ChampionId;
        this.player2ChampionId = player2ChampionId;
        this.player3ChampionId = player3ChampionId;
        this.player4ChampionId = player4ChampionId;
        this.player5ChampionId = player5ChampionId;
        this.player6ChampionId = player6ChampionId;
        this.player7ChampionId = player7ChampionId;
        this.player8ChampionId = player8ChampionId;
        this.player9ChampionId = player9ChampionId;
        this.player10ChampionId = player10ChampionId;
        this.player1SummonerName = player1SummonerName;
        this.player2SummonerName = player2SummonerName;
        this.player3SummonerName = player3SummonerName;
        this.player4SummonerName = player4SummonerName;
        this.player5SummonerName = player5SummonerName;
        this.player6SummonerName = player6SummonerName;
        this.player7SummonerName = player7SummonerName;
        this.player8SummonerName = player8SummonerName;
        this.player9SummonerName = player9SummonerName;
        this.player10SummonerName = player10SummonerName;
        this.win = win;
    }
}
