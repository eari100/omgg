package gg.om.omgg.domain.participant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Participant {
    // ParticipantDto
    @EmbeddedId
    private ParticipantId participantId;
    private int championId;
    private int teamId;
    private int spell1Id;
    private int spell2Id;
    // ParticipantStatsDto
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
    // PlayerDto
    private String accoutId;
    private String summonerName;
    private String summonerId;

    @Builder
    public Participant(ParticipantId participantId, int championId, int teamId, int spell1Id, int spell2Id,
                       int item0, int item1, int item2, int item3, int item4, int item5, int item6,
                       int kills, int deaths, int assists, int perk0, int perkSubStyle,
                       int champLevel, int totalMinionsKilled, int neutralMinionsKilled, int visionWardsBoughtInGame,
                       String accoutId, String summonerName, String summonerId) {
        this.participantId = participantId;
        this.championId = championId;
        this.teamId = teamId;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
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
        this.accoutId = accoutId;
        this.summonerName = summonerName;
        this.summonerId = summonerId;
    }
}
