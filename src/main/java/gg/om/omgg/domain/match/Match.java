package gg.om.omgg.domain.match;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Match implements Serializable {
    @EmbeddedId
    private MatchId id;

    @Builder
    public Match(MatchId id) {
        this.id = id;
    }
}
