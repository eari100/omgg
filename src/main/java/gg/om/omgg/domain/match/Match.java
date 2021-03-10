package gg.om.omgg.domain.match;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Match {
    private long gameId;
    private int queueId;
    private String platformId;
    private int seasonId;

    @Builder
    public Match(long gameId, int queueId, String platformId, int seasonId) {
        this.gameId = gameId;
        this.queueId = queueId;
        this.platformId = platformId;
        this.seasonId = seasonId;
    }
}
