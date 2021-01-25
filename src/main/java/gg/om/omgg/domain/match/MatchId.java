package gg.om.omgg.domain.match;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Embeddable
public class MatchId implements Serializable {
    private String accountId;
    private Long gameId;

    @Builder
    public MatchId(String accountId, Long gameId) {
        this.accountId = accountId;
        this.gameId = gameId;
    }
}
