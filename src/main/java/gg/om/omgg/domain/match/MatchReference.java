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
public class MatchReference implements Serializable {
    @EmbeddedId
    private MatchReferenceId id;

    @Builder
    public MatchReference(MatchReferenceId id) {
        this.id = id;
    }
}
