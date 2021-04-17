package gg.om.omgg.domain.match;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
public class Match {
    @Id
    @Column(name="game_id")
    private long gameId;
    private int queueId;
    private String platformId;
    private int seasonId;
    private long gameCreation;
    private long gameDuration;

    @Builder
    public Match(long gameId, int queueId, String platformId, int seasonId, long gameCreation, long gameDuration) {
        this.gameId = gameId;
        this.queueId = queueId;
        this.platformId = platformId;
        this.seasonId = seasonId;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
    }
}
