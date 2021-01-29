package gg.om.omgg.domain.match;

import gg.om.omgg.domain.summoner.Summoner;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Embeddable
public class MatchReferenceId implements Serializable {
    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName ="account_id")
    private Summoner summoner;
    private long gameId;

    @Builder
    public MatchReferenceId(Summoner summoner, Long gameId) {
        this.summoner = summoner;
        this.gameId = gameId;
    }
}
