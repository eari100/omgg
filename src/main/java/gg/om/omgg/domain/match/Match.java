package gg.om.omgg.domain.match;

import gg.om.omgg.domain.participant.Participant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="game_match")
public class Match implements Comparator<Match> {
    @Id
    @Column(name="game_id")
    private long gameId;
    private int queueId;
    private String platformId;
    private int seasonId;
    private long gameCreation;
    private long gameDuration;

    @OneToMany
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    List<Participant> participants = new ArrayList<>();

    @Builder
    public Match(long gameId, int queueId, String platformId, int seasonId, long gameCreation, long gameDuration) {
        this.gameId = gameId;
        this.queueId = queueId;
        this.platformId = platformId;
        this.seasonId = seasonId;
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
    }

    // 내림차순 정렬
    @Override
    public int compare(Match match1, Match match2) {
        if(match1.getGameId() < match2.getGameId()) return 1;
        else if(match1.getGameId() > match2.getGameId()) return -1;

        return 0;
    }
}
